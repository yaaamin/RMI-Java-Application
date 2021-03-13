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
    private ArrayList <Salesperson> SalespersonContainer = new ArrayList <Salesperson>();
    
    private void readFromFileAdmin(){
    try{
         FileInputStream fileIn = new FileInputStream("Admin.txt");
         ObjectInputStream objectIn = new ObjectInputStream(fileIn);
         AdminContainer = (ArrayList<Admin>) objectIn.readObject();
         objectIn.close();
         fileIn.close();
     }
     
     catch(IOException | ClassNotFoundException e){
         System.out.println("Error with text file or class!");
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
         System.out.println("Error with text file!");
 }
    }
    
    private void readFromFileSalesperson(){
    try{
         FileInputStream fileIn = new FileInputStream("Salesperson.txt");
         ObjectInputStream objectIn = new ObjectInputStream(fileIn);
         SalespersonContainer = (ArrayList<Salesperson>) objectIn.readObject();
         objectIn.close();
         fileIn.close();
     }
     
     catch(IOException | ClassNotFoundException e){
         System.out.println("Error with text file or class!");
     }    
    }
    
    private void writeToFileSalesperson(){
     
     try{
         FileOutputStream fileOut = new FileOutputStream("Salesperson.txt");
         ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
         objectOut.writeObject(SalespersonContainer);
         fileOut.close();
         objectOut.close();
     }
     
     catch(IOException e){
         System.out.println("Error with text file!");
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
     
     Admin.modifyPassport(securePassport(Admin.getPassportNumber(), "A321"));
     
     boolean isFound = false;
     
     Admin[] adminList = new Admin[AdminContainer.size()];
     
     for(int i = 0; i < AdminContainer.size(); i++){
         
         adminList[i] = AdminContainer.get(i);
         if(adminList[i].getFirstName().equals(Admin.getFirstName()) && (adminList[i].getLastName().equals(Admin.getLastName()) && (adminList[i].getPassportNumber().equals(Admin.getPassportNumber())))){
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
    public String addSalesperson(Salesperson Salesperson) throws RemoteException {
     readFromFileSalesperson();
     
     Salesperson.modifyPassport(securePassport(Salesperson.getPassportNumber(), "A321"));
     
     boolean isFound = false;
     
     Salesperson[] salespersonList = new Salesperson[SalespersonContainer.size()];
     
     for(int i = 0; i < SalespersonContainer.size(); i++){
         
         salespersonList[i] = SalespersonContainer.get(i);
         if(salespersonList[i].getFirstName().equals(Salesperson.getFirstName()) && (salespersonList[i].getLastName().equals(Salesperson.getLastName()) && (salespersonList[i].getPassportNumber().equals(Salesperson.getPassportNumber())))){
             isFound = true;
             break;
            }
     }
     
     if(isFound == true){
     
         return "Failure to add new user, probably already exists! Try logging in with username and passport numeber!";
     }
     
     Salesperson.modifyPassport(securePassport(Salesperson.getPassportNumber(), "A321"));
     SalespersonContainer.add(Salesperson);
     writeToFileSalesperson();
     
     return "Successfully added!";  
    }
    
    @Override
    public String login(String name, String passport) throws RemoteException{
    
    readFromFileAdmin();
    String[] firstandlastNames = name.split(" ");
    
     int i;
     boolean isFoundAdmin = false;
     boolean isFoundSalesperson = false;
     
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
        return "Logged in successfully as admin!" + i;
        }
        
        readFromFileSalesperson();
        
        Salesperson[] salespersonList = new Salesperson[SalespersonContainer.size()];
        
        for (i = 0; i < SalespersonContainer.size(); i++) {
            salespersonList[i] = SalespersonContainer.get(i);
        
        
        if(salespersonList[i].getFirstName().equals(firstandlastNames[0]) && (salespersonList[i].getLastName().equals(firstandlastNames[1]) && (salespersonList[i].getPassportNumber().equals(passport)))){
        
            isFoundSalesperson = true;
            break;
        }
        
        if(isFoundSalesperson == true){
        return "Logged in successfully as salesperson!";
        }
        }
        
        return "No account found with given username and passport number!";
    }
    
}
