/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Purnama
 */
public class ReturnSales extends Invoice{
    private Item_Out item;

    public Item_Out getItem() {
        return item;
    }

    public void setItem(Item_Out item) {
        this.item = item;
    }
    
}
