//
//  InputButtonsContainerView.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/5.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import UIKit

class InputButtonsContainerView: UIView {

    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */
    var hexButtons = [UIButton]()
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        
        for view in subviews {
            for btn in view.subviews where btn is UIButton {
                btn.layer.cornerRadius = 5
                btn.layer.borderWidth = 1
                btn.layer.borderColor = UIColor.black.cgColor
                if btn.tag > 9 && btn.tag < 16 {
                    hexButtons.append(btn as! UIButton)
                }
//                if let title = (btn as! UIButton).currentTitle{
//                    if title == "A"||title == "B"||title == "C"||title == "D"||title == "E"||title == "F" {
//                        hexButtons.append(btn as! UIButton)
//                    }
//                }
            }
        }
        defer{
            inputMode = .dec
        }
    }
    
    var inputMode = MakerInputMode.dec {
        didSet{
            switch inputMode {
            case .dec:
                for btn in hexButtons {
                    btn.layer.borderColor = UIColor.lightGray.cgColor
                    btn.setTitleColor(UIColor.lightGray, for: .normal)
                }
            case .hex:
                for btn in hexButtons {
                    btn.layer.borderColor = UIColor.black.cgColor
                    btn.setTitleColor(UIColor.black, for: .normal)
                }
            }
        }
    }
}
