/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author Purnama
 */
public class InvoiceWarehouseOut extends InvoiceWarehouse{

    private Item_Out item;

    public Item_Out getItem() {
        return item;
    }

    public void setItem(Item_Out item) {
        this.item = item;
    }
}
