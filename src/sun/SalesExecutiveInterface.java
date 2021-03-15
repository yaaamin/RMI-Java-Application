/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sun;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
/**
 *
 * @author salma
 */
public interface SalesExecutiveInterface extends Remote{
    public ArrayList<Receipt> getReceipts() throws RemoteException;
    public double calculatePrice(int itemId, int quantity) throws RemoteException;
    public String decreaseStock(ArrayList<Integer> itemIds, ArrayList<Integer> quantities) throws RemoteException;
}
