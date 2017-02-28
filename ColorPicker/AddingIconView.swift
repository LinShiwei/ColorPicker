//
//  addingIconView.swift
//  CatchIdea
//
//  Created by Linsw on 17/1/3.
//  Copyright © 2017年 Linsw. All rights reserved.
//

import UIKit

class AddingIconView: UIView {
    
    internal var active = false {
        didSet{
            active ? performActiveAnimation() : performInactiveAnimation()
        }
    }
    private let dotLayer = CALayer()
    private let verticalLineLayer = CALayer()

    override init(frame: CGRect) {
        super.init(frame: frame)
        assert(frame.width == frame.height)
        layer.cornerRadius = frame.width/2
        layer.borderWidth = 2
        layer.borderColor = UIColor.lightGray.cgColor
        
        dotLayer.frame = CGRect(x: frame.width/2-1, y: frame.height/4, width: 2, height: 2)
        dotLayer.backgroundColor = layer.borderColor
        
        verticalLineLayer.frame = CGRect(x: frame.width/2-1, y: frame.height/4+4, width: 2, height: frame.height/2-4)
        verticalLineLayer.backgroundColor = layer.borderColor
        
        layer.addSublayer(dotLayer)
        layer.addSublayer(verticalLineLayer)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func performActiveAnimation(){
        self.layer.removeAllAnimations()
        let rotationAnimation = CABasicAnimation(keyPath: "transform.rotation.z")
        rotationAnimation.toValue = M_PI * 2.0
        rotationAnimation.duration = 0.3
        self.layer.add(rotationAnimation, forKey: "rotationAnimation")
        self.layer.borderColor = Theme.shared.mainThemeColor.cgColor
        dotLayer.backgroundColor = layer.borderColor
        verticalLineLayer.backgroundColor = layer.borderColor
    }
    
    private func performInactiveAnimation(){
        self.layer.removeAllAnimations()
        let rotationBackAnimation = CABasicAnimation(keyPath: "transform.rotation.z")
        rotationBackAnimation.toValue = -M_PI * 2.0
        rotationBackAnimation.duration = 0.3
        self.layer.add(rotationBackAnimation, forKey: "rotationBackAnimation")
        self.layer.borderColor = UIColor.lightGray.cgColor
        dotLayer.backgroundColor = layer.borderColor
        verticalLineLayer.backgroundColor = layer.borderColor
    }
    
    
}
