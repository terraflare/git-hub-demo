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
public class Production{
    private int id;
    private User user;
    private Warehouse warehouse;
    private String note;
    private boolean status;
    private Date date;
    private Item_In itemin;
    private int inquantity;
    
    private Item_Out itemout;
    private float inputquantity;
    private float outquantity;

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

    public Item_In getItemin() {
        return itemin;
    }

    public void setItemin(Item_In itemin) {
        this.itemin = itemin;
    }

    public int getInquantity() {
        return inquantity;
    }

    public void setInquantity(int inquantity) {
        this.inquantity = inquantity;
    }

    public Item_Out getItemout() {
        return itemout;
    }

    public void setItemout(Item_Out itemout) {
        this.itemout = itemout;
    }

    public float getInputquantity() {
        return inputquantity;
    }

    public void setInputquantity(float inputquantity) {
        this.inputquantity = inputquantity;
    }

    public float getOutquantity() {
        return outquantity;
    }

    public void setOutquantity(float outquantity) {
        this.outquantity = outquantity;
    }

    
}
