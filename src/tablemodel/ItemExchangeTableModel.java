/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import entity.ItemExchange;
import entity.Item_Out;
import entity.ReturnSales;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Purnama
 */
public class ItemExchangeTableModel extends AbstractTableModel{
    private final ArrayList<ItemExchange> itemexchangelist;
    
    String[] columnNames = new String[]{"ID", "TANGGAL", "NAMA BARANG", "MEREK", "KEMASAN", "JUMLAH", "STATUS"};
    
    public ItemExchangeTableModel(ArrayList<ItemExchange> itemexchangelist){
        super();
        
        this.itemexchangelist = itemexchangelist;
    }

    @Override
    public int getRowCount() {
        return itemexchangelist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ItemExchange c = itemexchangelist.get(rowIndex);
        
        ReturnSales rs = c.getReturnsales();
        Item_Out io = rs.getItem();
        
        String unit =  String.format("%.1f", rs.getQuantity()%io.getSacksize());
        
        String status = "";
        
        if(c.getInvoicesales() != null){
            status = "OK";
        }
        
        Object[] values = new Object[]{String.format("%04d", c.getId()),
            c.getDate(),
            rs.getItem().getName(), 
            rs.getItem().getMerk().getName(),
            rs.getItem().getPackaging().getAmount() + " " + 
                rs.getItem().getPackaging().getUnit(),
            (int)rs.getQuantity()/io.getSacksize() + " k " + unit + " u",
            status
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
    
    public void addRow(ItemExchange itemexchange) {
        int rowCount = getRowCount();

        itemexchangelist.add(itemexchange);
        fireTableRowsInserted(rowCount, rowCount);
    }
    
    public void deleteRow(int id){
        
        int counter = 0;
        int sel = 0;
        
        for(Object o : itemexchangelist){
            ItemExchange w = (ItemExchange)o;
            if(w.getId() == id){
                sel = counter;
            }
            counter++;
        }
        
        itemexchangelist.remove(sel);
        fireTableRowsDeleted(sel, sel);
    }
}
