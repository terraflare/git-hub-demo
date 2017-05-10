/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import entity.Production4;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Purnama
 */
public class Production4TableModel extends AbstractTableModel{
    private final ArrayList<Production4> production4list;
    
    String[] columnNames = new String[]{"ID", "TANGGAL", "PENGGUNA", "GUDANG"};
    
    public Production4TableModel(ArrayList<Production4> production4list){
        super();
        
        this.production4list = production4list;
    }

    @Override
    public int getRowCount() {
        return production4list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Production4 m = production4list.get(rowIndex);
        
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
    
    public void addRow(Production4 production4) {
        int rowCount = getRowCount();

        production4list.add(production4);
        fireTableRowsInserted(rowCount, rowCount);
    } 
    
    public void editRow(Production4 production4){
        for(Object o : production4list){
            Production4 w = (Production4)o;
            if(w.getId() == production4.getId()){
                w.setDate(production4.getDate());
                w.setWarehouse(production4.getWarehouse());
                w.setDate(production4.getDate());
                break;
            }
        }
    }
    
    public void deleteRow(int id){
        
        int counter = 0;
        int sel = 0;
        
        for(Object o : production4list){
            Production4 w = (Production4)o;
            if(w.getId() == id){
                sel = counter;
            }
            counter++;
        }
        
        production4list.remove(sel);
        fireTableRowsDeleted(sel, sel);
    }
}