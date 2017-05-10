/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import entity.InvoiceSales;
import entity.ItemExchange;
import entity.ReturnSales;
import entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import service.GeneralService;
import service.ItemExchangeService;
import service.ReturnSalesService;
import tablemodel.ItemExchangeTableModel;
import util.DateUtil;
import util.GlobalFields;

/**
 *
 * @author Purnama
 */
public class ItemExchangeController {
    
    private GeneralService generalService;
    private ItemExchangeService itemexchangeService;
    private ReturnSalesService returnsalesService;
    
    public ItemExchangeController(){
        generalService = new GeneralService();
        itemexchangeService = new ItemExchangeService();
        returnsalesService = new ReturnSalesService();
    }
    
    public ArrayList<ItemExchange> getItemExchangeList(){
        return(ArrayList<ItemExchange>)itemexchangeService.getItemExchangeList(GlobalFields.USER);
    }
    
    public ItemExchange getItemExchange(int id){
        return itemexchangeService.getItemExchange(id);
    }
    
    public ItemExchange addItemExchange(int returnid, User user, Date date){
        
        ReturnSales rs = returnsalesService.getReturnSales(returnid);
        
        ItemExchange itemexchange = new ItemExchange();
        itemexchange.setDate(date);
        itemexchange.setUser(user);
        itemexchange.setReturnsales(rs);
        
        generalService.Persist(itemexchange);
        
        return itemexchange;
    }
    
    public boolean editItemExchange(ItemExchange itemexchange){
        try{
            ReturnSales returnsales = itemexchange.getReturnsales();
            InvoiceSales invoicesales = itemexchange.getInvoicesales();
            
            if(returnsales.getQuantity() != invoicesales.getQuantity()){
                return GlobalFields.FAIL;
            }
            else if(returnsales.getItem().getId() != invoicesales.getItem().getId()){
                return GlobalFields.FAIL;
            }
            else{
                generalService.Merge(itemexchange);
                return GlobalFields.SUCCESS;
            }
        }
        catch(Exception e){
            return GlobalFields.FAIL;
        }
    }
    
    public void deleteItemExchange(int id){
        ItemExchange is = itemexchangeService.getItemExchange(id);
        
        generalService.Delete(is);
    }
    
    public void deleteAllItemExchange(Date end){
       
        Date date = DateUtil.toEndofDay(end);
        
        List itemexchangelist = itemexchangeService.getItemExchangeList(date);
        
        for(Object o : itemexchangelist){
            ItemExchange p = (ItemExchange)o;
            
            generalService.Delete(p);
        }
    }
}
