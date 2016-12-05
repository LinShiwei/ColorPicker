//
//  MakerTextField.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/4.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import UIKit

internal class MakerTextField: UITextField {

    
    //Should change text before setting `colorComponentValue`
    internal var colorComponentValue = 255 {
        didSet{
            placeholder = text
        }
    }
    internal var isFocusedOn = false {
        didSet{
            self.backgroundColor = isFocusedOn ? focusedBackgroundColor : UIColor.white
            if isFocusedOn {
                text = ""
            }
        }
    }
    
    internal var focusedBackgroundColor = UIColor.yellow
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        placeholder = String(colorComponentValue)
    }
}
