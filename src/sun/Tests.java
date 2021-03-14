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
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tests {
    public static AccountsInterface accountsInterface;
    public static AdminInterface adminInterface;
    public static SalesExecutiveInterface salesExecutiveInterface;

    public static void main(String args[]) {
        String path = "rmi://localhost:" + Server.port;
        try {
            accountsInterface = (AccountsInterface) Naming.lookup(path + "/Accounts");
        } catch (NotBoundException ex) {
            Logger.getLogger(SUN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(SUN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(SUN.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (accountsInterface != null) {
            System.out.println("#1: " + path + "/Accounts - SUCCESS");
        } else {
            System.out.println("#1: " + path + "/Accounts - FAILURE");
        }

        try {
            adminInterface = (AdminInterface) Naming.lookup(path + "/Admin");
        } catch (NotBoundException ex) {
            Logger.getLogger(SUN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(SUN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(SUN.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (adminInterface != null) {
            System.out.println("#2: " + path + "/Admin - SUCCESS");
        } else {
            System.out.println("#2: " + path + "/Admin - FAILURE");
        }

        try {
            salesExecutiveInterface = (SalesExecutiveInterface) Naming.lookup(path + "/SalesExecutive");
        } catch (NotBoundException ex) {
            Logger.getLogger(SUN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(SUN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(SUN.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (salesExecutiveInterface != null) {
            System.out.println("#3: " + path + "/Sales - SUCCESS");
        } else {
            System.out.println("#3: " + path + "/Sales - FAILURE");
        }

    }
}
