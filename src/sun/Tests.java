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
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String args[]) {
        String path = "rmi://localhost:" + Server.port;
        String dummyPassportAdmin = "0779281923";
        String dummyPassportSalesExecutive = "1237892792";

        /////////////////////////////////////////////////////////////////////////////////// Authorization Testing

        // Test #1
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
            System.out.println("#1: Connecting to server " + path + "/Accounts - " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        } else {
            System.out.println("#1: Connecting to server " + path + "/Accounts - " + ANSI_RED + "FAILED" + ANSI_RESET);
        }

        // Test #2

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
            System.out.println("#2: Connecting to server " + path + "/Admin - " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        } else {
            System.out.println("#2: Connecting to server " + path + "/Admin - " + ANSI_RED + "FAILED" + ANSI_RESET);
        }

        // Test #3

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
            System.out.println("#3: Connecting to server " + path + "/Sales - " + ANSI_GREEN + "PASSED" + ANSI_RESET);
        } else {
            System.out.println("#3: Connecting to server " + path + "/Sales - " + ANSI_RED + "FAILED" + ANSI_RESET);
        }

        // Test #4

        Admin admin = new Admin("Ahmed", "Qaid", dummyPassportAdmin);
        try {
            String msg = accountsInterface.addAdmin(admin);
            if (msg.equals("Successfully added!")) {
                System.out.println("#4: Registering Admin - " + ANSI_GREEN + "PASSED" + ANSI_RESET);
            } else {
                System.out.println("#4: Registering Admin - " + ANSI_RED + "FAILED" + ANSI_RESET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Test #5

        try {
            String msg = accountsInterface.login("Ahmed Qaid", dummyPassportAdmin);
            if (msg.equals("Logged in successfully as admin!")) {
                System.out.println("#5: Admin Login - " + ANSI_GREEN + "PASSED" + ANSI_RESET);
            } else {
                System.out.println("#5: Admin Login - " + ANSI_RED + "FAILED" + ANSI_RESET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Test #6
        
        Admin adminDup = new Admin("Ahmed", "Qaid", dummyPassportAdmin);
        try {
            String msg = accountsInterface.addAdmin(adminDup);
            if (msg.equals("Failure to add new user, probably already exists! Try logging in with username and passport number!")) {
                System.out.println("#6: Preventing Registration Dupliaction - " + ANSI_GREEN + "PASSED" + ANSI_RESET);
            } else {
                System.out.println("#6: Preventing Registration Dupliaction - " + ANSI_RED + "FAILED" + ANSI_RESET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Test #7

        try {
            String msg = accountsInterface.login("Ahmed Test", "127928921");
            if (msg.equals("No account found with given username and passport number!")) {
                System.out.println("#7: Preventing Unauthorized Login - " + ANSI_GREEN + "PASSED" + ANSI_RESET);
            } else {
                System.out.println("#7: Preventing Unauthorized Login - " + ANSI_RED + "FAILED" + ANSI_RESET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Test #8

        try {
            SalesExecutive se = new SalesExecutive("Salman", "Ahmed", dummyPassportSalesExecutive);
            String msg = accountsInterface.addSalesExecutive(se);
            if (msg.equals("Successfully added!")) {
                System.out.println("#8: Adding Sales Executive - " + ANSI_GREEN + "PASSED" + ANSI_RESET);
            } else {
                System.out.println("#8: Adding Sales Executive - " + ANSI_RED + "FAILED" + ANSI_RESET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Test #9

        try {
            SalesExecutive se = new SalesExecutive("Salman", "Ahmed", dummyPassportSalesExecutive);
            String msg = accountsInterface.addSalesExecutive(se);
            if (msg.equals("Failure to add new user, probably already exists! Try logging in with username and passport number!")) {
                System.out.println("#9: Preventing Sales Executive Dupliaction - " + ANSI_GREEN + "PASSED" + ANSI_RESET);
            } else {
                System.out.println("#9: Preventing Sales Executive Dupliaction - " + ANSI_RED + "FAILED" + ANSI_RESET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Test #10

        try {
            String msg = accountsInterface.login("Salman Ahmed", dummyPassportSalesExecutive);
            if (msg.equals("Logged in successfully as Sales Executive!")) {
                System.out.println("#10: Sales Executive Login - " + ANSI_GREEN + "PASSED" + ANSI_RESET);
            } else {
                System.out.println("#10: Sales Executive Login - " + ANSI_RED + "FAILED" + ANSI_RESET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /////////////////////////////////////////////////////////////////////////////////// Item CRUD Testing (Admin)

        // Test #11

        Integer itemId = -1;
        try {
            Item item = new Item("Sample", 20.5, 100);
            itemId = adminInterface.addItem(item);
            if (itemId != null) {
                System.out.println("#11: Adding Item - " + ANSI_GREEN + "PASSED" + ANSI_RESET);
            } else {
                System.out.println("#11: Adding Item - " + ANSI_RED + "FAILED" + ANSI_RESET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Test #12

        try {
            Item item = new Item("Sample Modified", 10.3, 50);
            String msg = adminInterface.modifyItem(item, itemId);
            if (msg.equals("Successfully modified!")) {
                System.out.println("#12: Modifying Item - " + ANSI_GREEN + "PASSED" + ANSI_RESET);
            } else {
                System.out.println("#12: Modifying Item - " + ANSI_RED + "FAILED" + ANSI_RESET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Test #13

        try {
            String msg = adminInterface.deleteItem(itemId);
            if (msg.equals("Successfully deleted!")) {
                System.out.println("#13: Deleting Item - " + ANSI_GREEN + "PASSED" + ANSI_RESET);
            } else {
                System.out.println("#13: Deleting Item - " + ANSI_RED + "FAILED" + ANSI_RESET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Test #14

        try {
            ArrayList<Item> msg = adminInterface.readItems();
            if (! msg.isEmpty()) {
                System.out.println("#14: Reading Items List - " + ANSI_GREEN + "PASSED" + ANSI_RESET);
            } else {
                System.out.println("#14: Reading Items List - " + ANSI_RED + "FAILED" + ANSI_RESET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /////////////////////////////////////////////////////////////////////////////////// Order Testing (Sales Executive)

        // Test #15

        try {
            ArrayList<Integer> itemIds = new ArrayList<Integer>();
            itemIds.add(itemId);
            ArrayList<Integer> quantities = new ArrayList<Integer>();
            quantities.add(3);
            String msg = salesExecutiveInterface.decreaseStock(itemIds, quantities);
            if (msg.equals("Success!")) {
                System.out.println("#15: Making Order - " + ANSI_GREEN + "PASSED" + ANSI_RESET);
            } else {
                System.out.println("#15: Making Order - " + ANSI_RED + "FAILED" + ANSI_RESET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
