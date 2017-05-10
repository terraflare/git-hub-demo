/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tablemodel;

import entity.Item_In;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class ItemInTableModel extends AbstractTableModel{
    private final ArrayList<Item_In> iteminlist;
    
    String[] columnNames = new String[]{"ID", "NAMA", "KUALITAS", "STATUS"};
    
    public ItemInTableModel(ArrayList<Item_In> iteminlist){
        super();
        
        this.iteminlist = iteminlist;
    }

    @Override
    public int getRowCount() {
        return iteminlist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Item_In u = iteminlist.get(rowIndex);
        
        String status;
        
        if(u.isActive() == GlobalFields.ACTIVE){
            status = "AKTIF";
        }
        else{
            status = "NONAKTIF";
        }
        
        Object[] values = new Object[]{String.format("%04d", u.getId()), u.getName(), 
            u.getQuality().getCategory().getName() + " " + u.getQuality().getName(), 
            status};
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
    
    public void addRow(Item_In itemin) {
        int rowCount = getRowCount();

        iteminlist.add(itemin);
        fireTableRowsInserted(rowCount, rowCount);
    }
    
    public void editRow(Item_In itemin){
        
        for(Object o : iteminlist){
            Item_In c = (Item_In)o;
            if(c.getId() == itemin.getId()){
                c.setName(itemin.getName());
                c.setQuality(itemin.getQuality());
                c.setActive(itemin.isActive());
                break;
            }
        }
    }
}
