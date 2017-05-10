/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tablemodel;

import entity.Item_Out;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class ItemOutTableModel extends AbstractTableModel{
    private final ArrayList<Item_Out> itemoutlist;
    
    String[] columnNames = new String[]{"ID", "NAMA", "MEREK", "KEMASAN", "STATUS"};
    
    public ItemOutTableModel(ArrayList<Item_Out> itemoutlist){
        super();
        
        this.itemoutlist = itemoutlist;
    }

    @Override
    public int getRowCount() {
        return itemoutlist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Item_Out u = itemoutlist.get(rowIndex);
        
        String status;
        
        if(u.isActive() == GlobalFields.ACTIVE){
            status = "AKTIF";
        }
        else{
            status = "NONAKTIF";
        }
        
        Object[] values = new Object[]{String.format("%04d", u.getId()), u.getName(), 
            u.getMerk().getName(), u.getPackaging().getAmount() + " " + u.getPackaging().getUnit(),
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
    
    public void addRow(Item_Out itemout) {
        int rowCount = getRowCount();

        itemoutlist.add(itemout);
        fireTableRowsInserted(rowCount, rowCount);
    }
    
    public void editRow(Item_Out itemout){
        
        for(Object o : itemoutlist){
            Item_Out c = (Item_Out)o;
            if(c.getId() == itemout.getId()){
                c.setName(itemout.getName());
                c.setMerk(itemout.getMerk());
                c.setPackaging(itemout.getPackaging());
                c.setActive(itemout.isActive());
                break;
            }
        }
    }
}
