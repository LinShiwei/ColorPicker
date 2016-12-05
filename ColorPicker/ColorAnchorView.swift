//
//  ColorAnchorView.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/4.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import UIKit

class ColorAnchorView: UIView {

    private let ringGap : CGFloat = -2

    private let magnifierView = UIView(frame: CGRect(x: 0, y: 0, width: 120, height: 120))
    private let magnifyingImageView = UIImageView()
    
    internal var targetView = UIView()
    
    internal var currentFocusColor = #colorLiteral(red: 0.9529411793, green: 0.6862745285, blue: 0.1333333403, alpha: 1)
    
    internal init(center: CGPoint, size :CGSize, targetView :UIView){
        let frame = CGRect(x: center.x-size.width/2, y: center.y-size.height/2, width: size.width, height: size.height)
        super.init(frame: frame)
        assert(frame.width == frame.height)
        assert(frame.width > 10)
        
        self.targetView = targetView
        
        isUserInteractionEnabled = false
        layer.borderWidth = 1
        layer.borderColor = UIColor.black.cgColor
        layer.cornerRadius = frame.width/2
        layer.backgroundColor = nil
        
        let innerRingLayer = CALayer()
        innerRingLayer.frame = CGRect(x: ringGap, y: ringGap, width: frame.width-ringGap*2, height: frame.height-ringGap*2)
        innerRingLayer.borderWidth = 1
        innerRingLayer.borderColor = UIColor.white.cgColor
        innerRingLayer.cornerRadius = innerRingLayer.frame.width/2
        innerRingLayer.backgroundColor = nil
        
        layer.addSublayer(innerRingLayer)
        
        let centerDotLayer = CALayer()
        centerDotLayer.frame = CGRect(x: frame.width/2, y: frame.height/2, width: 1, height: 1)
        centerDotLayer.backgroundColor = UIColor.black.cgColor
        
        layer.addSublayer(centerDotLayer)
        
        initMagnifierView()
    }
    
    private override init(frame:CGRect){
        super.init(frame: frame)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func initMagnifierView(){
        magnifierView.center = CGPoint(x: frame.width/2, y: -magnifierView.frame.height/2-20)
//        magnifierView.layer.borderWidth = 1
//        magnifierView.layer.borderColor = UIColor.black.cgColor
        magnifierView.layer.cornerRadius = magnifierView.frame.width/2
        magnifierView.layer.masksToBounds = true
        
        let gap : CGFloat                                                                                                                                                                                                                                                   = 5.0
        magnifyingImageView.frame = CGRect(x: -gap, y: -gap, width: magnifierView.frame.width+gap*2, height: magnifierView.frame.height+gap*2)
        magnifyingImageView.contentMode = UIViewContentMode.scaleToFill
        magnifyingImageView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        magnifyingImageView.clipsToBounds = true
        magnifierView.addSubview(magnifyingImageView)
        
        addSubview(magnifierView)
    }
    
    private func snapshotTargetView(_ view: UIView!, inRect rect: CGRect!) -> UIImage! {
        //Hide self
//        self.isHidden = true
        let scale = UIScreen.main.scale
        //Snapshot of view
        UIGraphicsBeginImageContextWithOptions(rect.size, false, scale)
        UIGraphicsGetCurrentContext()?.translateBy(x: -rect.origin.x, y: -rect.origin.y)
        view.layer.render(in: UIGraphicsGetCurrentContext()!) //Need this to stop screen flashing, but it's slower
        let snapshotImage = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        //Show self
//        self.isHidden = false
        return snapshotImage
    }
    
    private func resizeImage(_ image: UIImage, toNewSize newSize:CGSize) -> UIImage {
        let scale = UIScreen.main.scale
        
        UIGraphicsBeginImageContextWithOptions(newSize, false, scale)
        image.draw(in: CGRect(x: 0, y: 0, width: newSize.width, height: newSize.height))
        let newImage = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        
        return newImage!
    }
    
    internal func refreshImage() {
        var newImage = snapshotTargetView(targetView, inRect: frame)
        newImage = resizeImage(newImage!, toNewSize: magnifyingImageView.frame.size)
        magnifyingImageView.image = newImage
    }
    
}
