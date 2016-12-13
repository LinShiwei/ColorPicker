//
//  InputButtonsContainerView.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/5.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import UIKit

class InputButtonsContainerView: UIView {

    var hexButtons = [UIButton]()
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        
        //Configure buttons
        for view in subviews {
            for btn in view.subviews where btn is UIButton {
                let button = btn as! UIButton
                button.layer.cornerRadius = SizeAdaptation.sharedAdaptation.inputButtonCornerRadius
                button.layer.borderWidth = 1
                button.layer.borderColor = UIColor.black.cgColor
                button.titleLabel?.font = UIFont.systemFont(ofSize: SizeAdaptation.sharedAdaptation.inputButtonFontSize)
                if button.tag > 9 && button.tag < 16 {
                    hexButtons.append(button)
                }
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
