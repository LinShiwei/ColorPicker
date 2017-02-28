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
    
    internal let dimPresentAnimationController = DimPresentAnimationController()
    internal let dimDismissAnimationController = DimDismissAnimationController()

    //MARK: Life cycle
    override func viewDidLoad() {
        super.viewDidLoad()
        colorCollectionTableView.addPullRefresh{ [weak self] in
            
            if ViewControllermanager.shared.infoVC == nil {
                self?.performSegue(withIdentifier: "ShowSupportPage", sender: nil)
            }else{
                
            }
            
            self?.colorCollectionTableView.stopPullRefreshEver()
        }
        
        self.navigationController?.navigationBar.barTintColor = Theme.shared.mainThemeColor
        self.navigationController?.navigationBar.tintColor = Theme.shared.barItemColor
        self.navigationController?.navigationBar.titleTextAttributes = [NSForegroundColorAttributeName: Theme.shared.navigationTitleColor]

    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        colorCollectionSourceManager.getAllCollectedColor{[weak self](success,colors) in
            if(success&&(colors != nil)){
                self?.colorCollectionTableView.collectedColors = colors!
                self?.colorCollectionTableView.reloadData()
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
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        guard let identifier = segue.identifier else {return}
        switch identifier {
        case "ShowSupportPage":
            if let destinationViewController = segue.destination as? InfoViewController {
                ViewControllermanager.shared.infoVC = destinationViewController
                destinationViewController.transitioningDelegate = self
                dimPresentAnimationController.dimCenter = CGPoint(x: windowBounds.width/2, y: 88)
            }
            break
        default:
            break
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

extension ColorCollectionViewController: UIViewControllerTransitioningDelegate {
    
    func animationController(forPresented presented: UIViewController, presenting: UIViewController, source: UIViewController) -> UIViewControllerAnimatedTransitioning? {
        switch presented {
        case is InfoViewController:
            return dimPresentAnimationController
        default:
            return nil
        }
    }
    
    func animationController(forDismissed dismissed: UIViewController) -> UIViewControllerAnimatedTransitioning? {
        switch dismissed {
        case is InfoViewController:
            return dimDismissAnimationController
        default:
            return nil
        }
        
    }
}
