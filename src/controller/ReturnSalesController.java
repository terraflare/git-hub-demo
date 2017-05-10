/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Cn;
import entity.ItemExchange;
import entity.Item_Out;
import entity.Item_Out_Warehouse;
import entity.Production3;
import entity.ReturnSales;
import entity.User;
import entity.Warehouse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import service.CnService;
import service.GeneralService;
import service.ItemService;
import service.ReturnSalesService;
import service.WarehouseService;
import tablemodel.ReturnSalesTableModel;
import util.DateUtil;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class ReturnSalesController {
    private GeneralService generalService;
    private ReturnSalesService returnsalesService;
    private ItemService itemService;
    private WarehouseService warehouseService;
    private CnService cnService;
    
    public ReturnSalesController(){
        generalService = new GeneralService();
        returnsalesService = new ReturnSalesService();
        itemService = new ItemService();
        warehouseService = new WarehouseService();
        cnService = new CnService();
    }
    
    public ArrayList<ReturnSales> getReturnSalesList(){
        return(ArrayList<ReturnSales>)returnsalesService.getReturnSalesList(GlobalFields.USER);
    }
    
    public ReturnSales getReturnSales(int id){
        return returnsalesService.getReturnSales(id);
    }
    
    public void addReturnSales(ReturnSalesTableModel istm, Date date, User user, 
            int sackquantity, float unitquantity,  int itemid, String warehouse, String note,
            boolean ie, int exquantity, int inquantity){
        
        Item_Out item = itemService.getItemOut(itemid);
        Warehouse w = warehouseService.getWarehouse(warehouse);
        Cn cn = cnService.getCn(1);
        
        float quantity = sackquantity * item.getSacksize() + unitquantity;
        
        ReturnSales is = new ReturnSales();
        is.setDate(date);
        is.setItem(item);
        is.setNote(note);
        is.setQuantity(quantity);
        is.setUser(user);
        is.setWarehouse(w);
        is.setStatus(GlobalFields.UNCHECKED);

        generalService.Persist(is);

        Production3 production3 = new Production3();
        production3.setWarehouse(w);
        production3.setUser(user);
        production3.setStatus(GlobalFields.UNCHECKED);
        production3.setOutquantity(inquantity);
        production3.setNote("");
        production3.setItemout(item);
        production3.setInquantity(quantity);
        production3.setDifference(exquantity);
        production3.setDate(new Date());
        production3.setCn(cn);
        
        generalService.Persist(production3);
        
        cn.setStock(cn.getStock() + inquantity);
        generalService.Merge(cn);
        
        istm.addRow(is);
        
        if(ie == true){
            ItemExchange itemexchange = new ItemExchange();
            itemexchange.setDate(new Date());
            itemexchange.setUser(user);
            itemexchange.setReturnsales(is);
            generalService.Merge(itemexchange);
        }   
    }
    
    public void addReturnSales(ReturnSalesTableModel istm, Date date, User user, 
            int sackquantity, float unitquantity,  int itemid, String warehouse, String note, boolean ie){
        
        Item_Out item = itemService.getItemOut(itemid);
        Warehouse w = warehouseService.getWarehouse(warehouse);
        
        Item_Out_Warehouse iow = itemService.getItem_Out_Warehouse(item, w);
        
        float quantity = sackquantity * item.getSacksize() + unitquantity;
        
        iow.setStock(iow.getStock()+quantity);

        generalService.Update(iow);

        ReturnSales is = new ReturnSales();
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
        
        if(ie == true){
            ItemExchange itemexchange = new ItemExchange();
            itemexchange.setDate(new Date());
            itemexchange.setUser(user);
            itemexchange.setReturnsales(is);
            generalService.Merge(itemexchange);
        }
            
    }
    
    public boolean deleteReturnSales(int id){
        ReturnSales is = returnsalesService.getReturnSales(id);
        
        Item_Out_Warehouse iw = itemService.getItem_Out_Warehouse(is.getItem(), is.getWarehouse());
        
        if(iw.getStock()-is.getQuantity() < 0){
            return GlobalFields.FAIL;
        }
        
        iw.setStock(iw.getStock() - is.getQuantity());
        
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
    
    public void deleteAllReturnSales(Date end){
       
        Date date = DateUtil.toEndofDay(end);
        
        List returnsaleslist = returnsalesService.getReturnSalesList(date);
        
        for(Object o : returnsaleslist){
            ReturnSales p = (ReturnSales)o;
            
            generalService.Delete(p);
        }
    }
}
