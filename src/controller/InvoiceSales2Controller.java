/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.InvoiceSales2;
import entity.Item_In;
import entity.Item_In_Warehouse;
import entity.User;
import entity.Warehouse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import service.GeneralService;
import service.InvoiceSales2Service;
import service.ItemService;
import service.WarehouseService;
import tablemodel.InvoiceSales2TableModel;
import util.DateUtil;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class InvoiceSales2Controller {
    private GeneralService generalService;
    private InvoiceSales2Service invoicesales2Service;
    private ItemService itemService;
    private WarehouseService warehouseService;
    
    public InvoiceSales2Controller(){
        generalService = new GeneralService();
        invoicesales2Service = new InvoiceSales2Service();
        itemService = new ItemService();
        warehouseService = new WarehouseService();
    }
    
    public ArrayList<InvoiceSales2> getInvoiceSales2List(){
        return(ArrayList<InvoiceSales2>)invoicesales2Service.getInvoiceSales2List(GlobalFields.USER);
    }
    
    public InvoiceSales2 getInvoiceSales2(int id){
        return invoicesales2Service.getInvoiceSales2(id);
    }
    
    public boolean addInvoiceSales2(InvoiceSales2TableModel iptm, Date date, User user, 
            int quantity, int itemid, String warehouse, String note){
        
        Item_In item = itemService.getItemIn(itemid);
        Warehouse w = warehouseService.getWarehouse(warehouse);
        
        Item_In_Warehouse iiw = itemService.getItem_In_Warehouse(item, w);
        
        if(iiw.getStock()-quantity < 0){
            return GlobalFields.FAIL;
        }
        else{
            InvoiceSales2 is = new InvoiceSales2();
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
    
    public boolean deleteInvoiceSales2(int id){
        InvoiceSales2 is = invoicesales2Service.getInvoiceSales2(id);
        
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
    
    public void deleteAllInvoiceSales2(Date end){
       
        Date date = DateUtil.toEndofDay(end);
        
        List invoicesales2list = invoicesales2Service.getInvoiceSales2List(date);
        
        for(Object o : invoicesales2list){
            InvoiceSales2 p = (InvoiceSales2)o;
            
            generalService.Delete(p);
        }
    }
}
