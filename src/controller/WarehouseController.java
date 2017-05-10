/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import entity.AdjustmentCn;
import entity.AdjustmentIn;
import entity.AdjustmentOut;
import entity.AdjustmentPlastic;
import entity.Cn;
import entity.Item_In;
import entity.Item_In_Warehouse;
import entity.Item_Out;
import entity.Item_Out_Warehouse;
import entity.Plastic;
import entity.Plastic_Warehouse;
import entity.User;
import entity.Warehouse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import service.AdjustmentService;
import service.GeneralService;
import service.ItemService;
import service.WarehouseService;
import tablemodel.WarehouseTableModel;
import util.DateUtil;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class WarehouseController {
    
    private WarehouseService warehouseService;
    private GeneralService generalService;
    private ItemService itemService;
    private AdjustmentService adjustmentService;
    
    public WarehouseController(){
        warehouseService = new WarehouseService();
        generalService = new GeneralService();
        itemService = new ItemService();
        adjustmentService = new AdjustmentService();
    }
    
    public ArrayList<Warehouse> getWarehouseList(){
        return(ArrayList<Warehouse>)warehouseService.getWarehouseList();
    }
    
    public Object [] getWarehouseNameList(){
        List ls = warehouseService.getWarehouseNameList();
        return ls.toArray();
    }
    
    public Object [] getWarehouseNameList(User user){
        List ls = warehouseService.getWarehouseNameList();
        
        Set<Warehouse> set = user.getWarehouses();
        
        ArrayList newls = new ArrayList();
        
        for(Object o : ls){
            String w = String.valueOf(o);
            
            for(Warehouse ware : set){
                if(ware.getName().equals(w) && ware.isActive() != GlobalFields.INACTIVE){
                    newls.add(w);
                }
            }
        }
        
        return newls.toArray();
    }
    
    public void addWarehouse(WarehouseTableModel wtm, String name, String note){
        Warehouse warehouse = new Warehouse();
        warehouse.setName(name);
        warehouse.setNote(note);
        warehouse.setActive(GlobalFields.ACTIVE);
        
        generalService.Persist(warehouse);
        
        List iteminlist = itemService.getItemInList();
        List itemoutlist = itemService.getItemOutList();
        
        for(Object o : iteminlist){
            Item_In item = (Item_In)o;
            
            Item_In_Warehouse iw = new Item_In_Warehouse();
            iw.setItem(item);
            iw.setWarehouse(warehouse);
            iw.setStock(0);
            
            generalService.Persist(iw);
        }
        
        for(Object o : itemoutlist){
            Item_Out item = (Item_Out)o;
            
            Item_Out_Warehouse iw = new Item_Out_Warehouse();
            iw.setItem(item);
            iw.setWarehouse(warehouse);
            iw.setStock(0);
            
            generalService.Persist(iw);
        }
        
        wtm.addRow(warehouse);
    }
    
    public void editWarehouse(WarehouseTableModel wtm, int id, String name, String note){
        Warehouse w = getWarehouse(id);
        w.setName(name);
        w.setNote(note);
        
        generalService.Merge(w);
        
        wtm.editRow(w);
    }
    
    public Warehouse getWarehouse(int id){
        return warehouseService.getWarehouse(id);
    }
    
    public Warehouse getWarehouse(String name){
        return warehouseService.getWarehouse(name);
    }
    
    public Item_In_Warehouse getItemInWarehouse(Warehouse warehouse, Item_In item){
        return warehouseService.getItem_In_Warehouse(warehouse, item);
    }
    
    public Item_Out_Warehouse getItemOutWarehouse(Warehouse warehouse, Item_Out item){
        return warehouseService.getItem_Out_Warehouse(warehouse, item);
    }
    
    public Plastic_Warehouse getPlasticWarehouse(Warehouse warehouse, Plastic plastic){
        return warehouseService.getPlastic_Warehouse(warehouse, plastic);
    }
    
    public void setItemInWarehouseStock(Warehouse warehouse, Item_In item, int stock, String note){
        
        Item_In_Warehouse iiw = warehouseService.getItem_In_Warehouse(warehouse, item);
        
        int oldstock = iiw.getStock();
        
        iiw.setStock(stock);
        generalService.Merge(iiw);
        
        AdjustmentIn ai = new AdjustmentIn();
        ai.setDate(new Date());
        ai.setItem(item);
        ai.setNote(note);
        ai.setQuantity(stock - oldstock);
        ai.setStatus(GlobalFields.UNCHECKED);
        ai.setUser(GlobalFields.USER);
        ai.setWarehouse(warehouse);
        generalService.Persist(ai);
    }
    
    public void setItemOutWarehouseStock(Warehouse warehouse, Item_Out item, float stock, String note){
        Item_Out_Warehouse iow = warehouseService.getItem_Out_Warehouse(warehouse, item);
        
        float oldstock = iow.getStock();
        
        iow.setStock(stock);
        generalService.Merge(iow);
        
        AdjustmentOut ao = new AdjustmentOut();
        ao.setDate(new Date());
        ao.setItem(item);
        ao.setNote(note);
        ao.setQuantity(stock-oldstock);
        ao.setStatus(GlobalFields.UNCHECKED);
        ao.setUser(GlobalFields.USER);
        ao.setWarehouse(warehouse);
        generalService.Persist(ao);
    }
    
    public void setPlasticWarehouseStock(Warehouse warehouse, Plastic plastic, int stock, String note){
        Plastic_Warehouse pw = warehouseService.getPlastic_Warehouse(warehouse, plastic);
        
        int oldstock = pw.getStock();
        
        pw.setStock(stock);
        generalService.Merge(pw);
        
        AdjustmentPlastic ap = new AdjustmentPlastic();
        ap.setDate(new Date());
        ap.setPlastic(plastic);
        ap.setNote(note);
        ap.setQuantity(stock-oldstock);
        ap.setStatus(GlobalFields.UNCHECKED);
        ap.setUser(GlobalFields.USER);
        ap.setWarehouse(warehouse);
        generalService.Persist(ap);
    }
    
    public void changeStatus(WarehouseTableModel utm, Warehouse warehouse){
        warehouse.setActive(!warehouse.isActive());
        generalService.Merge(warehouse);
        utm.editRow(warehouse);
    }
    
    public void deleteAllAdjustmentCn(Date end){
       
        Date date = DateUtil.toEndofDay(end);
        
        List adjustmentcnlist = adjustmentService.getAdjustmentCnList(date);
        
        for(Object o : adjustmentcnlist){
            AdjustmentCn p = (AdjustmentCn)o;
            
            generalService.Delete(p);
        }
    }
    
    public void deleteAllAdjustmentOut(Date end){
       
        Date date = DateUtil.toEndofDay(end);
        
        List adjustmentoutlist = adjustmentService.getAdjustmentOutList(date);
        
        for(Object o : adjustmentoutlist){
            AdjustmentOut p = (AdjustmentOut)o;
            
            generalService.Delete(p);
        }
    }
    
    public void deleteAllAdjustmentIn(Date end){
       
        Date date = DateUtil.toEndofDay(end);
        
        List adjustmentinlist = adjustmentService.getAdjustmentInList(date);
        
        for(Object o : adjustmentinlist){
            AdjustmentIn p = (AdjustmentIn)o;
            
            generalService.Delete(p);
        }
    }
}
