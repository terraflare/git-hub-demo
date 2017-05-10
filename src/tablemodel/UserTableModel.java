/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tablemodel;

import entity.User;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class UserTableModel extends AbstractTableModel{
    
    private final ArrayList<User> userlist;
    
    String[] columnNames = new String[]{"ID", "NAMA", "KATA SANDI", "WEWENANG", "STATUS"};
    
    public UserTableModel(ArrayList<User> userlist){
        super();
        
        this.userlist = userlist;
    }

    @Override
    public int getRowCount() {
        return userlist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User u = userlist.get(rowIndex);
        
        String status;
        
        if(u.isActive() == GlobalFields.ACTIVE){
            status = "AKTIF";
        }
        else{
            status = "NON AKTIF";
        }
        
        Object[] values = new Object[]{String.format("%03d", u.getId()), u.getUsername(), u.getPassword(), u.getRole(), status};
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
    
    public void addRow(User user) {
        int rowCount = getRowCount();
        
        userlist.add(user);
        fireTableRowsInserted(rowCount, rowCount);
    }
    
    public void editRow(User user){
        
        for(Object o : userlist){
            User u = (User)o;
            if(u.getId() == user.getId()){
                u.setUsername(user.getUsername());
                u.setPassword(user.getPassword());
                u.setRole(user.getRole());
                u.setActive(user.isActive());
                break;
            }
        }
    }
}

