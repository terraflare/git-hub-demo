/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.InvoiceWarehouseIn;
import entity.Item_In;
import entity.Item_In_Warehouse;
import entity.User;
import entity.Warehouse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import service.GeneralService;
import service.InvoiceWarehouseService;
import service.ItemService;
import service.WarehouseService;
import tablemodel.InvoiceWarehouseInTableModel;
import util.DateUtil;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class InvoiceWarehouseInController {
    private GeneralService generalService;
    private ItemService itemService;
    private InvoiceWarehouseService invoicewarehouseService;
    private WarehouseService warehouseService;
    
    public InvoiceWarehouseInController(){
        generalService = new GeneralService();
        invoicewarehouseService = new InvoiceWarehouseService();
        itemService = new ItemService();
        warehouseService = new WarehouseService();
    }
    
    public ArrayList<InvoiceWarehouseIn> getInvoiceWarehouseInList(){
        return(ArrayList<InvoiceWarehouseIn>)invoicewarehouseService.getInvoiceWarehouseInList(GlobalFields.USER);
    }
    
    public InvoiceWarehouseIn getInvoiceWarehouseIn(int id){
        return invoicewarehouseService.getInvoiceWarehouseIn(id);
    }
    
    public boolean addInvoiceWarehouseIn(InvoiceWarehouseInTableModel iwitm, Date date, User user, 
            int quantity, int itemid, String origin, String destination, String note){
        
        Item_In item = itemService.getItemIn(itemid);
        Warehouse ori = warehouseService.getWarehouse(origin);
        Warehouse dest = warehouseService.getWarehouse(destination);
        
        Item_In_Warehouse iw = itemService.getItem_In_Warehouse(item, ori);
        Item_In_Warehouse iw2 = itemService.getItem_In_Warehouse(item, dest);
        
        if(iw.getStock()-quantity < 0){
            return GlobalFields.FAIL;
        }
        else{
            iw.setStock(iw.getStock()-quantity);
            iw2.setStock(iw2.getStock()+quantity);

            generalService.Update(iw);

            InvoiceWarehouseIn inw = new InvoiceWarehouseIn();
            inw.setDate(date);
            inw.setItem(item);
            inw.setNote(note);
            inw.setQuantity(quantity);
            inw.setUser(user);
            inw.setWarehouse(ori);
            inw.setDestination(dest);
            inw.setStatus(GlobalFields.UNCHECKED);

            generalService.Persist(inw);

            generalService.Update(iw);
            generalService.Update(iw2);

            iwitm.addRow(inw);

            return GlobalFields.SUCCESS;
        }
    }
    
    public boolean deleteInvoiceWarehouseIn(int id){
        InvoiceWarehouseIn iwi = invoicewarehouseService.getInvoiceWarehouseIn(id);
        
        Item_In_Warehouse iwd = itemService.getItem_In_Warehouse(iwi.getItem(), iwi.getDestination());
        Item_In_Warehouse iwo = itemService.getItem_In_Warehouse(iwi.getItem(), iwi.getWarehouse());
        
        if(iwd.getStock()-iwi.getQuantity() < 0){
            return GlobalFields.FAIL;
        }
        
        iwd.setStock(iwd.getStock() - (int)iwi.getQuantity());
        iwo.setStock(iwo.getStock() + (int)iwi.getQuantity());
        
        try{
            generalService.Update(iwd);
            generalService.Update(iwo);

            generalService.Delete(iwi);
            
            return GlobalFields.SUCCESS;
        }
        catch(Exception e){
            System.out.println(e);
            return GlobalFields.FAIL;
        }
    }
    
    public void deleteAllInvoiceWarehouseIn(Date end){
       
        Date date = DateUtil.toEndofDay(end);
        
        List invoicewarehouseinlist = invoicewarehouseService.getInvoiceWarehouseInList(date);
        
        for(Object o : invoicewarehouseinlist){
            InvoiceWarehouseIn p = (InvoiceWarehouseIn)o;
            
            generalService.Delete(p);
        }
    }
}
