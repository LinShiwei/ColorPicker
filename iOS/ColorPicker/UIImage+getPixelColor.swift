//
//  UIImage+getPixelColor.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/5.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import UIKit

extension UIImage {
    internal func getPixelColor(pos: CGPoint) -> UIColor {
        //当图片尺寸是 1600*1600 时，pos 取值范围应该是 0～1599
        if pos.x<0 || pos.y<0 || pos.x>=self.size.width || pos.y>=self.size.height {
            return UIColor.white
        }else{
            let pixelData = self.cgImage!.dataProvider!.data
            let data: UnsafePointer<UInt8> = CFDataGetBytePtr(pixelData)
            let pixelInfo: Int = ((Int(self.size.width) * Int(pos.y)) + Int(pos.x)) * 4

            let r = CGFloat(data[pixelInfo]) / CGFloat(255.0)
            let g = CGFloat(data[pixelInfo+1]) / CGFloat(255.0)
            let b = CGFloat(data[pixelInfo+2]) / CGFloat(255.0)
            let a = CGFloat(data[pixelInfo+3]) / CGFloat(255.0)
            return UIColor(red: r, green: g, blue: b, alpha: a)
        }
    }
}
