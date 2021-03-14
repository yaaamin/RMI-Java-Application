/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sun;

/**
 *
 * @author salma
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;

public class Receipt implements Serializable{
    public ArrayList<Integer> itemIds;
    public double amount;
    Date timestamp;

    public Receipt(ArrayList<Integer> itemIds, double amount) {
        this.itemIds = itemIds;
        this.amount = amount;
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
    }
}
