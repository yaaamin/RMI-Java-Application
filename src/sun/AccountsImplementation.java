/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sun;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author salma
 */
public class AccountsImplementation extends UnicastRemoteObject implements AccountsInterface, Serializable{
    
    public AccountsImplementation() throws RemoteException{
    
    }
    
    private ArrayList <Admin> AdminContainer = new ArrayList <Admin>(); 
    private ArrayList <SalesExecutive> SalesExecutiveContainer = new ArrayList <SalesExecutive>();
    
    private void readFromFileAdmin(){
    try{
         FileInputStream fileIn = new FileInputStream("Admin.txt");
         ObjectInputStream objectIn = new ObjectInputStream(fileIn);
         AdminContainer = (ArrayList<Admin>) objectIn.readObject();
         objectIn.close();
         fileIn.close();
     }
     
     catch(IOException | ClassNotFoundException e){
         e.printStackTrace();
     }    
    }
    
    private void writeToFileAdmin(){
     
     try{
         FileOutputStream fileOut = new FileOutputStream("Admin.txt");
         ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
         objectOut.writeObject(AdminContainer);
         fileOut.close();
         objectOut.close();
     }
     
     catch(IOException e){
         e.printStackTrace();
 }
    }
    
    private void readFromFileSalesExecutive(){
    try{
         FileInputStream fileIn = new FileInputStream("SalesExecutive.txt");
         ObjectInputStream objectIn = new ObjectInputStream(fileIn);
         SalesExecutiveContainer = (ArrayList<SalesExecutive>) objectIn.readObject();
         objectIn.close();
         fileIn.close();
     }
     
     catch(IOException | ClassNotFoundException e){
         e.printStackTrace();
     }    
    }
    
    private void writeToFileSalesExecutive(){
     
     try{
         FileOutputStream fileOut = new FileOutputStream("SalesExecutive.txt");
         ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
         objectOut.writeObject(SalesExecutiveContainer);
         fileOut.close();
         objectOut.close();
     }
     
     catch(IOException e){
         e.printStackTrace();
 }
    }
    
    //Whenever calling the securePassport function, use the salt 'A321'.
    private String securePassport(String passport, String salt){
    
        String generatedPassword = null;
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = md.digest(passport.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }
    return generatedPassword;
    
    }
    
    @Override
    public String addAdmin(Admin Admin) throws RemoteException{
     readFromFileAdmin();
     
     String hash = securePassport(Admin.getPassportNumber(), "A321");
     
     boolean isFound = false;
     
     Admin[] adminList = new Admin[AdminContainer.size()];
     
     for(int i = 0; i < AdminContainer.size(); i++){
         
         adminList[i] = AdminContainer.get(i);
         if(adminList[i].getFirstName().equals(Admin.getFirstName()) && (adminList[i].getLastName().equals(Admin.getLastName()) && (adminList[i].getPassportNumber().equals(hash)))){
             isFound = true;
             break;
            }
     }
     
     if(isFound == true){
     
         return "Failure to add new user, probably already exists! Try logging in with username and passport numeber!";
     }
     
     Admin.modifyPassport(securePassport(Admin.getPassportNumber(), "A321"));
     AdminContainer.add(Admin);
     writeToFileAdmin();
     
     return "Successfully added!";
    }
    
    @Override
    public String addSalesExecutive(SalesExecutive SalesExecutive) throws RemoteException {
     readFromFileSalesExecutive();
     
     String hash = securePassport(SalesExecutive.getPassportNumber(), "A321");
     
     boolean isFound = false;
     
     SalesExecutive[] SalesExecutiveList = new SalesExecutive[SalesExecutiveContainer.size()];
     
     for(int i = 0; i < SalesExecutiveContainer.size(); i++){
         
         SalesExecutiveList[i] = SalesExecutiveContainer.get(i);
         if(SalesExecutiveList[i].getFirstName().equals(SalesExecutive.getFirstName()) && (SalesExecutiveList[i].getLastName().equals(SalesExecutive.getLastName()) && (SalesExecutiveList[i].getPassportNumber().equals(hash)))){
             isFound = true;
             break;
            }
     }
     
     if(isFound == true){
     
         return "Failure to add new user, probably already exists! Try logging in with username and passport numeber!";
     }
     
     SalesExecutive.modifyPassport(securePassport(SalesExecutive.getPassportNumber(), "A321"));
     SalesExecutiveContainer.add(SalesExecutive);
     writeToFileSalesExecutive();
     
     return "Successfully added!";  
    }
    
    @Override
    public String login(String name, String passport) throws RemoteException{
    
    readFromFileAdmin();
    String[] firstandlastNames = name.split(" ");
    
     int i;
     boolean isFoundAdmin = false;
     boolean isFoundSalesExecutive = false;
     
     passport = securePassport(passport, "A321");
     
     Admin[] adminList = new Admin[AdminContainer.size()];
     
     for(i = 0; i < AdminContainer.size(); i++){
         
         adminList[i] = AdminContainer.get(i);
         
         if(adminList[i].getFirstName().equals(firstandlastNames[0]) && (adminList[i].getLastName().equals(firstandlastNames[1]) && (adminList[i].getPassportNumber().equals(passport)))){
             isFoundAdmin = true;
             break;
            }
     }
     
        if(isFoundAdmin == true){
        return "Logged in successfully as admin!";
        }
        
        readFromFileSalesExecutive();
        
        SalesExecutive[] SalesExecutiveList = new SalesExecutive[SalesExecutiveContainer.size()];
        
        for (i = 0; i < SalesExecutiveContainer.size(); i++) {
            SalesExecutiveList[i] = SalesExecutiveContainer.get(i);
        
        
        if(SalesExecutiveList[i].getFirstName().equals(firstandlastNames[0]) && (SalesExecutiveList[i].getLastName().equals(firstandlastNames[1]) && (SalesExecutiveList[i].getPassportNumber().equals(passport)))){
        
            isFoundSalesExecutive = true;
            break;
        }
            }
        
        if(isFoundSalesExecutive == true){
        return "Logged in successfully as Sales Executive!";
        }
        
        return "No account found with given username and passport number!";
    }
    
}
