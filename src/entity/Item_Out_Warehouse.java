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
public class Item_Out_Warehouse{
    private int id;
    private Warehouse warehouse;
    private float stock;
    private Item_Out item;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public float getStock() {
        return stock;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }

    public Item_Out getItem() {
        return item;
    }

    public void setItem(Item_Out item) {
        this.item = item;
    }
}
