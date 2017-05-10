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
public class Plastic {
    private int id;
    private String name;
    private String note;
    private boolean active;
    
    private Set<Plastic_Warehouse> plastic_warehouses;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Set<Plastic_Warehouse> getPlastic_warehouses() {
        return plastic_warehouses;
    }

    public void setPlastic_warehouses(Set<Plastic_Warehouse> plastic_warehouses) {
        this.plastic_warehouses = plastic_warehouses;
    }
}
