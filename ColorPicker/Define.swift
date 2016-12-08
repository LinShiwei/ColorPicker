//
//  Define.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/3.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import Foundation
import UIKit

internal var colorStyle = ColorComponentsStyle.rgb

internal let windowBounds = UIScreen.main.bounds

internal let textFieldFocusedBackgroundColor = UIColor.yellow

internal let isIPhone = (UI_USER_INTERFACE_IDIOM() == .phone)

internal enum ColorComponentsStyle {
    case rgb,hsv
}

internal struct CollectedColor {
    let date : Date
    let color : UIColor
}
