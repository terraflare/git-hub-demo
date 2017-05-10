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
public class AdjustmentPlastic extends Adjustment{
    private Plastic plastic;

    public Plastic getPlastic() {
        return plastic;
    }

    public void setPlastic(Plastic plastic) {
        this.plastic = plastic;
    }
}
