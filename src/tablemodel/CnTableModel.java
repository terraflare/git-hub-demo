/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import entity.Cn;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Purnama
 */
public class CnTableModel extends AbstractTableModel{
    private final ArrayList<Cn> cnlist;
    
    String[] columnNames = new String[]{"ID", "STOK (KG)"};
    
    public CnTableModel(ArrayList<Cn> cnlist){
        super();
        
        this.cnlist = cnlist;
    }

    @Override
    public int getRowCount() {
        return cnlist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cn c = cnlist.get(rowIndex);
        Object[] values = new Object[]{String.format("%04d", c.getId()),
            String.format("%.1f",c.getStock())
        };
        
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
    
    public void editRow(Cn cn){
        
        for(Object o : cnlist){
            Cn c = (Cn)o;
            if(c.getId() == cn.getId()){
                c.setStock(cn.getStock());
                break;
            }
        }
    }
}