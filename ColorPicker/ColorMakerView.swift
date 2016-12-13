//
//  ColorMakerView.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/3.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import UIKit
enum MakerInputMode {
    case dec,hex
}

class ColorMakerView: UIView {
    
    private var rgbhsvMakerView : RGBHSVMakerView?
    private var hexMakerView : HEXColorMakerView?
    
    internal var inputMode = MakerInputMode.dec {
        didSet{
            for view in superview!.subviews where view is InputButtonsContainerView{
                (view as! InputButtonsContainerView).inputMode = inputMode
            }
            rgbhsvMakerView?.inputMode = inputMode
            hexMakerView?.inputMode = inputMode
        }
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    override func layoutSubviews() {
        initMakerViews()
    }
    
    private func initMakerViews(){
        if rgbhsvMakerView == nil {
            if let viewFromNib = Bundle.main.loadNibNamed("RGBHSVMakerView", owner: self, options: nil)?.first as? RGBHSVMakerView {
                viewFromNib.frame = CGRect(x: 0, y: 0, width: frame.width, height: 30)
                rgbhsvMakerView = viewFromNib
                self.addSubview(rgbhsvMakerView!)
            }
        }
        if hexMakerView == nil {
            if let viewFromNib = Bundle.main.loadNibNamed("HEXColorMakerView", owner: self, options: nil)?.first as? HEXColorMakerView {
                viewFromNib.frame = CGRect(x: 0, y: 30+8, width: frame.width, height: 30)
                hexMakerView = viewFromNib
                self.addSubview(hexMakerView!)
            }
        }
        self.frame.size = CGSize(width: self.frame.width, height: 30*2+8)
        self.layoutIfNeeded()
    }
    
    internal func tabToFocusOnNextTextField(){
        rgbhsvMakerView?.switchToNextTextField()
        hexMakerView?.switchToNextTextField()
    }
    
    internal func clearAllTextFields(){
        rgbhsvMakerView?.clearAllTextFields()
        hexMakerView?.clearAllTextFields()
    }
    
    internal func inputOneNumber(numberString: String){
        guard let rgbhsvView = rgbhsvMakerView, let hexView = hexMakerView else {return}
        switch inputMode {
        case .dec:
            guard let dec = Int(rgbhsvView.currentTextField.text! + numberString) else {return}
            if (dec>=0)&&(dec<256) {
                rgbhsvView.currentValue = dec
                hexView.currentValue = dec
            }
        case .hex:
            let dec = hexToDecInt(num: hexView.currentTextField.text! + numberString)
            if (dec>=0)&&(dec<256) {
                hexView.currentValue = dec
                rgbhsvView.currentValue = dec
            }
        }
    }
    
    internal func getCurrentColor()-> UIColor {
        guard let rgbhsvView = rgbhsvMakerView, let _ = hexMakerView else {return UIColor.white}
        return rgbhsvView.currentColor
    }
    
    //MARK: Help func
    private func hexToDecInt(num:String) -> Int {
        let str = num.uppercased()
        var sum = 0
        for i in str.utf8 {
            sum = sum * 16 + Int(i) - 48 // 0-9 从48开始
            if i >= 65 {                 // A-Z 从65开始，但有初始值10，所以应该是减去55
                sum -= 7
            }
        }
        return sum
    }
}

