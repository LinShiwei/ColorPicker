//
//  CollectedColorTableView.swift
//  ColorPicker
//
//  Created by Linsw on 17/1/12.
//  Copyright © 2017年 Linsw. All rights reserved.
//

import UIKit

class CollectedColorTableView: UITableView {

    internal var collectedColors = [CollectedColor]()

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)

        tableFooterView = UIView()
    }
}

extension CollectedColorTableView: CellManageDelegate {
    func deleteCell(sender: UITableViewCell){
        if let indexPath = indexPath(for: sender) {
            ColorCollectionSourceManager.shared.deleteOneCollectedColor(color: collectedColors[indexPath.row]){success in
                if !success{
                    print("fail to delete color")
                }
            }
            collectedColors.remove(at: indexPath.row)
            deleteRows(at: [indexPath], with: .left)
        }
    }
}
