/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tablemodel;

import entity.Category;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Purnama
 */
public class CategoryTableModel extends AbstractTableModel{
    private final ArrayList<Category> categorylist;
    
    String[] columnNames = new String[]{"ID", "NAMA"};
    
    public CategoryTableModel(ArrayList<Category> categorylist){
        super();
        
        this.categorylist = categorylist;
    }

    @Override
    public int getRowCount() {
        return categorylist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Category m = categorylist.get(rowIndex);
        Object[] values = new Object[]{String.format("%04d", m.getId()), m.getName()};
        return values[columnIndex];
    }
    
    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
    
    @Override
    public Class getColumnClass(int c) {  
        return getValueAt(0, c).getClass();  
    }
    
    public void addRow(Category category) {
        int rowCount = getRowCount();

        categorylist.add(category);
        fireTableRowsInserted(rowCount, rowCount);
    }
    
    public void editRow(Category category){
        
        for(Object o : categorylist){
            Category c = (Category)o;
            if(c.getId() == category.getId()){
                c.setName(category.getName());
                break;
            }
        }
    }
    
    public void deleteRow(int id){
        
        int counter = 0;
        int sel = 0;
        
        for(Object o : categorylist){
            Category w = (Category)o;
            if(w.getId() == id){
                sel = counter;
            }
            counter++;
        }
        
        categorylist.remove(sel);
        fireTableRowsDeleted(sel, sel);
    }
}
