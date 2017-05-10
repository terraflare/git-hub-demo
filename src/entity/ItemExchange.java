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
public class ItemExchange {
    private int id;
    private Date date;
    private User user;
    private ReturnSales returnsales;
    private InvoiceSales invoicesales;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReturnSales getReturnsales() {
        return returnsales;
    }

    public void setReturnsales(ReturnSales returnsales) {
        this.returnsales = returnsales;
    }

    public InvoiceSales getInvoicesales() {
        return invoicesales;
    }

    public void setInvoicesales(InvoiceSales invoicesales) {
        this.invoicesales = invoicesales;
    }
}
