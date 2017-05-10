/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tablemodel;

import entity.Packaging;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Purnama
 */
public class PackagingTableModel extends AbstractTableModel{
    private final ArrayList<Packaging> packaginglist;
    
    String[] columnNames = new String[]{"ID", "JUMLAH", "SATUAN BERAT"};
    
    public PackagingTableModel(ArrayList<Packaging> packaginglist){
        super();
        
        this.packaginglist = packaginglist;
    }

    @Override
    public int getRowCount() {
        return packaginglist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Packaging m = packaginglist.get(rowIndex);
        Object[] values = new Object[]{String.format("%04d", m.getId()), String.valueOf(m.getAmount()), m.getUnit()};
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
    
    public void addRow(Packaging packaging) {
        int rowCount = getRowCount();

        packaginglist.add(packaging);
        fireTableRowsInserted(rowCount, rowCount);
    }
    
    public void editRow(Packaging packaging){
        
        for(Object o : packaginglist){
            Packaging p = (Packaging)o;
            if(p.getId() == packaging.getId()){
                p.setAmount(packaging.getAmount());
                p.setUnit(packaging.getUnit());
                break;
            }
        }
    }
    
    public void deleteRow(int id){
        
        int counter = 0;
        int sel = 0;
        
        for(Object o : packaginglist){
            Packaging w = (Packaging)o;
            if(w.getId() == id){
                sel = counter;
            }
            counter++;
        }
        
        packaginglist.remove(sel);
        fireTableRowsDeleted(sel, sel);
    }
}
