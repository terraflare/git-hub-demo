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
public class Production3 {
    private int id;
    private User user;
    private Warehouse warehouse;
    private String note;
    private boolean status;
    private Date date;
    private Cn cn;
    private Item_Out itemout;
    private float inquantity;
    private float outquantity;
    private float difference;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Cn getCn() {
        return cn;
    }

    public void setCn(Cn cn) {
        this.cn = cn;
    }

    public Item_Out getItemout() {
        return itemout;
    }

    public void setItemout(Item_Out itemout) {
        this.itemout = itemout;
    }

    public float getInquantity() {
        return inquantity;
    }

    public void setInquantity(float inquantity) {
        this.inquantity = inquantity;
    }

    public float getOutquantity() {
        return outquantity;
    }

    public void setOutquantity(float outquantity) {
        this.outquantity = outquantity;
    }

    public float getDifference() {
        return difference;
    }

    public void setDifference(float difference) {
        this.difference = difference;
    }
    
    
}
