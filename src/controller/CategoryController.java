/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import entity.Category;
import java.util.ArrayList;
import java.util.List;
import service.CategoryService;
import service.GeneralService;
import tablemodel.CategoryTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class CategoryController {
    
    private CategoryService categoryService;
    private GeneralService generalService;
    
    public CategoryController(){
        categoryService = new CategoryService();
        generalService = new GeneralService();
    }
    
    public ArrayList<Category> getCategoryList(){
        return(ArrayList<Category>)categoryService.getCategoryList();
    }
    
    public Object [] getCategoryNameList(){
        List ls = categoryService.getCategoryNameList();
        return ls.toArray();
    }
    
    public void addCategory(CategoryTableModel ctm, String name, String note){
        Category category = new Category();
        category.setName(name);
        category.setNote(note);
        
        generalService.Persist(category);
        
        ctm.addRow(category);
    }
    
    public void editCategory(CategoryTableModel ctm, int id, String name, String note){
        Category category = getCategory(id);
        category.setName(name);
        category.setNote(note);
        
        generalService.Merge(category);
        
        ctm.editRow(category);
    }
    
    public boolean deleteCategory(int id){
        Category category = getCategory(id);
        
        try{
            generalService.Delete(category);
            return GlobalFields.SUCCESS;
        }
        catch(Exception e){
            System.out.println(e);
            return GlobalFields.FAIL;
            
        }
    }
    
    public Category getCategory(int id){
        return categoryService.getCategory(id);
    }
    
    public Category getCategory(String name){
        return categoryService.getCategory(name);
    }
}
