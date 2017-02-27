//
//  Theme.swift
//  ColorPicker
//
//  Created by Linsw on 17/1/12.
//  Copyright © 2017年 Linsw. All rights reserved.
//

import UIKit

internal class Theme {
    static let shared = Theme()
    private init(){}
    
    internal let mainThemeColor = UIColor(red: 0.11, green: 0.51, blue: 0.82, alpha: 1)
    internal let barItemColor = UIColor.white
    internal let navigationTitleColor = UIColor.white
    
    internal let cellSwipeToDeleteColor = UIColor(red: 0.87, green: 0.24, blue: 0.054, alpha: 1)
    internal let textFieldFocusedBackgroundColor = UIColor.yellow
}
