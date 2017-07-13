//
//  ColorComponentView.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/3.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import UIKit

internal class ColorComponentView: UIView {

    @IBOutlet weak var componentTitleLabel: UILabel!
    @IBOutlet weak var hexValueLabel: UILabel!
    @IBOutlet weak var decValueLabel: UILabel!
    @IBOutlet weak var normalizedValueLabel: UILabel!
    
    internal var name = "" {
        didSet{
            componentTitleLabel.text = name;
        }
    }
    
    internal var value : CGFloat = 1 {
        didSet{
            normalizedValueLabel.text = String(format: "%.2f", value)
            decValueLabel.text = String(Int(255*value))
            hexValueLabel.text = String(Int(255*value), radix: 16, uppercase: true)
        }
    }
}
