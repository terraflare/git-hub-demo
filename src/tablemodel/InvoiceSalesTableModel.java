/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import entity.InvoiceSales;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Purnama
 */
public class InvoiceSalesTableModel extends AbstractTableModel{
    private final ArrayList<InvoiceSales> invoicesaleslist;
    
    String[] columnNames = new String[]{"ID", "TANGGAL", "GUDANG", "PENGGUNA"};
    
    public InvoiceSalesTableModel(ArrayList<InvoiceSales> invoicesaleslist){
        super();
        
        this.invoicesaleslist = invoicesaleslist;
    }

    @Override
    public int getRowCount() {
        return invoicesaleslist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceSales m = invoicesaleslist.get(rowIndex);
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
    
    public void addRow(InvoiceSales invoicesales) {
        int rowCount = getRowCount();

        invoicesaleslist.add(invoicesales);
        fireTableRowsInserted(rowCount, rowCount);
    }
    
    public void deleteRow(int id){
        
        int counter = 0;
        int sel = 0;
        
        for(Object o : invoicesaleslist){
            InvoiceSales w = (InvoiceSales)o;
            if(w.getId() == id){
                sel = counter;
            }
            counter++;
        }
        
        invoicesaleslist.remove(sel);
        fireTableRowsDeleted(sel, sel);
    }
}