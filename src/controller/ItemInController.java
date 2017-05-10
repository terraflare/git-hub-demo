/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import entity.Item_In;
import entity.Item_In_Warehouse;
import entity.Warehouse;
import java.util.ArrayList;
import java.util.List;
import service.GeneralService;
import service.ItemService;
import service.QualityService;
import service.WarehouseService;
import tablemodel.ItemInTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class ItemInController {
    private ItemService itemService;
    private GeneralService generalService;
    private QualityService qualityService;
    private WarehouseService warehouseService;
    
    public ItemInController(){
        itemService = new ItemService();
        generalService = new GeneralService();
        qualityService = new QualityService();
        warehouseService = new WarehouseService();
    }
    
    public ArrayList<Item_In> getItemList(){
        return(ArrayList<Item_In>)itemService.getItemInList();
    }
    
    public ArrayList<Item_In> getActiveItemList(){
        return (ArrayList<Item_In>)itemService.getActiveItemInList();
    }
    
    public Item_In getItem(int id){
        return itemService.getItemIn(id);
    }
    
    public void editItem(ItemInTableModel iitm, int id, String name, String quality, String note){
        Item_In item = getItem(id);
        item.setName(name);
        item.setQuality(qualityService.getQuality(quality));
        item.setNote(note);
        
        generalService.Merge(item);
        
        iitm.editRow(item);
    }
    
    public void addItem(ItemInTableModel iitm, String name, String quality, String note){
        Item_In item = new Item_In();
        item.setName(name);
        item.setQuality(qualityService.getQuality(quality));
        item.setNote(note);
        item.setActive(GlobalFields.ACTIVE);
        
        generalService.Persist(item);
        
        List warehouselist = warehouseService.getWarehouseList();
        
        for(Object o : warehouselist){
            Warehouse warehouse = (Warehouse)o;
            
            Item_In_Warehouse iiw = new Item_In_Warehouse();
            iiw.setItem(item);
            iiw.setWarehouse(warehouse);
            iiw.setStock(0);
            
            generalService.Persist(iiw);
        }
        
        iitm.addRow(item);
    }
    
    public void changeStatus(ItemInTableModel iitm, Item_In item){
        item.setActive(!item.isActive());
        generalService.Merge(item);
        iitm.editRow(item);
    }
}
