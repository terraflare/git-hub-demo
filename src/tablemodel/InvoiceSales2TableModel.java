/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import entity.InvoiceSales2;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Purnama
 */
public class InvoiceSales2TableModel extends AbstractTableModel{
    private final ArrayList<InvoiceSales2> invoicesales2list;
    
    String[] columnNames = new String[]{"ID", "TANGGAL", "GUDANG", "PENGGUNA"};
    
    public InvoiceSales2TableModel(ArrayList<InvoiceSales2> invoicesales2list){
        super();
        
        this.invoicesales2list = invoicesales2list;
    }

    @Override
    public int getRowCount() {
        return invoicesales2list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceSales2 m = invoicesales2list.get(rowIndex);
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
    
    public void addRow(InvoiceSales2 invoicesales2) {
        int rowCount = getRowCount();

        invoicesales2list.add(invoicesales2);
        fireTableRowsInserted(rowCount, rowCount);
    }
    
    public void deleteRow(int id){
        
        int counter = 0;
        int sel = 0;
        
        for(Object o : invoicesales2list){
            InvoiceSales2 w = (InvoiceSales2)o;
            if(w.getId() == id){
                sel = counter;
            }
            counter++;
        }
        
        invoicesales2list.remove(sel);
        fireTableRowsDeleted(sel, sel);
    }
}