/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import entity.Quality;
import java.util.ArrayList;
import java.util.List;
import service.CategoryService;
import service.GeneralService;
import service.QualityService;
import tablemodel.QualityTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class QualityController {
    
    private QualityService qualityService;
    private GeneralService generalService;
    private CategoryService categoryService;
    
    public QualityController(){
        qualityService = new QualityService();
        generalService = new GeneralService();
        categoryService = new CategoryService();
    }
    
    public ArrayList<Quality> getQualityList(){
        return(ArrayList<Quality>)qualityService.getQualityList();
    }
    
    public Object [] getQualityNameList(){
        List ls = qualityService.getQualityNameList();
        return ls.toArray();
    }
    
    public void addQuality(QualityTableModel qtm, String name, String category, String note){
        Quality quality = new Quality();
        quality.setName(name);
        quality.setCategory(categoryService.getCategory(category));
        quality.setNote(note);
        
        generalService.Persist(quality);
        
        qtm.addRow(quality);
    }
    
    public void editQuality(QualityTableModel qtm, int id, String name, String category, String note){
        Quality q = getQuality(id);
        q.setName(name);
        q.setCategory(categoryService.getCategory(category));
        q.setNote(note);
        
        generalService.Merge(q);
        
        qtm.editRow(q);
    }
    
    public boolean deleteQuality(int id){
        Quality quality = getQuality(id);
        
        try{
            generalService.Delete(quality);
            return GlobalFields.SUCCESS;
        }
        catch(Exception e){
            System.out.println(e);
            return GlobalFields.FAIL;
            
        }
    }
    
    public Quality getQuality(int id){
        return qualityService.getQuality(id);
    }
}
