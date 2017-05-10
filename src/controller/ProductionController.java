/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Item_In;
import entity.Item_In_Warehouse;
import entity.Item_Out;
import entity.Item_Out_Warehouse;
import entity.Production;
import entity.User;
import entity.Warehouse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import service.GeneralService;
import service.ProductionService;
import service.WarehouseService;
import tablemodel.ProductionTableModel;
import util.DateUtil;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class ProductionController {
    private final ProductionService productionService;
    private final GeneralService generalService;
    private final WarehouseService warehouseService;
    
    public ProductionController(){
        productionService = new ProductionService();
        generalService = new GeneralService();
        warehouseService = new WarehouseService();
    }
    
    public Production getProduction(int id){
        return productionService.getProduction(id);
    }
    
    public ArrayList<Production> getProductionList(){
        return(ArrayList<Production>)productionService.getProductionList(GlobalFields.USER);
    }
    
    public Production addProduction(User user, Date date, String warehouse, String note){
        
        Warehouse w = warehouseService.getWarehouse(warehouse);
        
        if(w == null){
            return null;
        }
        
        Production production = new Production();
        production.setUser(user);
        production.setNote(note);
        production.setDate(date);
        production.setWarehouse(w);
        production.setStatus(GlobalFields.UNCHECKED);
        
        generalService.Persist(production);
        
        return production;
    }
    
    public void editProduction(ProductionTableModel ptm, int id , Date date, String warehouse, String note){
        Warehouse ware = warehouseService.getWarehouse(warehouse);
        
        Production production = getProduction(id);
        production.setNote(note);
        production.setDate(date);
        production.setWarehouse(ware);
        
        generalService.Merge(production);
        
        ptm.editRow(production);
    }
    
    public boolean editProduction(Production production){
        Production oldproduction = productionService.getProduction(production.getId());
        
        if(production.getItemin() == null && production.getInquantity() != 0){
            return GlobalFields.FAIL;
        }
        
        if(production.getItemout() == null && production.getOutquantity() != 0){
            return GlobalFields.FAIL;
        }
        
        try{
            Item_Out olditemout = oldproduction.getItemout();
            Item_Out itemout = production.getItemout();
            Item_In olditemin = oldproduction.getItemin();
            Item_In itemin = production.getItemin();

            Item_Out_Warehouse oldiow = warehouseService.getItem_Out_Warehouse(oldproduction.getWarehouse(), olditemout);
            Item_In_Warehouse oldiiw = warehouseService.getItem_In_Warehouse(oldproduction.getWarehouse(), olditemin);
            Item_In_Warehouse iiw = warehouseService.getItem_In_Warehouse(production.getWarehouse(), itemin);
            Item_Out_Warehouse iow = warehouseService.getItem_Out_Warehouse(production.getWarehouse(), itemout);

            int oldinquantity = oldproduction.getInquantity();
            int inquantity = production.getInquantity();
            float oldoutquantity = oldproduction.getOutquantity();
            float outquantity = production.getOutquantity();

            if(olditemin.getId() == itemin.getId() && oldinquantity == inquantity && olditemout.getId() == itemout.getId() && oldoutquantity == outquantity){
                //changing expected out quantity or no change
                generalService.Merge(production);
                return GlobalFields.SUCCESS;
            }
            else if(olditemout.getId() == itemout.getId() && oldoutquantity == outquantity){
                //changing itemin and or in quantity
                if(olditemin.getId() == itemin.getId()){
                    int difference = inquantity - oldinquantity;
                    
                    if(iiw.getStock() - difference < 0){
                        return GlobalFields.FAIL;
                    }
                    else{
                        iiw.setStock(iiw.getStock() - difference);
                    }
                }
                else{
                    oldiiw.setStock(oldiiw.getStock() + oldinquantity);
                    if(iiw.getStock() - inquantity < 0){
                        return GlobalFields.FAIL;
                    }
                    else{
                        iiw.setStock(iiw.getStock() - production.getInquantity());
                    }
                }
            }
            else if(olditemin.getId() == itemin.getId() && oldinquantity == inquantity){
                //changing itemout and or out quantity and expected out quantity
                if(olditemout.getId() == itemout.getId()){
                    float difference = outquantity - oldoutquantity;
                    
                    if(iow.getStock() + difference < 0){
                        return GlobalFields.FAIL;
                    }
                    else{
                        iow.setStock(iow.getStock() + difference);
                    }
                }
                else{
                    if(oldiow.getStock() - oldoutquantity < 0){
                        return GlobalFields.FAIL;
                    }
                    else{
                        oldiow.setStock(oldiow.getStock() - oldoutquantity);
                    }
                    iow.setStock(iow.getStock() + outquantity);
                }
            }
            else{
                //changing everything
                if(olditemout.getId() == itemout.getId() && olditemin.getId() == itemin.getId()){
                    float difference = outquantity - oldoutquantity;
                    
                    if(iow.getStock() + difference < 0){
                        return GlobalFields.FAIL;
                    }
                    else{
                        iow.setStock(iow.getStock() + difference);
                    }
                    
                    int difference2 = inquantity - oldinquantity;
                    
                    if(iiw.getStock() - difference2 < 0){
                        return GlobalFields.FAIL;
                    }
                    else{
                        iiw.setStock(iiw.getStock() - difference2);
                    }
                }
                else if(olditemout.getId() == itemout.getId()){
                    float difference = outquantity - oldoutquantity;
                    
                    if(iow.getStock() + difference < 0){
                        return GlobalFields.FAIL;
                    }
                    else{
                        iow.setStock(iow.getStock() + difference);
                    }
                    
                    oldiiw.setStock(oldiiw.getStock() + oldinquantity);
                    if(iiw.getStock() - inquantity < 0){
                        return GlobalFields.FAIL;
                    }
                    else{
                        iiw.setStock(iiw.getStock() - production.getInquantity());
                    }
                }
                else if(olditemin.getId() == itemin.getId()){
                    int difference2 = inquantity - oldinquantity;
                    
                    if(iiw.getStock() - difference2 < 0){
                        return GlobalFields.FAIL;
                    }
                    else{
                        iiw.setStock(iiw.getStock() - difference2);
                    }
                    
                    if(oldiow.getStock() - oldoutquantity < 0){
                        return GlobalFields.FAIL;
                    }
                    else{
                        oldiow.setStock(oldiow.getStock() - oldoutquantity);
                    }
                    iow.setStock(iow.getStock() + outquantity);
                }
                else{
                    oldiiw.setStock(oldiiw.getStock() + oldinquantity);
                    if(iiw.getStock() - inquantity < 0){
                        return GlobalFields.FAIL;
                    }
                    else{
                        iiw.setStock(iiw.getStock() - production.getInquantity());
                    }

                    if(oldiow.getStock() - oldoutquantity < 0){
                        return GlobalFields.FAIL;
                    }
                    else{
                        oldiow.setStock(oldiow.getStock() - oldoutquantity);
                    }
                    iow.setStock(iow.getStock() + outquantity);
                }
            }

            generalService.Merge(oldiiw);
            generalService.Merge(oldiow);
            generalService.Merge(iiw);
            generalService.Merge(iow);
            generalService.Merge(production);

            return GlobalFields.SUCCESS;
        }
        catch(Exception e){
            Item_Out itemout = production.getItemout();
            Item_In itemin = production.getItemin();
            
            Item_In_Warehouse iiw = warehouseService.getItem_In_Warehouse(production.getWarehouse(), itemin);
            Item_Out_Warehouse iow = warehouseService.getItem_Out_Warehouse(production.getWarehouse(), itemout);
            
            int inquantity = production.getInquantity();
            float outquantity = production.getOutquantity();
            
            if(iiw.getStock() - inquantity < 0){
                return GlobalFields.FAIL;
            }
            else{
                iiw.setStock(iiw.getStock() - production.getInquantity());
            }
            
            iow.setStock(iow.getStock() + outquantity);
            
            generalService.Merge(iiw);
            generalService.Merge(iow);
            generalService.Merge(production);

            return GlobalFields.SUCCESS;
        }
    }
        
//        try{
//            Item_Out olditemout = oldproduction.getItemout();
//            Item_Out_Warehouse oldiow = warehouseService.getItem_Out_Warehouse(oldproduction.getWarehouse(), olditemout);
//            if(oldiow.getStock() - oldproduction.getOutquantity() < 0){
//                return GlobalFields.FAIL;
//            }
//            oldiow.setStock(oldiow.getStock() - oldproduction.getOutquantity());
//            generalService.Merge(oldiow);
//        }
//        catch(Exception e){
//            System.out.println("masuk1 " + e);
//        }
//        
//        try{
//            Item_In olditemin = oldproduction.getItemin();
//            Item_In_Warehouse oldiiw = warehouseService.getItem_In_Warehouse(oldproduction.getWarehouse(), olditemin);
//            oldiiw.setStock(oldiiw.getStock() + oldproduction.getInquantity());
//            
//            generalService.Merge(oldiiw);
//            
//        }
//        catch(Exception e){
//            System.out.println("masuk2 " + e);
//        }
//        
//        Item_In itemin = production.getItemin();
//        Item_Out itemout = production.getItemout();
//        
//        Item_In_Warehouse iiw = warehouseService.getItem_In_Warehouse(production.getWarehouse(), itemin);
//        Item_Out_Warehouse iow = warehouseService.getItem_Out_Warehouse(production.getWarehouse(), itemout);
//        
//        if(iiw.getStock() - production.getInquantity() < 0){
//            return GlobalFields.FAIL;
//        }
//        
//        iiw.setStock(iiw.getStock() - production.getInquantity());
//        iow.setStock(iow.getStock() + production.getOutquantity());
//        
//        generalService.Merge(iiw);
//        generalService.Merge(iow);
//        
//        generalService.Merge(production);
//        
//        return GlobalFields.SUCCESS;
    
    public boolean deleteProduction(int id){
        Production production = getProduction(id);
        
        if(production.getItemin() == null && production.getItemout() == null){
            generalService.Delete(production);
            return GlobalFields.SUCCESS;
        }
        
        try{
            Item_In itemin = production.getItemin();
            Item_Out itemout = production.getItemout();
            
            Item_In_Warehouse iiw = warehouseService.getItem_In_Warehouse(production.getWarehouse(), itemin);
            Item_Out_Warehouse iow = warehouseService.getItem_Out_Warehouse(production.getWarehouse(), itemout);
            
            if(iow.getStock() - production.getOutquantity() < 0){
                return GlobalFields.FAIL;
            }
            
            iiw.setStock(iiw.getStock() + production.getInquantity());
            
            iow.setStock(iow.getStock() - production.getOutquantity());
            
            generalService.Merge(iiw);
            generalService.Merge(iow);
            generalService.Delete(production);
            return GlobalFields.SUCCESS;
        }
        catch(Exception e){
            System.out.println(e);
            return GlobalFields.FAIL;
        }
    }
    
    public void deleteAllProduction(Date end){
       
        Date date = DateUtil.toEndofDay(end);
        
        List productionlist = productionService.getProductionList(date);
        
        for(Object o : productionlist){
            Production p = (Production)o;
            
            generalService.Delete(p);
        }
    }
}
