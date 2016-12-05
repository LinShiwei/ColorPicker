//
//  AddColorViewController.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/3.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import UIKit

class AddColorViewController: UIViewController {

    @IBOutlet weak var colorIndicationView: UIView!
    @IBOutlet weak var colorMakerView: ColorMakerView!
    @IBOutlet weak var inputButtonsContainerView: InputButtonsContainerView!
    
    private let colorCollectionSourceManager = ColorCollectionSourceManager.sharedManager
    
    override func viewDidLoad() {
        super.viewDidLoad()
        colorIndicationView.layer.borderWidth = 1
        colorIndicationView.layer.borderColor = UIColor.black.cgColor

    }

    @IBAction func tapAmongInputButtons(_ sender: UIButton) {
        guard let text = sender.currentTitle else {return}
        switch text {
        case "1","2","3","4","5","6","7","8","9","0","A","B","C","D","E","F":
            colorMakerView.inputOneNumber(numberString: text)
            colorIndicationView.backgroundColor = colorMakerView.getCurrentColor()
        case "Clear":
            colorMakerView.clearAllTextFields()
        case "Tab":
            colorMakerView.tabToFocusOnNextTextField()
        default:
            fatalError()
        }
    }
    @IBAction func saveCurrentColor(_ sender: UIBarButtonItem) {
        
        colorCollectionSourceManager.saveOneCollectedColor(color: CollectedColor(date: Date(), color: colorMakerView.getCurrentColor())){success in
            if !success {
                print("fail to save color")
            }
        }
    }
}
