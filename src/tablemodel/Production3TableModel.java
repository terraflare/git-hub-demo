/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import entity.Production3;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Purnama
 */
public class Production3TableModel extends AbstractTableModel{
    private final ArrayList<Production3> production3list;
    
    String[] columnNames = new String[]{"ID", "TANGGAL", "PENGGUNA", "GUDANG"};
    
    public Production3TableModel(ArrayList<Production3> production3list){
        super();
        
        this.production3list = production3list;
    }

    @Override
    public int getRowCount() {
        return production3list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Production3 m = production3list.get(rowIndex);
        
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
    
    public void addRow(Production3 production3) {
        int rowCount = getRowCount();

        production3list.add(production3);
        fireTableRowsInserted(rowCount, rowCount);
    } 
    
    public void editRow(Production3 production3){
        for(Object o : production3list){
            Production3 w = (Production3)o;
            if(w.getId() == production3.getId()){
                w.setDate(production3.getDate());
                w.setWarehouse(production3.getWarehouse());
                w.setDate(production3.getDate());
                break;
            }
        }
    }
    
    public void deleteRow(int id){
        
        int counter = 0;
        int sel = 0;
        
        for(Object o : production3list){
            Production3 w = (Production3)o;
            if(w.getId() == id){
                sel = counter;
            }
            counter++;
        }
        
        production3list.remove(sel);
        fireTableRowsDeleted(sel, sel);
    }
}