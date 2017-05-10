/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cn;
import entity.Item_In;
import entity.Item_In_Warehouse;
import entity.Production4;
import entity.User;
import entity.Warehouse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import service.CnService;
import service.GeneralService;
import service.Production4Service;
import service.WarehouseService;
import tablemodel.Production4TableModel;
import util.DateUtil;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class Production4Controller {
    
    private final Production4Service production4Service;
    private final GeneralService generalService;
    private final WarehouseService warehouseService;
    private CnService cnService;
    
    public Production4Controller(){
        production4Service = new Production4Service();
        generalService = new GeneralService();
        warehouseService = new WarehouseService();
        cnService = new CnService();
    }
    
    public Production4 getProduction4(int id){
        return production4Service.getProduction4(id);
    }
    
    public ArrayList<Production4> getProduction4List(){
        return(ArrayList<Production4>)production4Service.getProduction4List(GlobalFields.USER);
    }
    
    public Production4 addProduction4(User user, Date date, String warehouse, String note){
        
        Warehouse w = warehouseService.getWarehouse(warehouse);
        
        Cn cn = cnService.getCn(1);
        
        if(w == null){
            return null;
        }
        
        Production4 production4 = new Production4();
        production4.setUser(user);
        production4.setNote(note);
        production4.setDate(date);
        production4.setWarehouse(w);
        production4.setCn(cn);
        production4.setStatus(GlobalFields.UNCHECKED);
        
        generalService.Persist(production4);
        
        return production4;
    }
    
    public void editProduction4(Production4TableModel ptm, int id , Date date, String warehouse, String note){
        Warehouse ware = warehouseService.getWarehouse(warehouse);
        
        Production4 production4 = getProduction4(id);
        production4.setNote(note);
        production4.setDate(date);
        production4.setWarehouse(ware);
        
        generalService.Merge(production4);
        
        ptm.editRow(production4);
    }
    
    public boolean editProduction4(Production4 production4){
        
        Production4 oldproduction4 = production4Service.getProduction4(production4.getId());
        
        if(production4.getItemin() == null && production4.getOutquantity() != 0){
            return GlobalFields.FAIL;
        }
        
        try{
            Item_In olditemout = oldproduction4.getItemin();
            Item_In itemout = production4.getItemin();
            
            Item_In_Warehouse oldiow = warehouseService.getItem_In_Warehouse(oldproduction4.getWarehouse(), olditemout);
            Item_In_Warehouse iow = warehouseService.getItem_In_Warehouse(production4.getWarehouse(), itemout);
            
            Cn cn = production4.getCn();
            
            float oldinquantity = oldproduction4.getInquantity();
            float inquantity = production4.getInquantity();
            int oldoutquantity = oldproduction4.getOutquantity();
            int outquantity = production4.getOutquantity();
            
            if(olditemout.getId() == itemout.getId() && oldinquantity == inquantity &&  oldoutquantity == outquantity){
                //changing expected out quantity or no change
                generalService.Merge(production4);
                return GlobalFields.SUCCESS;
            }
            else if(oldinquantity == inquantity){
                //changing itemout and or out quantity
                if(olditemout.getId() == itemout.getId()){
                    int difference = outquantity - oldoutquantity;
                    
                    iow.setStock(iow.getStock() + difference);
                }
                else{
                    if(oldiow.getStock() - oldoutquantity < 0){
                        return GlobalFields.FAIL;
                    }
                    else{
                        oldiow.setStock(oldiow.getStock() - oldoutquantity);
                        iow.setStock(iow.getStock() + outquantity);
                    }
                }
            }
            else if(olditemout.getId() == itemout.getId() && oldoutquantity == outquantity){
                //changing cn quantity
                float difference = inquantity - oldinquantity;

                if(cn.getStock() - difference < 0){
                    return GlobalFields.FAIL;
                }
                else{
                    cn.setStock(cn.getStock() - difference);
                }
            }
            else{
                //changing everything
                
                float difference = inquantity - oldinquantity;
                    
                if(cn.getStock() - difference < 0){
                    return GlobalFields.FAIL;
                }
                else{
                    cn.setStock(cn.getStock() - difference);
                }
                    
                if(olditemout.getId() == itemout.getId()){
                    int difference2 = outquantity - oldoutquantity;
                    
                    if(iow.getStock() + difference2 < 0){
                        return GlobalFields.FAIL;
                    }
                    else{
                        iow.setStock(iow.getStock() + difference2);
                    }
                }
                else{
                    if(oldiow.getStock() - oldoutquantity < 0){
                        return GlobalFields.FAIL;
                    }
                    else{
                        oldiow.setStock(oldiow.getStock() - oldoutquantity);
                    }
                    
                    if(iow.getStock() + outquantity < 0){
                        return GlobalFields.FAIL;
                    }
                    else{
                        iow.setStock(iow.getStock() + outquantity);
                    }
                }
            }
            generalService.Merge(oldiow);
            generalService.Merge(iow);
            generalService.Merge(cn);
            generalService.Merge(production4);
            
            return GlobalFields.SUCCESS;
        }
        catch(Exception e){
            Item_In itemout = production4.getItemin();
            Cn cn = production4.getCn();
            
            Item_In_Warehouse iow = warehouseService.getItem_In_Warehouse(production4.getWarehouse(), itemout);
            
            float inquantity = production4.getInquantity();
            int outquantity = production4.getOutquantity();
            
            if(iow.getStock() + outquantity < 0){
                return GlobalFields.FAIL;
            }
            else{
                iow.setStock(iow.getStock() + outquantity);
            }
            
            if(cn.getStock() - inquantity < 0){
                return GlobalFields.FAIL;
            }
            else{
                cn.setStock(cn.getStock() - inquantity);
            }
            
            generalService.Merge(cn);
            generalService.Merge(iow);
            generalService.Merge(production4);

            return GlobalFields.SUCCESS;
        }
    }
    
    public boolean deleteProduction4(int id){
        Production4 production4 = getProduction4(id);
        
        if(production4.getItemin() == null){
            generalService.Delete(production4);
            return GlobalFields.SUCCESS;
        }
        
        try{
            Cn cn = production4.getCn();
            Item_In itemin = production4.getItemin();
            
            Item_In_Warehouse iiw = warehouseService.getItem_In_Warehouse(production4.getWarehouse(), itemin);
            
            if(cn.getStock() - production4.getInquantity() < 0){
                return GlobalFields.FAIL;
            }
            
            cn.setStock(cn.getStock() - production4.getInquantity());
            
            iiw.setStock(iiw.getStock() + production4.getOutquantity());
            
            generalService.Merge(cn);
            generalService.Merge(iiw);
            generalService.Delete(production4);
            return GlobalFields.SUCCESS;
        }
        catch(Exception e){
            System.out.println(e);
            return GlobalFields.FAIL;
        }
    }
    
    public void deleteAllProduction4(Date end){
       
        Date date = DateUtil.toEndofDay(end);
        
        List production4list = production4Service.getProduction4List(date);
        
        for(Object o : production4list){
            Production4 p = (Production4)o;
            
            generalService.Delete(p);
        }
    }
}
