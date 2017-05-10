/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import entity.InvoicePurchase;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Purnama
 */
public class InvoicePurchaseTableModel extends AbstractTableModel{
    private final ArrayList<InvoicePurchase> invoicepurchaselist;
    
    String[] columnNames = new String[]{"ID", "TANGGAL", "GUDANG", "PENGGUNA"};
    
    public InvoicePurchaseTableModel(ArrayList<InvoicePurchase> invoicepurchaselist){
        super();
        
        this.invoicepurchaselist = invoicepurchaselist;
    }

    @Override
    public int getRowCount() {
        return invoicepurchaselist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoicePurchase m = invoicepurchaselist.get(rowIndex);
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
    
    public void addRow(InvoicePurchase invoicepurchase) {
        int rowCount = getRowCount();

        invoicepurchaselist.add(invoicepurchase);
        fireTableRowsInserted(rowCount, rowCount);
    }
    
    public void deleteRow(int id){
        
        int counter = 0;
        int sel = 0;
        
        for(Object o : invoicepurchaselist){
            InvoicePurchase w = (InvoicePurchase)o;
            if(w.getId() == id){
                sel = counter;
            }
            counter++;
        }
        
        invoicepurchaselist.remove(sel);
        fireTableRowsDeleted(sel, sel);
    }
}