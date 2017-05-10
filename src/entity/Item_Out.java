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
public class Item_Out extends Item{
    private int sacksize;
    
    private Packaging packaging;
    private Merk merk;
    private Plastic plastic;

    private Set<Item_Out_Warehouse> item_warehouses;

    public Plastic getPlastic() {
        return plastic;
    }

    public void setPlastic(Plastic plastic) {
        this.plastic = plastic;
    }

    public Set<Item_Out_Warehouse> getItem_warehouses() {
        return item_warehouses;
    }

    public void setItem_warehouses(Set<Item_Out_Warehouse> item_warehouses) {
        this.item_warehouses = item_warehouses;
    }

    
    public Packaging getPackaging() {
        return packaging;
    }

    public void setPackaging(Packaging packaging) {
        this.packaging = packaging;
    }

    public Merk getMerk() {
        return merk;
    }

    public void setMerk(Merk merk) {
        this.merk = merk;
    }
    
    public int getSacksize() {
        return sacksize;
    }

    public void setSacksize(int sacksize) {
        this.sacksize = sacksize;
    }
}
