/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Item_Out;
import entity.Item_Out_Warehouse;
import entity.Production2;
import entity.User;
import entity.Warehouse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import service.GeneralService;
import service.Production2Service;
import service.WarehouseService;
import tablemodel.Production2TableModel;
import util.DateUtil;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class Production2Controller {
    private final Production2Service production2Service;
    private final GeneralService generalService;
    private final WarehouseService warehouseService;
    
    public Production2Controller(){
        production2Service = new Production2Service();
        generalService = new GeneralService();
        warehouseService = new WarehouseService();
    }
    
    public Production2 getProduction2(int id){
        return production2Service.getProduction2(id);
    }
    
    public ArrayList<Production2> getProduction2List(){
        return(ArrayList<Production2>)production2Service.getProduction2List(GlobalFields.USER);
    }
    
    public Production2 addProduction2( User user, Date date, String warehouse, String note){
        
        Warehouse w = warehouseService.getWarehouse(warehouse);
        
        if(w == null){
            return null;
        }
        
        Production2 production2 = new Production2();
        production2.setUser(user);
        production2.setNote(note);
        production2.setDate(date);
        production2.setWarehouse(w);
        production2.setStatus(GlobalFields.UNCHECKED);
        
        generalService.Persist(production2);
        
        return production2;
    }
    
    public void editProduction2(Production2TableModel ptm, int id , Date date, String warehouse, String note){
        Warehouse ware = warehouseService.getWarehouse(warehouse);
        
        Production2 production2 = getProduction2(id);
        production2.setNote(note);
        production2.setDate(date);
        production2.setWarehouse(ware);
        
        generalService.Merge(production2);
        
        ptm.editRow(production2);
    }
    
    public boolean editProduction2(Production2 production2){
        Production2 oldproduction2 = production2Service.getProduction2(production2.getId());
        
        if(production2.getItemin() == null && production2.getInquantity() != 0){
            return GlobalFields.FAIL;
        }
        
        if(production2.getItemout() == null && production2.getOutquantity() != 0){
            return GlobalFields.FAIL;
        }
        
        try{
            Item_Out olditemout = oldproduction2.getItemout();
            Item_Out itemout = production2.getItemout();
            Item_Out olditemin = oldproduction2.getItemin();
            Item_Out itemin = production2.getItemin();
            
            Item_Out_Warehouse oldiow = warehouseService.getItem_Out_Warehouse(oldproduction2.getWarehouse(), olditemout);
            Item_Out_Warehouse oldiiw = warehouseService.getItem_Out_Warehouse(oldproduction2.getWarehouse(), olditemin);
            Item_Out_Warehouse iiw = warehouseService.getItem_Out_Warehouse(production2.getWarehouse(), itemin);
            Item_Out_Warehouse iow = warehouseService.getItem_Out_Warehouse(production2.getWarehouse(), itemout);
            
            float oldinquantity = oldproduction2.getInquantity();
            float inquantity = production2.getInquantity();
            float oldoutquantity = oldproduction2.getOutquantity();
            float outquantity = production2.getOutquantity();
            
            if(olditemin.getId() == itemin.getId() && oldinquantity == inquantity && olditemout.getId() == itemout.getId() && oldoutquantity == outquantity){
                //no change
                return GlobalFields.SUCCESS;
            }
            else if(olditemout.getId() == itemout.getId() && oldoutquantity == outquantity){
                //changing itemin and or in quantity
                if(olditemin.getId() == itemin.getId()){
                    float difference = inquantity - oldinquantity;
                    
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
                        iiw.setStock(iiw.getStock() - inquantity);
                    }
                }         
            }
            else if(olditemin.getId() == itemin.getId() && oldinquantity == inquantity){
                //changing itemout and or out quantity
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
                    
                    float difference2 = inquantity - oldinquantity;
                    
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
                        iiw.setStock(iiw.getStock() - production2.getInquantity());
                    }
                }
                else if(olditemin.getId() == itemin.getId()){
                    float difference2 = inquantity - oldinquantity;
                    
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
                        iiw.setStock(iiw.getStock() - production2.getInquantity());
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
            generalService.Merge(production2);

            return GlobalFields.SUCCESS;
            
        }
        catch(Exception e){
            Item_Out itemout = production2.getItemout();
            Item_Out itemin = production2.getItemin();
            
            Item_Out_Warehouse iiw = warehouseService.getItem_Out_Warehouse(production2.getWarehouse(), itemin);
            Item_Out_Warehouse iow = warehouseService.getItem_Out_Warehouse(production2.getWarehouse(), itemout);
            
            float inquantity = production2.getInquantity();
            float outquantity = production2.getOutquantity();
            
            if(iiw.getStock() - inquantity < 0){
                return GlobalFields.FAIL;
            }
            else{
                iiw.setStock(iiw.getStock() - production2.getInquantity());
            }
            
            iow.setStock(iow.getStock() + outquantity);
            
            generalService.Merge(iiw);
            generalService.Merge(iow);
            generalService.Merge(production2);

            return GlobalFields.SUCCESS;
        }
    }
    
    public boolean deleteProduction2(int id){
        Production2 production2 = getProduction2(id);
        
        if(production2.getItemin() == null && production2.getItemout() == null){
            generalService.Delete(production2);
            return GlobalFields.SUCCESS;
        }
        
        try{
            Item_Out itemin = production2.getItemin();
            Item_Out itemout = production2.getItemout();
            
            Item_Out_Warehouse iiw = warehouseService.getItem_Out_Warehouse(production2.getWarehouse(), itemin);
            Item_Out_Warehouse iow = warehouseService.getItem_Out_Warehouse(production2.getWarehouse(), itemout);
            
            if(iow.getStock() - production2.getOutquantity() < 0){
                return GlobalFields.FAIL;
            }
            
            iiw.setStock(iiw.getStock() + production2.getInquantity());
            
            iow.setStock(iow.getStock() - production2.getOutquantity());
            
            generalService.Merge(iiw);
            generalService.Merge(iow);
            generalService.Delete(production2);
            return GlobalFields.SUCCESS;
        }
        catch(Exception e){
            System.out.println(e);
            return GlobalFields.FAIL;
        }
    }
    
    public void deleteAllProduction2(Date end){
        
        Date date = DateUtil.toEndofDay(end);
        
        List production2list = production2Service.getProduction2List(date);
        
        for(Object o : production2list){
            Production2 p = (Production2)o;
            
            generalService.Delete(p);
        }
    }
}
