//
//  ColorComponentsContainerView.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/3.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import UIKit

class ColorComponentsContainerView: UIView {
    
    private var componentRorHView : ColorComponentView?
    private var componentGorSView : ColorComponentView?
    private var componentBorVView : ColorComponentView?
    
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    override func layoutSubviews() {
        initComponentsView()
    }
    
    private func initComponentsView(){
        if componentRorHView == nil {
            if let viewFromNib = Bundle.main.loadNibNamed("ColorComponentView", owner: self, options: nil)?.first as? ColorComponentView {
            viewFromNib.frame = CGRect(x: 0, y: 0, width: frame.width, height: frame.height/3)
            componentRorHView = viewFromNib
            self.addSubview(componentRorHView!)
            }
        }
        if componentGorSView == nil {
            if let viewFromNib = Bundle.main.loadNibNamed("ColorComponentView", owner: self, options: nil)?.first as? ColorComponentView {
                viewFromNib.frame = CGRect(x: 0, y: frame.height/3, width: frame.width, height: frame.height/3)
                componentGorSView = viewFromNib
                self.addSubview(componentGorSView!)
            }
        }
        if componentBorVView == nil {
            if let viewFromNib = Bundle.main.loadNibNamed("ColorComponentView", owner: self, options: nil)?.first as? ColorComponentView {
                viewFromNib.frame = CGRect(x: 0, y: frame.height*2/3, width: frame.width, height: frame.height/3)
                componentBorVView = viewFromNib
                self.addSubview(componentBorVView!)
            }
        }
        updateColorValue()
    }

    internal var color = UIColor.white 
    
    internal func updateColorValue(){
        colorStyle == .rgb ? updateRGBValue() : updateHSVValue()
    }
    
    private func updateRGBValue(){
        var r : CGFloat = 0
        var g : CGFloat = 0
        var b : CGFloat = 0
        var a : CGFloat = 0
        color.getRed(&r, green: &g, blue: &b, alpha: &a)
        assert(a != 0)
        componentRorHView?.name = "R"
        componentGorSView?.name = "G"
        componentBorVView?.name = "B"
        
        componentRorHView?.value = r
        componentGorSView?.value = g
        componentBorVView?.value = b
    }
    
    private func updateHSVValue(){
        var h : CGFloat = 0
        var s : CGFloat = 0
        var v : CGFloat = 0
        var a : CGFloat = 0
        color.getHue(&h, saturation: &s, brightness: &v, alpha: &a)
        assert(a != 0)
        componentRorHView?.name = "H"
        componentGorSView?.name = "S"
        componentBorVView?.name = "V"
        
        componentRorHView?.value = h
        componentGorSView?.value = s
        componentBorVView?.value = v
    }
}
