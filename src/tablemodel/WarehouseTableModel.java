/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tablemodel;

import entity.Warehouse;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class WarehouseTableModel extends AbstractTableModel{
    private final ArrayList<Warehouse> warehouselist;
    
    String[] columnNames = new String[]{"ID", "NAMA", "STATUS"};
    
    public WarehouseTableModel(ArrayList<Warehouse> warehouselist){
        super();
        
        this.warehouselist = warehouselist;
    }

    @Override
    public int getRowCount() {
        return warehouselist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Warehouse w = warehouselist.get(rowIndex);
        
        String status;
        
        if(w.isActive() == GlobalFields.ACTIVE){
            status = "AKTIF";
        }
        else{
            status = "NONAKTIF";
        }
        
        Object[] values = new Object[]{String.format("%03d", w.getId()), w.getName(), status};
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
    
    public void addRow(Warehouse warehouse) {
        int rowCount = getRowCount();

        warehouselist.add(warehouse);
        fireTableRowsInserted(rowCount, rowCount);
    }
    
    public void editRow(Warehouse warehouse){
        
        for(Object o : warehouselist){
            Warehouse w = (Warehouse)o;
            if(w.getId() == warehouse.getId()){
                w.setName(warehouse.getName());
                w.setActive(warehouse.isActive());
                break;
            }
        }
    }
}
