//
//  ColorComponentsView.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/3.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import UIKit


internal class ColorComponentsView: UIView {

    @IBOutlet weak var componentRorHView: ColorComponentView!

    @IBOutlet weak var componentGorSView: ColorComponentView!

    @IBOutlet weak var componentBorVView: ColorComponentView!

    internal var componentsStyle = ColorComponentsStyle.rgb {
        didSet{
            componentsStyle == .rgb ? updateRGBValue() : updateHSVValue()
        }
    }
    
    internal var color = UIColor.white {
        didSet{
            componentsStyle == .rgb ? updateRGBValue() : updateHSVValue()
        }
    }
    
    private func updateRGBValue(){
        var r : CGFloat = 0
        var g : CGFloat = 0
        var b : CGFloat = 0
        var a : CGFloat = 0
        color.getRed(&r, green: &g, blue: &b, alpha: &a)
        assert(a != 0)
        componentRorHView.name = "R"
        componentGorSView.name = "G"
        componentBorVView.name = "B"
        
        componentRorHView.value = r
        componentGorSView.value = g
        componentBorVView.value = b
    }
    
    private func updateHSVValue(){
        var h : CGFloat = 0
        var s : CGFloat = 0
        var v : CGFloat = 0
        var a : CGFloat = 0
        color.getHue(&h, saturation: &s, brightness: &v, alpha: &a)
        assert(a != 0)
        componentRorHView.name = "H"
        componentGorSView.name = "S"
        componentBorVView.name = "V"
        
        componentRorHView.value = h
        componentGorSView.value = s
        componentBorVView.value = v
    }
    
    
}
