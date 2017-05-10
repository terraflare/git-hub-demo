/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tablemodel;

import entity.Merk;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Purnama
 */
public class MerkTableModel extends AbstractTableModel{
    private final ArrayList<Merk> merklist;
    
    String[] columnNames = new String[]{"ID", "NAMA"};
    
    public MerkTableModel(ArrayList<Merk> merklist){
        super();
        
        this.merklist = merklist;
    }

    @Override
    public int getRowCount() {
        return merklist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Merk m = merklist.get(rowIndex);
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
    
    public void addRow(Merk merk) {
        int rowCount = getRowCount();

        merklist.add(merk);
        fireTableRowsInserted(rowCount, rowCount);
    }
    
    public void editRow(Merk merk){
        
        for(Object o : merklist){
            Merk m = (Merk)o;
            if(m.getId() == merk.getId()){
                m.setName(merk.getName());
                break;
            }
        }
    }
    
    public void deleteRow(int id){
        
        int counter = 0;
        int sel = 0;
        
        for(Object o : merklist){
            Merk w = (Merk)o;
            if(w.getId() == id){
                sel = counter;
            }
            counter++;
        }
        
        merklist.remove(sel);
        fireTableRowsDeleted(sel, sel);
    }
}
