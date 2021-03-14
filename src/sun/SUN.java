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
     public static SalesExecutiveInterface salesexecutive;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Server server = new Server();
        server.main(new String[0]);
        
         try {
             auth = (AccountsInterface) Naming.lookup("rmi://localhost:5984/Accounts");
             admin = (AdminInterface) Naming.lookup("rmi://localhost:5984/Admin");
             salesexecutive = (SalesExecutiveInterface) Naming.lookup("rmi://localhost:5984/SalesExecutive");
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
