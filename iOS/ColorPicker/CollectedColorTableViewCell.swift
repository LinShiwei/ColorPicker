//
//  CollectedColorTableViewCell.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/3.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import UIKit

internal protocol CellManageDelegate {
    func deleteCell(sender: UITableViewCell)
}

class CollectedColorTableViewCell: UITableViewCell {

    @IBOutlet weak var colorInformationView: ColorInformationView!
    
    internal var delegate: CellManageDelegate?
    
    internal var color = UIColor.white{
        didSet{
            colorInformationView.currentColor = color
        }
    }
    
    internal var colorInformation : String {
        get{
            var r : CGFloat = 0
            var g : CGFloat = 0
            var b : CGFloat = 0
            var a : CGFloat = 0
            color.getRed(&r, green: &g, blue: &b, alpha: &a)
            assert(a != 0)
            
            let decR = String(Int(255*r))
            let decG = String(Int(255*g))
            let decB = String(Int(255*b))
            
            let hexR = String(Int(255*r), radix: 16, uppercase: true)
            let hexG = String(Int(255*b), radix: 16, uppercase: true)
            let hexB = String(Int(255*b), radix: 16, uppercase: true)
            
            let floatR = String(format: "%.2f", r)
            let floatG = String(format: "%.2f", g)
            let floatB = String(format: "%.2f", b)
            
            return hexR+" "+hexG+" "+hexB+" | "+decR+" "+decG+" "+decB+" | "+floatR+" "+floatG+" "+floatB
        }
    }
    override func awakeFromNib() {
        super.awakeFromNib()
        addGesture()
    }

    internal func updateColorValue(){
        colorInformationView.currentColor = color
    }
    
    internal func addGesture(){
        let cellSliderGestureRecognizer = DRCellSlideGestureRecognizer()

        let circleAction = DRCellSlideAction(forFraction: -0.25)
        circleAction?.icon = #imageLiteral(resourceName: "Delete")
        circleAction?.activeBackgroundColor = Theme.shared.cellSwipeToDeleteColor
        circleAction?.behavior = .pushBehavior
        circleAction?.didTriggerBlock = {[weak self] Void in
            if let safeSelf = self {
                safeSelf.delegate?.deleteCell(sender: safeSelf)
            }
        }
        cellSliderGestureRecognizer.addActions([circleAction])
        addGestureRecognizer(cellSliderGestureRecognizer)
    }
}
