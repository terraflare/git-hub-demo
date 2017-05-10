/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.ReturnPurchase;
import entity.Item_In;
import entity.Item_In_Warehouse;
import entity.User;
import entity.Warehouse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import service.GeneralService;
import service.ReturnPurchaseService;
import service.ItemService;
import service.WarehouseService;
import tablemodel.ReturnPurchaseTableModel;
import util.DateUtil;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class ReturnPurchaseController {
    
    private GeneralService generalService;
    private ReturnPurchaseService returnpurchaseService;
    private ItemService itemService;
    private WarehouseService warehouseService;
    
    public ReturnPurchaseController(){
        generalService = new GeneralService();
        returnpurchaseService = new ReturnPurchaseService();
        itemService = new ItemService();
        warehouseService = new WarehouseService();
    }
    
    public ArrayList<ReturnPurchase> getReturnPurchaseList(){
        return(ArrayList<ReturnPurchase>)returnpurchaseService.getReturnPurchaseList(GlobalFields.USER);
    }
    
    public ReturnPurchase getReturnPurchase(int id){
        return returnpurchaseService.getReturnPurchase(id);
    }
    
    public boolean addReturnPurchase(ReturnPurchaseTableModel iptm, Date date, User user, 
            int quantity, int itemid, String warehouse, String note){
        
        Item_In item = itemService.getItemIn(itemid);
        Warehouse w = warehouseService.getWarehouse(warehouse);
        
        Item_In_Warehouse iiw = itemService.getItem_In_Warehouse(item, w);
        
        if(iiw.getStock()-quantity < 0){
            return GlobalFields.FAIL;
        }
        else{
            ReturnPurchase is = new ReturnPurchase();
            is.setDate(date);
            is.setItem(item);
            is.setNote(note);
            is.setQuantity(quantity);
            is.setUser(user);
            is.setWarehouse(w);
            is.setStatus(GlobalFields.UNCHECKED);

            generalService.Persist(is);

            iiw.setStock(iiw.getStock()-quantity);

            generalService.Update(iiw);

            iptm.addRow(is);

            return GlobalFields.SUCCESS;
        }
    }
    
    public boolean deleteReturnPurchase(int id){
        ReturnPurchase is = returnpurchaseService.getReturnPurchase(id);
        
        Item_In_Warehouse iw = itemService.getItem_In_Warehouse(is.getItem(), is.getWarehouse());
        
        iw.setStock(iw.getStock() + (int)is.getQuantity());
        
        try{
            generalService.Update(iw);

            generalService.Delete(is);
            
            return GlobalFields.SUCCESS;
        }
        catch(Exception e){
            System.out.println(e);
            return GlobalFields.FAIL;
        }
    }
    
    public void deleteAllReturnPurchase(Date end){
       
        Date date = DateUtil.toEndofDay(end);
        
        List returnpurchaselist = returnpurchaseService.getReturnPurchaseList(date);
        
        for(Object o : returnpurchaselist){
            ReturnPurchase p = (ReturnPurchase)o;
            
            generalService.Delete(p);
        }
    }
}
