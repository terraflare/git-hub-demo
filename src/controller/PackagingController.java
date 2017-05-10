/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import entity.Packaging;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import service.GeneralService;
import service.PackagingService;
import tablemodel.PackagingTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class PackagingController {
    private PackagingService packagingService;
    private GeneralService generalService;
    
    public PackagingController(){
        packagingService = new PackagingService();
        generalService = new GeneralService();
    }
    
    public ArrayList<Packaging> getPackagingList(){
        return(ArrayList<Packaging>)packagingService.getPackagingList();
    }
    
    public Vector getPackagingNameList(){
        Vector vec = new Vector();
        List ls = packagingService.getPackagingList();
        
        for(Object obj : ls){
            Packaging pack = (Packaging)obj;
            vec.add(pack.getAmount() + " " + pack.getUnit());
        }
        
        return vec;
    }
    
    public void addPackaging(PackagingTableModel ptm, String amount, String unit, String note){
        Packaging packaging = new Packaging();
        packaging.setAmount(Float.parseFloat(amount));
        packaging.setUnit(unit);
        packaging.setNote(note);
        
        generalService.Persist(packaging);
        
        ptm.addRow(packaging);
    }
    
    public void editPackaging(PackagingTableModel ptm, int id, String amount, String unit, String note){
        Packaging packaging = getPackaging(id);
        packaging.setAmount(Float.parseFloat(amount));
        packaging.setUnit(unit);
        packaging.setNote(note);
        
        generalService.Merge(packaging);
        
        ptm.editRow(packaging);
    }
    
    public boolean deletePackaging(int id){
        Packaging packaging = getPackaging(id);
        
        try{
            generalService.Delete(packaging);
            return GlobalFields.SUCCESS;
        }
        catch(Exception e){
            System.out.println(e);
            return GlobalFields.FAIL;
            
        }
    }
    
    public Packaging getPackaging(int id){
        return packagingService.getPackaging(id);
    }
}
