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
    
    internal let pullIndicationText: String
    internal let releaseIndicationText: String
    //Info VC
    internal let titleForSection0: String
    internal let titleForSection1: String
    internal let emailAlertTitle: String
    internal let emailAlertMessage: String
    internal let sendAdvice: String
    internal let scoringApp: String
    internal let purchaseItem1: String
    internal let purchaseItem2: String
    
    private init(){
        if isLanguageChineseSimple {
            tableEmptyString = "还没有收藏的颜色哦"
            copyInformationTitle = "拷贝到剪贴板。"
            okString = "好"
            saveMessage = "保存成功。"
            saveString = "保存"
            
            pullIndicationText = "下拉打开“支持”页"
            releaseIndicationText = "松开打开“支持“页"
            
            titleForSection0 = "支持 ColorPicker"
            titleForSection1 = "给作者买点吃的"
            emailAlertTitle = "邮箱不可用"
            emailAlertMessage = "地址已拷贝到剪贴板："
            sendAdvice = "意见反馈"
            scoringApp = "给应用评分"
            purchaseItem1 = "一包辣条"
            purchaseItem2 = "一瓶饮料"

        }else{
            tableEmptyString = "There is no color yet."
            copyInformationTitle = "Copy to pasteboard."
            okString = "OK"
            saveMessage = "Save success."
            saveString = "Save"
            
            pullIndicationText = "Pull to Support"
            releaseIndicationText = "Release to Support"
            
            titleForSection0 = "Support ColorPicker"
            titleForSection1 = "Buy developer something"
            emailAlertTitle = "No account in Mail app"
            emailAlertMessage = "E-mail address has been copied to pasteboard: "
            sendAdvice = "Send advice"
            scoringApp = "Score this app"
            purchaseItem1 = "A packet of latiao"
            purchaseItem2 = "A bottle of drink"
        }
        
    }

}
