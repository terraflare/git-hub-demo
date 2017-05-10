/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import entity.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import service.GeneralService;
import service.UserService;
import service.WarehouseService;
import tablemodel.UserTableModel;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class UserController {
    
    private UserService userService;
    private GeneralService generalService;
    private WarehouseService warehouseService;
    
    public UserController(){
        userService = new UserService();
        generalService = new GeneralService();
        warehouseService = new WarehouseService();
    }
    
    public ArrayList<User> getUserList(){
        return(ArrayList<User>)userService.getUserList();
    }
    
    public User getUser(int id){
        return userService.getUser(id);
    }
    
    public void addUser(UserTableModel utm, String username, String password, String role){
        User user = new User();
        user.setPassword(password);
        user.setRole(role);
        user.setUsername(username);
        user.setActive(GlobalFields.ACTIVE);
       
        generalService.Persist(user);
        
        utm.addRow(user);
    }
    
    public void editUser(UserTableModel utm, int id, String username, String password, String role){
        
        User u = getUser(id);
        u.setPassword(password);
        u.setUsername(username);
        u.setRole(role);
        
        generalService.Merge(u);
        
        utm.editRow(u);
    }
    
    public User editUser(List warehouselist, User user){
        HashSet set = new HashSet();
        
        for(Object o : warehouselist){
            String warehousename = o.toString();
            
            set.add(warehouseService.getWarehouse(warehousename));
        }
        
        user.setWarehouses(set);
        
        generalService.Merge(user);
        
        return user;
    }
    
    public void changeStatus(UserTableModel utm, User user){
        user.setActive(!user.isActive());
        generalService.Merge(user);
        utm.editRow(user);
    }
}
