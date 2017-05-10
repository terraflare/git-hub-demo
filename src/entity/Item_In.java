/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.util.Set;

/**
 *
 * @author Purnama
 */
public class Item_In extends Item{
    
    private Quality quality;
    
    private Set<Item_In_Warehouse> item_warehouses;

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public Set<Item_In_Warehouse> getItem_warehouses() {
        return item_warehouses;
    }

    public void setItem_warehouses(Set<Item_In_Warehouse> item_warehouses) {
        this.item_warehouses = item_warehouses;
    }
}
