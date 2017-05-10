/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.InvoicePurchase;
import entity.Item_In;
import entity.Item_In_Warehouse;
import entity.User;
import entity.Warehouse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import service.GeneralService;
import service.InvoicePurchaseService;
import service.ItemService;
import service.WarehouseService;
import tablemodel.InvoicePurchaseTableModel;
import util.DateUtil;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class InvoicePurchaseController {
    
    private GeneralService generalService;
    private InvoicePurchaseService invoicepurchaseService;
    private ItemService itemService;
    private WarehouseService warehouseService;
    
    public InvoicePurchaseController(){
        generalService = new GeneralService();
        invoicepurchaseService = new InvoicePurchaseService();
        itemService = new ItemService();
        warehouseService = new WarehouseService();
    }
    
    public ArrayList<InvoicePurchase> getInvoicePurchaseList(){
        return(ArrayList<InvoicePurchase>)invoicepurchaseService.getInvoicePurchaseList(GlobalFields.USER);
    }
    
    public InvoicePurchase getInvoicePurchase(int id){
        return invoicepurchaseService.getInvoicePurchase(id);
    }
    
    public boolean addInvoicePurchase(InvoicePurchaseTableModel iptm, Date date, User user, 
            int quantity, int itemid, String warehouse, String note){
        
        Item_In item = itemService.getItemIn(itemid);
        Warehouse w = warehouseService.getWarehouse(warehouse);
        
        if( w == null){
            return GlobalFields.FAIL;
        }
        
        InvoicePurchase is = new InvoicePurchase();
        is.setDate(date);
        is.setItem(item);
        is.setNote(note);
        is.setQuantity(quantity);
        is.setUser(user);
        is.setWarehouse(w);
        is.setStatus(GlobalFields.UNCHECKED);
        
        generalService.Persist(is);
        
        Item_In_Warehouse iiw = itemService.getItem_In_Warehouse(item, w);
        iiw.setStock(iiw.getStock()+quantity);
        
        generalService.Update(iiw);
        
        iptm.addRow(is);
        
        return GlobalFields.SUCCESS;
    }
    
    public boolean deleteInvoicePurchase(int id){
        InvoicePurchase is = invoicepurchaseService.getInvoicePurchase(id);
        
        Item_In_Warehouse iw = itemService.getItem_In_Warehouse(is.getItem(), is.getWarehouse());
        
        if(iw.getStock()-is.getQuantity() < 0){
            return GlobalFields.FAIL;
        }
        
        iw.setStock(iw.getStock() - (int)is.getQuantity());
        
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
    
    public void deleteAllInvoicePurchase(Date end){
       
        Date date = DateUtil.toEndofDay(end);
        
        List invoicepurchaselist = invoicepurchaseService.getInvoicePurchaseList(date);
        
        for(Object o : invoicepurchaselist){
            InvoicePurchase p = (InvoicePurchase)o;
            
            generalService.Delete(p);
        }
    }
}
