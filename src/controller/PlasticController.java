/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Plastic;
import entity.Plastic_Warehouse;
import entity.Warehouse;
import java.util.ArrayList;
import java.util.List;
import service.GeneralService;
import service.PlasticService;
import service.WarehouseService;
import tablemodel.PlasticTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class PlasticController {
    
    private GeneralService generalService;
    private PlasticService plasticService;
    private WarehouseService warehouseService;
    
    public PlasticController(){
        generalService = new GeneralService();
        plasticService = new PlasticService();
        warehouseService = new WarehouseService();
    }
    
    public ArrayList<Plastic> getPlasticList(){
        return(ArrayList<Plastic>)plasticService.getPlasticList();
    }
    
    public ArrayList<Plastic> getActivePlasticList(){
        return (ArrayList<Plastic>)plasticService.getActivePlasticList();
    }
    
    public Object [] getActivePlasticNameList(){
        List ls = plasticService.getActivePlasticNameList();
        return ls.toArray();
    }
    
    public Plastic getPlastic(int id){
        return plasticService.getPlastic(id);
    }
    
    public void addPlastic(PlasticTableModel ptm, String name, String note){
        Plastic plastic = new Plastic();
        plastic.setName(name);
        plastic.setNote(note);
        plastic.setActive(GlobalFields.ACTIVE);
        
        generalService.Persist(plastic);
        
        List warehouselist = warehouseService.getWarehouseList();
        
        for(Object o : warehouselist){
            Warehouse warehouse = (Warehouse)o;
            
            Plastic_Warehouse pw = new Plastic_Warehouse();
            pw.setPlastic(plastic);
            pw.setWarehouse(warehouse);
            pw.setStock(0);
            
            generalService.Persist(pw);
        }
        
        ptm.addRow(plastic);
    }
    
    public void editPlastic(PlasticTableModel Ptm, int id, String name, String note){
        Plastic plastik = getPlastic(id);
        plastik.setName(name);
        plastik.setNote(note);
        
        generalService.Merge(plastik);
        
        Ptm.editRow(plastik);
    }
    
    public void changeStatus(PlasticTableModel ptm, Plastic plastic){
        plastic.setActive(!plastic.isActive());
        generalService.Merge(plastic);
        ptm.editRow(plastic);
    }
}
