//
//  CollectedColorTableViewCell.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/3.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import UIKit

class CollectedColorTableViewCell: UITableViewCell {

    @IBOutlet weak var colorView: UIView!
    
    @IBOutlet weak var componentsContainer: CellComponentsContainerView!
    
    internal var color = UIColor.white{
        didSet{
            componentsContainer.color = color
            colorView.layer.backgroundColor = color.cgColor
        }
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        colorView.layer.cornerRadius = 10
    }

    internal func updateColorValue(){
        componentsContainer.updateColorValue()
    }
}
