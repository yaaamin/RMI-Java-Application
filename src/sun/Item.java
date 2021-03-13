/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sun;

import java.io.Serializable;

/**
 *
 * @author salma
 */
import java.io.Serializable;

public class Item implements Serializable {
    public int id;
    public String name;
    public double price;
    public int stock;
    public boolean isDeleted = false;

    public Item (int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}