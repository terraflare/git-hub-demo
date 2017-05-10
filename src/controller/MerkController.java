/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import entity.Merk;
import java.util.ArrayList;
import java.util.List;
import service.GeneralService;
import service.MerkService;
import tablemodel.MerkTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class MerkController {
    private MerkService merkService;
    
    private GeneralService generalService;
    
    public MerkController(){
        merkService = new MerkService();
        generalService = new GeneralService();
    }
    
    public ArrayList<Merk> getMerkList(){
        return(ArrayList<Merk>)merkService.getMerkList();
    }
    
    public Object [] getMerkNameList(){
        List ls = merkService.getMerkNameList();
        return ls.toArray();
    }
    
    public void addMerk(MerkTableModel mtm, String name, String note){
        Merk merk = new Merk();
        merk.setName(name);
        merk.setNote(note);
        
        generalService.Persist(merk);
        
        mtm.addRow(merk);
    }
    
    public void editMerk(MerkTableModel mtm, int id, String name, String note){
        Merk w = getMerk(id);
        w.setName(name);
        w.setNote(note);
        
        generalService.Merge(w);
        
        mtm.editRow(w);
    }
    
    public boolean deleteMerk(int id){
        Merk merk = getMerk(id);
        
        try{
            generalService.Delete(merk);
            return GlobalFields.SUCCESS;
        }
        catch(Exception e){
            System.out.println(e);
            return GlobalFields.FAIL;
            
        }
    }
    
    public Merk getMerk(int id){
        return merkService.getMerk(id);
    }
}
