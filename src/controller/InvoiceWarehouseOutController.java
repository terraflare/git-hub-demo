/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.InvoiceWarehouseOut;
import entity.Item_Out;
import entity.Item_Out_Warehouse;
import entity.User;
import entity.Warehouse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import service.GeneralService;
import service.InvoiceWarehouseService;
import service.ItemService;
import service.WarehouseService;
import tablemodel.InvoiceWarehouseOutTableModel;
import util.DateUtil;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class InvoiceWarehouseOutController {
    private GeneralService generalService;
    private ItemService itemService;
    private InvoiceWarehouseService invoicewarehouseService;
    private WarehouseService warehouseService;
    
    public InvoiceWarehouseOutController(){
        generalService = new GeneralService();
        invoicewarehouseService = new InvoiceWarehouseService();
        itemService = new ItemService();
        warehouseService = new WarehouseService();
    }
    
    public ArrayList<InvoiceWarehouseOut> getInvoiceWarehouseOutList(){
        return(ArrayList<InvoiceWarehouseOut>)invoicewarehouseService.getInvoiceWarehouseOutList(GlobalFields.USER);
    }
    
    public InvoiceWarehouseOut getInvoiceWarehouseOut(int id){
        return invoicewarehouseService.getInvoiceWarehouseOut(id);
    }
    
    public boolean addInvoiceWarehouseOut(InvoiceWarehouseOutTableModel iwotm, Date date, User user, 
            int sackquantity, float unitquantity, int itemid, String origin, String destination, String note){
        
        Item_Out item = itemService.getItemOut(itemid);
        Warehouse ori = warehouseService.getWarehouse(origin);
        Warehouse dest = warehouseService.getWarehouse(destination);
        
        Item_Out_Warehouse iw = itemService.getItem_Out_Warehouse(item, ori);
        Item_Out_Warehouse iw2 = itemService.getItem_Out_Warehouse(item, dest);
        
        if(ori.getId() == dest.getId()){
            return GlobalFields.FAIL;
        }
        
        float quantity = sackquantity * item.getSacksize() + unitquantity;
        
        if(iw.getStock()-quantity < 0){
            return GlobalFields.FAIL;
        }
        else{
            iw.setStock(iw.getStock()-quantity);
            iw2.setStock(iw2.getStock()+quantity);

            generalService.Update(iw);

            InvoiceWarehouseOut inw = new InvoiceWarehouseOut();
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

            iwotm.addRow(inw);

            return GlobalFields.SUCCESS;
        }
    }
    
    public boolean deleteInvoiceWarehouseOut(int id){
        InvoiceWarehouseOut iwi = invoicewarehouseService.getInvoiceWarehouseOut(id);
        
        Item_Out_Warehouse iwd = itemService.getItem_Out_Warehouse(iwi.getItem(), iwi.getDestination());
        Item_Out_Warehouse iwo = itemService.getItem_Out_Warehouse(iwi.getItem(), iwi.getWarehouse());
        
        if(iwd.getStock()-iwi.getQuantity() < 0){
            return GlobalFields.FAIL;
        }
        
        iwd.setStock(iwd.getStock() - iwi.getQuantity());
        iwo.setStock(iwo.getStock() + iwi.getQuantity());
        
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
    
    public void deleteAllInvoiceWarehouseOut(Date end){
       
        Date date = DateUtil.toEndofDay(end);
        
        List invoicewarehouseoutlist = invoicewarehouseService.getInvoiceWarehouseOutList(date);
        
        for(Object o : invoicewarehouseoutlist){
            InvoiceWarehouseOut p = (InvoiceWarehouseOut)o;
            
            generalService.Delete(p);
        }
    }
}
