/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import entity.Item_Out;
import entity.Item_Out_Warehouse;
import entity.Plastic;
import entity.Warehouse;
import java.util.ArrayList;
import java.util.List;
import service.GeneralService;
import service.ItemService;
import service.MerkService;
import service.PackagingService;
import service.PlasticService;
import service.WarehouseService;
import tablemodel.ItemOutTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class ItemOutController {
    private ItemService itemService;
    private GeneralService generalService;
    private MerkService merkService;
    private PackagingService packagingService;
    private WarehouseService warehouseService;
    private PlasticService plasticService;
    
    public ItemOutController(){
        itemService = new ItemService();
        generalService = new GeneralService();
        merkService = new MerkService();
        packagingService = new PackagingService();
        warehouseService = new WarehouseService();
        plasticService = new PlasticService();
    }
    
    public ArrayList<Item_Out> getItemList(){
        return(ArrayList<Item_Out>)itemService.getItemOutList();
    }
    
    public ArrayList<Item_Out> getItemList(int plasticid){
        Plastic plastic = plasticService.getPlastic(plasticid);
        return (ArrayList<Item_Out>)itemService.getItemOutList(plastic);
    }
    
    public ArrayList<Item_Out> getActiveItemList(){
        return (ArrayList<Item_Out>)itemService.getActiveItemOutList();
    }
    
    public Item_Out getItem(int id){
        return itemService.getItemOut(id);
    }
    
    public void editItem(ItemOutTableModel iotm, int id, String name, String merk, 
            String packaging, String plastic, String note){
        Item_Out item = getItem(id);
        item.setName(name);
        item.setMerk(merkService.getMerk(merk));
        String [] ls = packaging.split(" ");
        item.setPackaging(packagingService.getPackaging(Float.parseFloat(ls[0]), ls[1]));
        
        try{
            item.setPlastic(plasticService.getPlastic(plastic));
        }
        catch(Exception e){
            item.setPlastic(null);
        }
        
        item.setNote(note);
        
        generalService.Merge(item);
        
        iotm.editRow(item);
    }
    
    public void addItem(ItemOutTableModel iotm, String name, String merk, String packaging, String plastic, int sacksize, String note){
        Item_Out item = new Item_Out();
        item.setName(name);
        item.setMerk(merkService.getMerk(merk));
        String [] ls = packaging.split(" ");
        item.setPackaging(packagingService.getPackaging(Float.parseFloat(ls[0]), ls[1]));
        item.setSacksize(sacksize);
        item.setNote(note);
        
        try{
            item.setPlastic(plasticService.getPlastic(plastic));
        }
        catch(Exception e){
            item.setPlastic(null);
        }
        
        item.setActive(GlobalFields.ACTIVE);
        
        generalService.Persist(item);
        
        List warehouselist = warehouseService.getWarehouseList();
        
        for(Object o : warehouselist){
            Warehouse warehouse = (Warehouse)o;
            
            Item_Out_Warehouse iow = new Item_Out_Warehouse();
            iow.setItem(item);
            iow.setWarehouse(warehouse);
            iow.setStock(0);
            
            generalService.Persist(iow);
        }
        
        iotm.addRow(item);
    }
    
    public void changeStatus(ItemOutTableModel iotm, Item_Out item){
        item.setActive(!item.isActive());
        generalService.Merge(item);
        iotm.editRow(item);
    }
}
