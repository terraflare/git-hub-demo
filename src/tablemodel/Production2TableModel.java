/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import entity.Production2;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Purnama
 */
public class Production2TableModel extends AbstractTableModel{
    private final ArrayList<Production2> production2list;
    
    String[] columnNames = new String[]{"ID", "TANGGAL", "PENGGUNA", "GUDANG"};
    
    public Production2TableModel(ArrayList<Production2> production2list){
        super();
        
        this.production2list = production2list;
    }

    @Override
    public int getRowCount() {
        return production2list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Production2 m = production2list.get(rowIndex);
        
        Object[] values = new Object[]{String.format("%04d", m.getId()), m.getDate(), m.getUser().getUsername(), m.getWarehouse().getName()};
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
    
    public void addRow(Production2 production2) {
        int rowCount = getRowCount();

        production2list.add(production2);
        fireTableRowsInserted(rowCount, rowCount);
    } 
    
    public void editRow(Production2 production2){
        for(Object o : production2list){
            Production2 w = (Production2)o;
            if(w.getId() == production2.getId()){
                w.setDate(production2.getDate());
                w.setWarehouse(production2.getWarehouse());
                w.setDate(production2.getDate());
                break;
            }
        }
    }
    
    public void deleteRow(int id){
        
        int counter = 0;
        int sel = 0;
        
        for(Object o : production2list){
            Production2 w = (Production2)o;
            if(w.getId() == id){
                sel = counter;
            }
            counter++;
        }
        
        production2list.remove(sel);
        fireTableRowsDeleted(sel, sel);
    }
}
