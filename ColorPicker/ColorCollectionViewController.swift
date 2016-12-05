//
//  ColorCollectionViewController.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/3.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import UIKit

class ColorCollectionViewController: UIViewController {

    fileprivate let colorCollectionSourceManager = ColorCollectionSourceManager.sharedManager
    
    fileprivate var collectedColors = [CollectedColor]()
    
    @IBOutlet weak var colorCollectionTableView: UITableView!
    //MARK: Life cycle
    override func viewDidLoad() {
        super.viewDidLoad()
    
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        colorCollectionSourceManager.getAllCollectedColor{[unowned self](success,colors) in
            if(success&&(colors != nil)){
                self.collectedColors = colors!
                self.colorCollectionTableView.reloadData()
            }else{
                print("fail to getColor")
            }
        }
    }
    
    //MARK: IBAction
    @IBAction func tapToChangeColorStyle(_ sender: UIBarButtonItem) {
        if sender.title == "RGB" {
            sender.title = "HSV"
            colorStyle = .hsv
        }else{
            sender.title = "RGB"
            colorStyle = .rgb
        }
        for cell in colorCollectionTableView.visibleCells {
            (cell as! CollectedColorTableViewCell).updateColorValue()
        }
    }
}

extension ColorCollectionViewController: UITableViewDelegate{
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 88
    }
    
    @objc(tableView:canFocusRowAtIndexPath:) func tableView(_ tableView: UITableView, canFocusRowAt indexPath: IndexPath) -> Bool {
        return true
    }
    
    @objc(tableView:commitEditingStyle:forRowAtIndexPath:) func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            colorCollectionSourceManager.deleteOneCollectedColor(color: collectedColors[indexPath.row]){success in
                if !success{
                    print("fail to delete color")
                }
            }
            collectedColors.remove(at: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: .fade)
        }
    }
    
}

extension ColorCollectionViewController: UITableViewDataSource{
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return collectedColors.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "CollectedColorTableViewCell", for: indexPath) as! CollectedColorTableViewCell
        cell.color = collectedColors[indexPath.row].color
        cell.updateColorValue()
        return cell
    }

}
