//
//  ColorCollectionViewController.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/3.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import UIKit

class ColorCollectionViewController: UIViewController {

    fileprivate let colorCollectionSourceManager = ColorCollectionSourceManager.shared
    
    @IBOutlet weak var colorCollectionTableView: CollectedColorTableView!
    //MARK: Life cycle
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        colorCollectionSourceManager.getAllCollectedColor{[unowned self](success,colors) in
            if(success&&(colors != nil)){
                self.colorCollectionTableView.collectedColors = colors!
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
        return SizeAdaptation.shared.tableViewCellHeight
    }

    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let pasteBoard = UIPasteboard.general
        pasteBoard.string = (tableView.cellForRow(at: indexPath) as! CollectedColorTableViewCell).colorInformation
        
        let alert = UIAlertController(title: LocalizationStrings.shared.copyInformationTitle, message: pasteBoard.string, preferredStyle: .alert)
        let okAction = UIAlertAction(title: LocalizationStrings.shared.okString, style: .default, handler: nil)
        alert.addAction(okAction)
        self.present(alert,animated: true,completion: nil)
    }
    
}

extension ColorCollectionViewController: UITableViewDataSource{
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        guard let table = tableView as? CollectedColorTableView else {
            return 0
        }
        return table.collectedColors.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "CollectedColorTableViewCell", for: indexPath) as! CollectedColorTableViewCell
        if let table = tableView as? CollectedColorTableView {
            cell.color = table.collectedColors[indexPath.row].color
            cell.delegate = table
        }
        return cell
    }

}
