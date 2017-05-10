/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tablemodel;

import entity.Quality;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Purnama
 */
public class QualityTableModel extends AbstractTableModel {
    private final ArrayList<Quality> qualitylist;
    
    String[] columnNames = new String[]{"ID", "NAMA", "KATEGORI"};
    
    public QualityTableModel(ArrayList<Quality> qualitylist){
        super();
        
        this.qualitylist = qualitylist;
    }

    @Override
    public int getRowCount() {
        return qualitylist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Quality m = qualitylist.get(rowIndex);
        Object[] values = new Object[]{String.format("%04d", m.getId()), m.getName(), m.getCategory().getName()};
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
    
    public void addRow(Quality quality) {
        int rowCount = getRowCount();

        qualitylist.add(quality);
        fireTableRowsInserted(rowCount, rowCount);
    } 
    
    public void editRow(Quality quality){
        
        for(Object o : qualitylist){
            Quality q = (Quality)o;
            if(q.getId() == quality.getId()){
                q.setName(quality.getName());
                q.setCategory(quality.getCategory());
                break;
            }
        }
    }
    
    public void deleteRow(int id){
        
        int counter = 0;
        int sel = 0;
        
        for(Object o : qualitylist){
            Quality w = (Quality)o;
            if(w.getId() == id){
                sel = counter;
            }
            counter++;
        }
        
        qualitylist.remove(sel);
        fireTableRowsDeleted(sel, sel);
    }
}