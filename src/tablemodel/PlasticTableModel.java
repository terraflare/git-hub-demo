/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import entity.Plastic;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class PlasticTableModel extends AbstractTableModel{
    private final ArrayList<Plastic> plasticlist;
    
    String[] columnNames = new String[]{"ID", "NAMA", "STATUS"};
    
    public PlasticTableModel(ArrayList<Plastic> plasticlist){
        super();
        
        this.plasticlist = plasticlist;
    }

    @Override
    public int getRowCount() {
        return plasticlist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Plastic p = plasticlist.get(rowIndex);
        
        String status;
        
        if(p.isActive() == GlobalFields.ACTIVE){
            status = "AKTIF";
        }
        else{
            status = "NON AKTIF";
        }
        
        Object[] values = new Object[]{p.getId(), p.getName(), status};
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
    
    public void addRow(Plastic plastic) {
        int rowCount = getRowCount();

        plasticlist.add(plastic);
        fireTableRowsInserted(rowCount, rowCount);
    }
    
    public void editRow(Plastic plastic){
        
        for(Object o : plasticlist){
            Plastic c = (Plastic)o;
            if(c.getId() == plastic.getId()){
                c.setName(plastic.getName());
                c.setActive(plastic.isActive());
                break;
            }
        }
    }
}
