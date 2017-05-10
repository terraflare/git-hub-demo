/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import entity.Category;
import entity.InvoicePurchase;
import entity.InvoiceSales;
import entity.Item_In;
import entity.Item_In_Warehouse;
import entity.Item_Out;
import entity.Item_Out_Warehouse;
import entity.Merk;
import entity.Packaging;
import entity.Quality;
import entity.User;
import entity.Warehouse;
import java.util.Date;
import java.util.List;
import service.GeneralService;
import service.ItemService;
import service.MerkService;
import service.PackagingService;
import service.QualityService;
import service.UserService;
import service.WarehouseService;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class LoginController {
    UserService userService;
    GeneralService generalService;
    QualityService qualityService;
    WarehouseService warehouseService;
    PackagingService packagingService;
    MerkService merkService;
    ItemService itemService;
    
    public LoginController(){
        userService = new UserService();
        generalService = new GeneralService();
        qualityService = new QualityService();
        warehouseService = new WarehouseService();
        packagingService = new PackagingService();
        merkService = new MerkService();
        itemService = new ItemService();
    }
    
    public User Login(String username, String password){
        
//        User user = new User();
//        user.setUsername("purnama");
//        user.setPassword("purnama");
//        user.setRole("SUPERADMIN");
//        user.setActive(true);
//        
//        generalService.Persist(user);
        
//        for(int i = 0; i < 150; i++){
//            Merk merk = new Merk();
//            merk.setName("merk " + i);
//            merk.setNote("merk " + i);
//            generalService.Persist(merk);
//            
//            Packaging packaging = new Packaging();
//            packaging.setAmount(i);
//            packaging.setUnit("KG");
//            packaging.setNote(i + " KG");
//            generalService.Persist(packaging);
//            
//            Item_Out item = new Item_Out();
//            item.setName("item " + i);
//            item.setMerk(merk);
//            item.setPackaging(packaging);
//            item.setSacksize(100);
//            item.setNote("item " + i);
//            item.setActive(GlobalFields.ACTIVE);
//
//            generalService.Persist(item);
//
//            List warehouselist = warehouseService.getWarehouseList();
//
//            for(Object o : warehouselist){
//                Warehouse warehouse = (Warehouse)o;
//
//                Item_Out_Warehouse iow = new Item_Out_Warehouse();
//                iow.setItem(item);
//                iow.setWarehouse(warehouse);
//                iow.setStock(0);
//
//                generalService.Persist(iow);
//            }
//        }
        
//        for(int i = 0; i < 150; i++){
//            Category category = new Category();
//            category.setName("category " + i);
//            category.setNote("category " + i);
//            generalService.Persist(category);
//            
//            Quality quality = new Quality();
//            quality.setCategory(category);
//            quality.setName("quality " + i);
//            quality.setNote("quality " + i);
//            generalService.Persist(quality);
//            
//            Item_In item = new Item_In();
//            item.setName("item " + i);
//            item.setQuality(quality);
//            item.setNote("item" + i);
//            item.setActive(GlobalFields.ACTIVE);
//            
//            generalService.Persist(item);
//            
//            List warehouselist = warehouseService.getWarehouseList();
//        
//            for(Object o : warehouselist){
//                Warehouse warehouse = (Warehouse)o;
//
//                Item_In_Warehouse iiw = new Item_In_Warehouse();
//                iiw.setItem(item);
//                iiw.setWarehouse(warehouse);
//                iiw.setStock(0);
//
//                generalService.Persist(iiw);
//            }
//        }
        
//        for(int i = 0; i < 150; i++){
//            InvoicePurchase ip = new InvoicePurchase();
//            ip.setDate(new Date());
//            ip.setItem(itemService.getItemIn(i+1));
//            ip.setNote("test");
//            ip.setQuantity(i);
//            ip.setStatus(GlobalFields.UNCHECKED);
//            ip.setUser(userService.getUser(1));
//            ip.setWarehouse(warehouseService.getWarehouse(1));
//            generalService.Persist(ip);
//        }
        
//        for(int i = 0; i < 150; i++){
//            InvoiceSales is = new InvoiceSales();
//            is.setDate(new Date());
//            is.setItem(itemService.getItemOut(i+1));
//            is.setNote("test");
//            is.setQuantity(i);
//            is.setStatus(GlobalFields.UNCHECKED);
//            is.setUser(userService.getUser(1));
//            is.setWarehouse(warehouseService.getWarehouse(1));
//            generalService.Persist(is);
//        }
        
        return userService.Login(username, password);
    }
    
    public User init(){
        User user = new User();
        user.setUsername("superadmin");
        user.setPassword("superadmin");
        user.setRole("SUPERADMIN");
        user.setActive(GlobalFields.ACTIVE);
        
        generalService.Persist(user);
        
        return user;
    }
}
