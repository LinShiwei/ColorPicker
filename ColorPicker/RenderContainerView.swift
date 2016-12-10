
//
//  RenderContainerView.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/10.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import UIKit
import AVFoundation

class RenderContainerView: UIView {

    private var hasLayoutRenderView = false
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        if !hasLayoutRenderView {
            let renderView = viewWithTag(1)
            let centerFrame = AVMakeRect(aspectRatio: CGSize(width: 480, height: 640), insideRect: bounds)
            renderView?.frame = centerFrame
            hasLayoutRenderView = true
        }
    }
}
