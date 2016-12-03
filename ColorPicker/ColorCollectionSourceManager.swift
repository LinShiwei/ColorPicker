//
//  ColorCollectionSourceManager.swift
//  ColorPicker
//
//  Created by Linsw on 16/12/3.
//  Copyright © 2016年 Linsw. All rights reserved.
//

import UIKit
import CoreData


internal final class ColorCollectionSourceManager{
    static let sharedManager = ColorCollectionSourceManager()
    private init(){
    }
    
    private var objects = [NSManagedObject]()

    //MARK: Public API
    internal func getAllCollectedColor(_ completion: @escaping (Bool,[CollectedColor]?)->Void){
        var colors = [CollectedColor]()
        let fetchRequest = NSFetchRequest<NSManagedObject>(entityName: "Color")
        fetchRequest.sortDescriptors = [NSSortDescriptor(key: "date", ascending: true)]
        do {
            objects = try getManagedContext().fetch(fetchRequest)
        } catch let error as NSError {
            print("Could not fetch \(error), \(error.userInfo)")
        }
        if objects.count > 0 {
            for object in objects {
                if let date = object.value(forKey: "date") as? Date,let red = object.value(forKey: "red") as? Float,let green = object.value(forKey: "green") as? Float,let blue = object.value(forKey: "blue") as? Float{
                    colors.append(CollectedColor(date: date, color: UIColor(red: CGFloat(red), green: CGFloat(green), blue: CGFloat(blue), alpha: 1)))
                }
            }
        }else{
            colors = mockColorCollectionData()
            saveMockCollectedColors(colors: colors)
        }
        completion(true,colors)
    
    }
    
    internal func saveOneCollectedColor(color: CollectedColor, _ completion: ((Bool)->Void)? = nil){
        DispatchQueue.global().async{[unowned self] in
            let managedContext = self.getManagedContext()
            let entity = NSEntityDescription.entity(forEntityName: "Color", in:managedContext)
            let colorObject = NSManagedObject(entity: entity!, insertInto: managedContext)
            var r : CGFloat = 0
            var g : CGFloat = 0
            var b : CGFloat = 0
            var a : CGFloat = 0
            color.color.getRed(&r, green: &g, blue: &b, alpha: &a)
            assert(a != 0)
            
            colorObject.setValue(color.date, forKey: "date")
            colorObject.setValue(r, forKey: "red")
            colorObject.setValue(g, forKey: "green")
            colorObject.setValue(b, forKey: "blue")
            do {
                try managedContext.save()
            }
            catch let error as NSError {
                print("Could not save \(error), \(error.userInfo)")
                completion?(false)
            }
            completion?(true)
            
        }
    }
    
    internal func deleteOneCollectedColor(color: CollectedColor, _ completion: ((Bool)->Void)? = nil){
        let managedContext = getManagedContext()
        let count = objects.count
        for (index,object) in objects.enumerated() where object.value(forKey: "date") as! Date == color.date {
            managedContext.delete(object)
            do {
                try managedContext.save()
            }
            catch let error as NSError {
                print("Could not save \(error), \(error.userInfo)")
                completion?(false)
            }
            objects.remove(at: index)
        }
        assert(objects.count == count - 1)
        completion?(true)
    }
    //MARK: Private help func
    private func getManagedContext()->NSManagedObjectContext{
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        return appDelegate.persistentContainer.viewContext
    }
    
    private func mockColorCollectionData()->[CollectedColor]{
        var colors = [CollectedColor]()
        var date = Date(timeIntervalSinceReferenceDate: 0)
        for _ in 0...20 {
            let r = CGFloat(arc4random_uniform(255))
            let g = CGFloat(arc4random_uniform(255))
            let b = CGFloat(arc4random_uniform(255))
            date.addTimeInterval(1)
            colors.append(CollectedColor(date: date, color: UIColor(red: r/255.0, green: g/255.0, blue: b/255.0, alpha: 1)))
        }
        return colors
    }
    
    private func saveMockCollectedColors(colors: [CollectedColor]){
        DispatchQueue.global().async {[unowned self] in
            let managedContext = self.getManagedContext()
            let entity = NSEntityDescription.entity(forEntityName: "Color", in: managedContext)
            for color in colors {
                var r : CGFloat = 0
                var g : CGFloat = 0
                var b : CGFloat = 0
                var a : CGFloat = 0
                color.color.getRed(&r, green: &g, blue: &b, alpha: &a)
                assert(a != 0)
                
                let colorObject = NSManagedObject(entity: entity!, insertInto: managedContext)
                colorObject.setValue(Float(r), forKey: "red")
                colorObject.setValue(Float(g), forKey: "green")
                colorObject.setValue(Float(b), forKey: "blue")
                colorObject.setValue(color.date, forKey: "date")
                self.objects.append(colorObject)
            }
            do {
                try managedContext.save()
            }catch let error as NSError {
                print("Could not save \(error), \(error.userInfo)")
            }
            
        }
    }
}
    
