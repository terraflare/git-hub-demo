/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cn;
import entity.Item_Out;
import entity.Item_Out_Warehouse;
import entity.Production3;
import entity.User;
import entity.Warehouse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import service.CnService;
import service.GeneralService;
import service.Production3Service;
import service.WarehouseService;
import tablemodel.Production3TableModel;
import util.DateUtil;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class Production3Controller {
    private final Production3Service production3Service;
    private final GeneralService generalService;
    private final WarehouseService warehouseService;
    private CnService cnService;
    
    public Production3Controller(){
        production3Service = new Production3Service();
        generalService = new GeneralService();
        warehouseService = new WarehouseService();
        cnService = new CnService();
    }
    
    public Production3 getProduction3(int id){
        return production3Service.getProduction3(id);
    }
    
    public ArrayList<Production3> getProduction3List(){
        return(ArrayList<Production3>)production3Service.getProduction3List(GlobalFields.USER);
    }
    
    public Production3 addProduction3(User user, Date date, String warehouse, String note){
        
        Warehouse w = warehouseService.getWarehouse(warehouse);
        
        Cn cn = cnService.getCn(1);
        
        if(w == null){
            return null;
        }
        
        Production3 production3 = new Production3();
        production3.setUser(user);
        production3.setNote(note);
        production3.setDate(date);
        production3.setWarehouse(w);
        production3.setCn(cn);
        production3.setStatus(GlobalFields.UNCHECKED);
        
        generalService.Persist(production3);
        
        return production3;
    }
    
    public void editProduction3(Production3TableModel ptm, int id , Date date, String warehouse, String note){
        Warehouse ware = warehouseService.getWarehouse(warehouse);
        
        Production3 production3 = getProduction3(id);
        production3.setNote(note);
        production3.setDate(date);
        production3.setWarehouse(ware);
        
        generalService.Merge(production3);
        
        ptm.editRow(production3);
    }
    
    public boolean editProduction3(Production3 production3){
        Production3 oldproduction3 = production3Service.getProduction3(production3.getId());
        
        if(production3.getItemout() == null && production3.getInquantity() != 0){
            return GlobalFields.FAIL;
        }
        
        try{
            Item_Out olditemout = oldproduction3.getItemout();
            Item_Out itemout = production3.getItemout();
            
            Item_Out_Warehouse oldiow = warehouseService.getItem_Out_Warehouse(oldproduction3.getWarehouse(), olditemout);
            Item_Out_Warehouse iow = warehouseService.getItem_Out_Warehouse(production3.getWarehouse(), itemout);
            
            Cn cn = production3.getCn();
            
            float oldinquantity = oldproduction3.getInquantity();
            float inquantity = production3.getInquantity();
            float oldoutquantity = oldproduction3.getOutquantity();
            float outquantity = production3.getOutquantity();
            
            if(olditemout.getId() == itemout.getId() && oldinquantity == inquantity &&  oldoutquantity == outquantity){
                //changing expected out quantity or no change
                generalService.Merge(production3);
                return GlobalFields.SUCCESS;
            }
            else if(oldoutquantity == outquantity){
                //changing itemin and or in quantity
                if(olditemout.getId() == itemout.getId()){
                    float difference = inquantity - oldinquantity;
                    
                    if(iow.getStock() - difference < 0){
                        return GlobalFields.FAIL;
                    }
                    else{
                        iow.setStock(iow.getStock() - difference);
                    }
                }
                else{
                    oldiow.setStock(oldiow.getStock() + oldinquantity);
                    if(iow.getStock() - inquantity < 0){
                        return GlobalFields.FAIL;
                    }
                    else{
                        iow.setStock(iow.getStock() - inquantity);
                    }
                }
            }
            else if(olditemout.getId() == itemout.getId() && oldinquantity == inquantity){
                //changing cn quantity
                float difference = outquantity - oldoutquantity;

                if(cn.getStock() + difference < 0){
                    return GlobalFields.FAIL;
                }
                else{
                    cn.setStock(cn.getStock() + difference);
                }
            }
            else{
                //changing everything
                
                float difference = outquantity - oldoutquantity;
                    
                if(cn.getStock() + difference < 0){
                    return GlobalFields.FAIL;
                }
                else{
                    cn.setStock(cn.getStock() + difference);
                }
                    
                if(olditemout.getId() == itemout.getId()){
                    float difference2 = inquantity - oldinquantity;
                    
                    if(iow.getStock() - difference2 < 0){
                        return GlobalFields.FAIL;
                    }
                    else{
                        iow.setStock(iow.getStock() - difference2);
                    }
                }
                else{
                    oldiow.setStock(oldiow.getStock() + oldinquantity);
                    if(iow.getStock() - inquantity < 0){
                        return GlobalFields.FAIL;
                    }
                    else{
                        iow.setStock(iow.getStock() - inquantity);
                    }
                }
            }
            
            generalService.Merge(oldiow);
            generalService.Merge(iow);
            generalService.Merge(cn);
            generalService.Merge(production3);
            
            return GlobalFields.SUCCESS;
        }
        catch(Exception e){
            Item_Out itemout = production3.getItemout();
            Cn cn = production3.getCn();
            
            Item_Out_Warehouse iow = warehouseService.getItem_Out_Warehouse(production3.getWarehouse(), itemout);
            
            float inquantity = production3.getInquantity();
            float outquantity = production3.getOutquantity();
            
            if(iow.getStock() - inquantity < 0){
                return GlobalFields.FAIL;
            }
            else{
                iow.setStock(iow.getStock() - production3.getInquantity());
            }
            
            cn.setStock(cn.getStock() + outquantity);
            
            generalService.Merge(cn);
            generalService.Merge(iow);
            generalService.Merge(production3);

            return GlobalFields.SUCCESS;
        }
    }
    
    public boolean deleteProduction3(int id){
        Production3 production3 = getProduction3(id);
        
        if(production3.getItemout() == null){
            generalService.Delete(production3);
            return GlobalFields.SUCCESS;
        }
        
        try{
            Cn cn = production3.getCn();
            Item_Out itemout = production3.getItemout();
            
            Item_Out_Warehouse iow = warehouseService.getItem_Out_Warehouse(production3.getWarehouse(), itemout);
            
            if(iow.getStock() - production3.getInquantity() < 0){
                return GlobalFields.FAIL;
            }
            
            cn.setStock(cn.getStock() + production3.getOutquantity());
            
            iow.setStock(iow.getStock() - production3.getInquantity());
            
            generalService.Merge(cn);
            generalService.Merge(iow);
            generalService.Delete(production3);
            return GlobalFields.SUCCESS;
        }
        catch(Exception e){
            System.out.println(e);
            return GlobalFields.FAIL;
        }
    }
    
    public void deleteAllProduction3(Date end){
       
        Date date = DateUtil.toEndofDay(end);
        
        List production3list = production3Service.getProduction3List(date);
        
        for(Object o : production3list){
            Production3 p = (Production3)o;
            
            generalService.Delete(p);
        }
    }
}
