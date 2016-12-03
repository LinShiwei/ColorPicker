//
//  Define.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/3.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import Foundation
import UIKit

var colorStyle = ColorComponentsStyle.rgb


enum ColorComponentsStyle {
    case rgb,hsv
}

struct CollectedColor {
    let date : Date
    let color : UIColor
}
