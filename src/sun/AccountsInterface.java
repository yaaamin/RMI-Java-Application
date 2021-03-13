/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sun;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author salma
 */
public interface AccountsInterface extends Remote{
    
     public String addAdmin(Admin Admin) throws RemoteException;
     public String addSalesExecutive(SalesExecutive SalesExecutive) throws RemoteException;
     public String login(String name, String passport) throws RemoteException;
}
