//
//  LocalizationStrings.swift
//  ColorPicker
//
//  Created by Linsw on 17/2/2.
//  Copyright © 2017年 Linsw. All rights reserved.
//

import Foundation

internal class LocalizationStrings {
    static let shared = LocalizationStrings()
    
    internal let tableEmptyString: String
    internal let copyInformationTitle: String
    internal let okString: String
    internal let saveMessage: String
    internal let saveString: String
    
    private init(){
        if isLanguageChineseSimple {
            tableEmptyString = "还没有收藏的颜色哦"
            copyInformationTitle = "拷贝到剪贴板。"
            okString = "好"
            saveMessage = "保存成功。"
            saveString = "保存"
        }else{
            tableEmptyString = "There is no color yet."
            copyInformationTitle = "Copy to pasteboard."
            okString = "OK"
            saveMessage = "Save success."
            saveString = "Save"
        }
        
    }

}
