/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sun;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author salma
 */
public class Server {
    
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(5984);
            registry.rebind("Accounts", new AccountsImplementation());
            registry.rebind("Admin", new AdminImplementation());
        } catch (RemoteException ex) {
            System.out.println("Remote exception error, try changing ports!");
            ex.printStackTrace();
        }
    }
    
}
