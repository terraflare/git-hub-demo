/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.InvoiceSales;
import entity.Item_Out;
import entity.Item_Out_Warehouse;
import entity.User;
import entity.Warehouse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import service.GeneralService;
import service.InvoiceSalesService;
import service.ItemService;
import service.WarehouseService;
import tablemodel.InvoiceSalesTableModel;
import util.DateUtil;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class InvoiceSalesController {
    private GeneralService generalService;
    private InvoiceSalesService invoicesalesService;
    private ItemService itemService;
    private WarehouseService warehouseService;
    
    public InvoiceSalesController(){
        generalService = new GeneralService();
        invoicesalesService = new InvoiceSalesService();
        itemService = new ItemService();
        warehouseService = new WarehouseService();
    }
    
    public ArrayList<InvoiceSales> getInvoiceSalesList(){
        return(ArrayList<InvoiceSales>)invoicesalesService.getInvoiceSalesList(GlobalFields.USER);
    }
    
    public InvoiceSales getInvoiceSales(int id){
        return invoicesalesService.getInvoiceSales(id);
    }
    
    public boolean addInvoiceSales(InvoiceSalesTableModel istm, Date date, User user, 
            int sackquantity, float unitquantity,  int itemid, String warehouse, String note){
        
        Item_Out item = itemService.getItemOut(itemid);
        Warehouse w = warehouseService.getWarehouse(warehouse);
        
        Item_Out_Warehouse iow = itemService.getItem_Out_Warehouse(item, w);
        
        float quantity = sackquantity * item.getSacksize() + unitquantity;
        
        if(iow.getStock()-quantity < 0){
            return GlobalFields.FAIL;
        }
        else{
            iow.setStock(iow.getStock()-quantity);

            generalService.Update(iow);

            InvoiceSales is = new InvoiceSales();
            is.setDate(date);
            is.setItem(item);
            is.setNote(note);
            is.setQuantity(quantity);
            is.setUser(user);
            is.setWarehouse(w);
            is.setStatus(GlobalFields.UNCHECKED);

            generalService.Persist(is);

            generalService.Update(iow);

            istm.addRow(is);

            return GlobalFields.SUCCESS;
        }
    }
    
    public boolean deleteInvoiceSales(int id){
        InvoiceSales is = invoicesalesService.getInvoiceSales(id);
        
        Item_Out_Warehouse iw = itemService.getItem_Out_Warehouse(is.getItem(), is.getWarehouse());
        
        iw.setStock(iw.getStock() + is.getQuantity());
        
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
    
    public void deleteAllInvoiceSales(Date end){
       
        Date date = DateUtil.toEndofDay(end);
        
        List invoicesaleslist = invoicesalesService.getInvoiceSalesList(date);
        
        for(Object o : invoicesaleslist){
            InvoiceSales p = (InvoiceSales)o;
            
            generalService.Delete(p);
        }
    }
}