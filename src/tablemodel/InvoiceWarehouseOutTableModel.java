/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import entity.InvoiceWarehouseOut;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Purnama
 */
public class InvoiceWarehouseOutTableModel extends AbstractTableModel{
    private final ArrayList<InvoiceWarehouseOut> invoicewarehouselist;
    
    String[] columnNames = new String[]{"ID", "TANGGAL", "ASAL", "TUJUAN", "PENGGUNA"};
    
    public InvoiceWarehouseOutTableModel(ArrayList<InvoiceWarehouseOut> invoicewarehouselist){
        super();
        
        this.invoicewarehouselist = invoicewarehouselist;
    }

    @Override
    public int getRowCount() {
        return invoicewarehouselist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceWarehouseOut m = invoicewarehouselist.get(rowIndex);
        Object[] values = new Object[]{String.format("%04d", m.getId()), m.getDate(), m.getWarehouse().getName(),m.getDestination().getName(), m.getUser().getUsername()};
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
    
    public void addRow(InvoiceWarehouseOut invoicewarehouse) {
        int rowCount = getRowCount();

        invoicewarehouselist.add(invoicewarehouse);
        fireTableRowsInserted(rowCount, rowCount);
    }
    
    public void deleteRow(int id){
        
        int counter = 0;
        int sel = 0;
        
        for(Object o : invoicewarehouselist){
            InvoiceWarehouseOut w = (InvoiceWarehouseOut)o;
            if(w.getId() == id){
                sel = counter;
            }
            counter++;
        }
        
        invoicewarehouselist.remove(sel);
        fireTableRowsDeleted(sel, sel);
    }
}
