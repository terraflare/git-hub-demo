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
public class InvoiceWarehouse extends Invoice{
    private Warehouse destination;

    public Warehouse getDestination() {
        return destination;
    }

    public void setDestination(Warehouse destination) {
        this.destination = destination;
    }
}
