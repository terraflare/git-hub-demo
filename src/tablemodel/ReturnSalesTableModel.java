/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import entity.ReturnSales;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Purnama
 */
public class ReturnSalesTableModel extends AbstractTableModel {
        private final ArrayList<ReturnSales> returnsaleslist;
    
    String[] columnNames = new String[]{"ID", "TANGGAL", "GUDANG", "PENGGUNA"};
    
    public ReturnSalesTableModel(ArrayList<ReturnSales> returnsaleslist){
        super();
        
        this.returnsaleslist = returnsaleslist;
    }

    @Override
    public int getRowCount() {
        return returnsaleslist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ReturnSales m = returnsaleslist.get(rowIndex);
        Object[] values = new Object[]{String.format("%04d", m.getId()), m.getDate(), m.getWarehouse().getName(), m.getUser().getUsername()};
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
    
    public void addRow(ReturnSales returnsales) {
        int rowCount = getRowCount();

        returnsaleslist.add(returnsales);
        fireTableRowsInserted(rowCount, rowCount);
    }
    
    public void deleteRow(int id){
        
        int counter = 0;
        int sel = 0;
        
        for(Object o : returnsaleslist){
            ReturnSales w = (ReturnSales)o;
            if(w.getId() == id){
                sel = counter;
            }
            counter++;
        }
        
        returnsaleslist.remove(sel);
        fireTableRowsDeleted(sel, sel);
    }
}
