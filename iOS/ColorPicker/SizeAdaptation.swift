//
//  SizeAdaptation.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/8.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import UIKit

internal class SizeAdaptation {
    static let shared = SizeAdaptation()
    
    internal let tableViewCellHeight : CGFloat = isIPhone ? 88 : 104
    internal let inputButtonFontSize : CGFloat = isIPhone ? 18 : 36
    internal let inputButtonCornerRadius : CGFloat = isIPhone ? 5 : 10
    internal let colorInformationViewHeight : CGFloat = isIPhone ? 88 : 104
    internal let magnifierViewSize = isIPhone ? CGSize(width: 120, height: 120) : CGSize(width: 240, height: 240)
    internal let colorAnchorViewSize = isIPhone ? CGSize(width: 40, height: 40) : CGSize(width: 80, height: 80)
    
    private init(){
    }
    
}
