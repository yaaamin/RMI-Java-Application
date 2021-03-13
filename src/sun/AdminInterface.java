/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sun;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface AdminInterface extends Remote {
    public String addItem(Item item) throws RemoteException;
    public String modifyItem(Item item, int id) throws RemoteException;
    public String deleteItem(int id) throws RemoteException;
    public ArrayList<Item> readItems() throws RemoteException;
}