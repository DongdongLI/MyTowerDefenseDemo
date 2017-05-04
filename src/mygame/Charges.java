/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

/**
 *
 * @author dli
 */
public class Charges {
    private Integer damage;
    private Integer remainingBullets;

    public Charges(Integer damage, Integer remainingBullets) {
        this.damage = damage;
        this.remainingBullets = remainingBullets;
    }

    public Charges() {
        
    }
    
    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getRemainingBullets() {
        return remainingBullets;
    }

    public void setRemainingBullets(Integer remainingBullets) {
        this.remainingBullets = remainingBullets;
    }
    
    
}
