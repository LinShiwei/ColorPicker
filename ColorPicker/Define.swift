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

internal let sharedAlertVC : UIAlertController = {
    
    if let languageID = Bundle.main.preferredLocalizations.first,languageID.contains("zh-Hans"){
        let alert = UIAlertController(title: "保存", message: "保存成功", preferredStyle: .alert)
        let okAction = UIAlertAction(title: "好", style: .default, handler: nil)
        alert.addAction(okAction)
        return alert
    }else{
        let alert = UIAlertController(title: "Save", message: "Save success.", preferredStyle: .alert)
        let okAction = UIAlertAction(title: "OK", style: .default, handler: nil)
        alert.addAction(okAction)
        return alert
    }
}()

