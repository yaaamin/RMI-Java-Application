/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sun;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salma
 */
public class SUN {

     public static AccountsInterface auth;
     public static AdminInterface admin;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try {
             auth = (AccountsInterface) Naming.lookup("rmi://localhost:5984/Accounts");
             admin = (AdminInterface) Naming.lookup("rmi://localhost:5984/Admin");
             // TODO code application logic here  
         } catch (NotBoundException ex) {
             Logger.getLogger(SUN.class.getName()).log(Level.SEVERE, null, ex);
         } catch (MalformedURLException ex) {
             Logger.getLogger(SUN.class.getName()).log(Level.SEVERE, null, ex);
         } catch (RemoteException ex) {
             Logger.getLogger(SUN.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         Login startScreen = new Login();
         startScreen.setVisible(true);
        
    }
    
}
