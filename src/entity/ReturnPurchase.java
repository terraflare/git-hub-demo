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
public class ReturnPurchase extends Invoice{
    private Item_In item;

    public Item_In getItem() {
        return item;
    }

    public void setItem(Item_In item) {
        this.item = item;
    }
}
