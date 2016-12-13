//
//  HEXColorMakerView.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/3.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import UIKit

class HEXColorMakerView: UIView {

    @IBOutlet weak var titleButton: UIButton!
    @IBOutlet weak var firstTextField: MakerTextField!
    @IBOutlet weak var secondTextField: MakerTextField!
    @IBOutlet weak var thirdTextField: MakerTextField!

    internal var inputMode = MakerInputMode.hex {
        didSet {
            switch inputMode {
            case .dec:
                firstTextField.focusedBackgroundColor = UIColor.white
                secondTextField.focusedBackgroundColor = UIColor.white
                thirdTextField.focusedBackgroundColor = UIColor.white
                currentTextField.isFocusedOn = false
                currentTextField.text = ""
                titleButton.layer.borderColor = UIColor.lightGray.cgColor
            case .hex:
                firstTextField.focusedBackgroundColor = textFieldFocusedBackgroundColor
                secondTextField.focusedBackgroundColor = textFieldFocusedBackgroundColor
                thirdTextField.focusedBackgroundColor = textFieldFocusedBackgroundColor
                currentTextField.isFocusedOn = true
                titleButton.layer.borderColor = UIColor.black.cgColor
            }
        }
    }
    
    internal var currentTextField : MakerTextField!
    internal var currentValue = 255 {
        didSet{
            currentTextField.text = String(currentValue, radix: 16, uppercase: true)
            currentTextField.colorComponentValue = currentValue 
        }
    }

    @IBAction func tapTitleButton(_ sender: UIButton) {
        if let view = superview as? ColorMakerView{
            view.inputMode = .hex
        }
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        titleButton.titleLabel?.adjustsFontSizeToFitWidth = true
        titleButton.layer.borderColor = UIColor.lightGray.cgColor
        titleButton.layer.borderWidth = 1
        titleButton.layer.cornerRadius = 5
        
        firstTextField.focusedBackgroundColor = UIColor.white
        secondTextField.focusedBackgroundColor = UIColor.white
        thirdTextField.focusedBackgroundColor = UIColor.white
        
        currentTextField = firstTextField
//        currentTextField.isFocusedOn = false
        
        if colorStyle == .hsv {
            firstTextField.colorComponentValue = 0
            secondTextField.colorComponentValue = 0
            
            firstTextField.placeholder = "0"
            secondTextField.placeholder = "0"
        }
    }

    internal func clearAllTextFields(){
        firstTextField.text = ""
        secondTextField.text = ""
        thirdTextField.text = ""
    }
    
    internal func switchToNextTextField(){
        currentTextField.isFocusedOn = false
        if currentTextField === firstTextField {
            currentTextField = secondTextField
        }else{
            if currentTextField === secondTextField {
                currentTextField = thirdTextField
            }else{
                currentTextField = firstTextField
            }
        }
        currentTextField.isFocusedOn = true
    }
    
}
