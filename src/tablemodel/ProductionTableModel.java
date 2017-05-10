/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import entity.Production;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Purnama
 */
public class ProductionTableModel extends AbstractTableModel{
    private final ArrayList<Production> productionlist;
    
    String[] columnNames = new String[]{"ID", "TANGGAL", "PENGGUNA", "GUDANG"};
    
    public ProductionTableModel(ArrayList<Production> productionlist){
        super();
        
        this.productionlist = productionlist;
    }

    @Override
    public int getRowCount() {
        return productionlist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Production m = productionlist.get(rowIndex);
        
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
    
    public void addRow(Production production) {
        int rowCount = getRowCount();

        productionlist.add(production);
        fireTableRowsInserted(rowCount, rowCount);
    } 
    
    public void editRow(Production production){
        for(Object o : productionlist){
            Production w = (Production)o;
            if(w.getId() == production.getId()){
                w.setDate(production.getDate());
                w.setWarehouse(production.getWarehouse());
                w.setDate(production.getDate());
                break;
            }
        }
    }
    
    public void deleteRow(int id){
        
        int counter = 0;
        int sel = 0;
        
        for(Object o : productionlist){
            Production w = (Production)o;
            if(w.getId() == id){
                sel = counter;
            }
            counter++;
        }
        
        productionlist.remove(sel);
        fireTableRowsDeleted(sel, sel);
    }
}