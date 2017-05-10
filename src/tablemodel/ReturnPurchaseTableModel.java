/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import entity.ReturnPurchase;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Purnama
 */
public class ReturnPurchaseTableModel extends AbstractTableModel{
    private final ArrayList<ReturnPurchase> returnpurchaselist;
    
    String[] columnNames = new String[]{"ID", "TANGGAL", "GUDANG", "PENGGUNA"};
    
    public ReturnPurchaseTableModel(ArrayList<ReturnPurchase> returnpurchaselist){
        super();
        
        this.returnpurchaselist = returnpurchaselist;
    }

    @Override
    public int getRowCount() {
        return returnpurchaselist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ReturnPurchase m = returnpurchaselist.get(rowIndex);
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
    
    public void addRow(ReturnPurchase returnpurchase) {
        int rowCount = getRowCount();

        returnpurchaselist.add(returnpurchase);
        fireTableRowsInserted(rowCount, rowCount);
    }
    
    public void deleteRow(int id){
        
        int counter = 0;
        int sel = 0;
        
        for(Object o : returnpurchaselist){
            ReturnPurchase w = (ReturnPurchase)o;
            if(w.getId() == id){
                sel = counter;
            }
            counter++;
        }
        
        returnpurchaselist.remove(sel);
        fireTableRowsDeleted(sel, sel);
    }
}