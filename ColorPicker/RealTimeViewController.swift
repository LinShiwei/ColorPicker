//
//  RealTimeViewController.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/9.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import UIKit
import GPUImage
import AVFoundation

class RealTimeViewController: UIViewController {

    private var camera : Camera?
    private var cameraRawData : RawDataOutput?
    
    private var cameraStop = true
    
    private let colorCollectionSourceManager = ColorCollectionSourceManager.shared
    
    @IBOutlet weak var cameraControlBtn: UIBarButtonItem!
    @IBOutlet weak var renderView: RenderView!
    @IBOutlet weak var colorInformationView: ColorInformationView!
//MARK: Life cycle
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewDidLayoutSubviews() {
        if camera == nil {
            cameraRawData = RawDataOutput()
            cameraRawData!.dataAvailableCallback = {data in
                let centerPointDataStartIndex = ((data.count/4)/2+240)*4
                
                let r = CGFloat(data[centerPointDataStartIndex])/255.0
                let g = CGFloat(data[centerPointDataStartIndex+1])/255.0
                let b = CGFloat(data[centerPointDataStartIndex+2])/255.0
                let a = CGFloat(data[centerPointDataStartIndex+3])/255.0
                
                let centerPointColor = UIColor(red: r, green: g, blue: b, alpha: a)
                
                DispatchQueue.main.async { [unowned self] in
                    self.colorInformationView.currentColor = centerPointColor
                }
            }
            do {
                //            camera = try Camera(sessionPreset: AVCaptureSessionPresetHigh)
                camera = try Camera(sessionPreset: AVCaptureSessionPreset640x480)
                camera! --> renderView
                camera! --> cameraRawData!
                camera!.startCapture()
                cameraStop = false
            } catch {
                fatalError("Could not initialize rendering pipeline: \(error)")
            }
        }
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        camera?.stopCapture()
    }
//MARK: IBAction
    @IBAction func saveCurrentColor(_ sender: UIBarButtonItem) {
        colorCollectionSourceManager.saveOneCollectedColor(color: CollectedColor(date: Date(), color: colorInformationView.currentColor)){success in
            if !success {
                print("fail to save color")
            }else{
                self.present(sharedAlertVC,animated: true,completion: nil)
            }
        }
    }
    @IBAction func pauseCapture(_ sender: UIBarButtonItem) {
        if cameraStop {
            camera?.startCapture()
            sender.image = #imageLiteral(resourceName: "Pause Filled-22")
        } else {
            camera?.stopCapture()
            sender.image = #imageLiteral(resourceName: "Play Filled-22")
        }
        cameraStop = !cameraStop
    }
}
