//
//  CollectedColorTableViewCell.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/3.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import UIKit

class CollectedColorTableViewCell: UITableViewCell {

    @IBOutlet weak var colorIndicationView: UIView!
    
    @IBOutlet weak var componentsContainer: CellComponentsContainerView!
    
    internal var color = UIColor.white{
        didSet{
            componentsContainer.color = color
            colorIndicationView.layer.backgroundColor = color.cgColor
        }
    }
    
    internal var colorInformation : String {
        get{
            var r : CGFloat = 0
            var g : CGFloat = 0
            var b : CGFloat = 0
            var a : CGFloat = 0
            color.getRed(&r, green: &g, blue: &b, alpha: &a)
            assert(a != 0)
            
            let decR = String(Int(255*r))
            let decG = String(Int(255*g))
            let decB = String(Int(255*b))
            
            let hexR = String(Int(255*r), radix: 16, uppercase: true)
            let hexG = String(Int(255*b), radix: 16, uppercase: true)
            let hexB = String(Int(255*b), radix: 16, uppercase: true)
            
            let floatR = String(format: "%.2f", r)
            let floatG = String(format: "%.2f", g)
            let floatB = String(format: "%.2f", b)
            
            return hexR+" "+hexG+" "+hexB+" | "+decR+" "+decG+" "+decB+" | "+floatR+" "+floatG+" "+floatB
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        colorIndicationView.layer.cornerRadius = 10
    }

    internal func updateColorValue(){
        componentsContainer.updateColorValue()
    }
}
