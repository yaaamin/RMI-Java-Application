/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sun;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author salma
 */
public class SalesExecutiveImplementation extends UnicastRemoteObject implements SalesExecutiveInterface, Serializable{

    private ArrayList<Item> Container = new ArrayList<Item>();
    private ArrayList<Receipt> receipts = new ArrayList<Receipt>();

    public SalesExecutiveImplementation() throws RemoteException {

    }

    public void writeToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream("items.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(Container);
            fileOut.close();
            objectOut.close();
        }

        catch (IOException e) {
            System.out.println("Error with text file!");
        }
    }

    public void readFromFile() {
        try {
            FileInputStream fileIn = new FileInputStream("items.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Container = (ArrayList<Item>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
        }

        catch (IOException | ClassNotFoundException e) {
            System.out.println("Error with text file or class!");
        }
    }

    public void makeOrder(ArrayList<Integer> itemIds) throws RemoteException {
        readFromFile();
        Item[] itemList = new Item[Container.size()];
        double total = 0;

        for (int i = 0; i < itemIds.size(); i++) {
            for (int j = 0; j < Container.size(); j++) {
                itemList[j] = Container.get(j);
                if (itemList[i].id == itemIds.get(i)) {
                    itemList[i].stock--;
                    total += itemList[i].price;
                    break;
                }
            }
        }
        writeToFile();
        
        Receipt receipt = new Receipt(itemIds, total);
        readReceiptFromFile();
        receipts.add(receipt);
        writeReceiptToFile();
    }

    public void writeReceiptToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream("receipts.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(receipts);
            fileOut.close();
            objectOut.close();
        }

        catch (IOException e) {
            System.out.println("Error with text file!");
        }
    }

    public void readReceiptFromFile() {
        try {
            FileInputStream fileIn = new FileInputStream("receipts.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            receipts = (ArrayList<Receipt>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
        }

        catch (IOException | ClassNotFoundException e) {
            System.out.println("Error with text file or class!");
        }
    }

    public ArrayList<Receipt> getReceipts() throws RemoteException {
        readReceiptFromFile();
        return receipts;
    }

    public Item findItem(int itemId) {
        readFromFile();
        
        Item[] itemList = new Item[Container.size()];
        for(int i = 0; i < Container.size(); i++) {
            
            itemList[i] = Container.get(i);
            if(itemList[i].id == itemId) {
                if (itemList[i].isDeleted) {
                    break;
                }
                return itemList[i];
            }
        }
        return null;
    }

    public double calculatePrice(int itemId, int quantity) throws RemoteException {
        Item item = findItem(itemId);
        if (quantity > item.stock) {
            return -1;
        }
        return item.price * quantity;
    }

    public String decreaseStock(ArrayList<Integer> itemIds, ArrayList<Integer> quantities) throws RemoteException {
       
        for (int i = 0; i < itemIds.size(); i++) {
             System.out.println("ItemID being passed: " + itemIds.get(i) + " at quantity of: " + quantities.get(i));
        }
         
       ArrayList<Integer> successfulIds = new ArrayList<Integer>();
        readFromFile();

        for (int siz = 0; siz < itemIds.size(); siz++) {
            Item[] itemList = new Item[Container.size()];
            for(int i = 0; i < Container.size(); i++) {
                
                itemList[i] = Container.get(i);
                if(itemList[i].id == itemIds.get(siz)) {
                    if (itemList[i].isDeleted) {
                        break;
                    }
                    if (quantities.get(siz) > itemList[i].stock) {
                        break;
                    }
                    Container.get(i).stock -= quantities.get(siz);
                    // itemList[i].stock -= quantities.get(siz);
                    successfulIds.add(itemList[i].id);
                    break;
                }
            }
            
        }
        
        for (int i = 0; i < successfulIds.size(); i++) {
            System.out.println(successfulIds.get(i));
        }
        
        writeToFile();
        String itemName = " ";
        
        try(FileWriter fw = new FileWriter("Receipts.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
        
        {
            out.println("Order conducted at: " + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
            for (int i = 0; i < itemIds.size(); i++) {
                for (int j = 0; j < Container.size(); j++) {
                    if(Container.get(j).id == itemIds.get(i)){
                        itemName = Container.get(j).name;
                        break;
                    }
                }
                
                out.println("Item purchased: " + itemName);
                out.println("Item ID: " + itemIds.get(i));
                out.println("Item quantity: " + quantities.get(i));
                
            }
            
             out.println("____________________________");
 
        } catch (IOException e) {
    
        }
        
        return "Sucess!";
    }
}
