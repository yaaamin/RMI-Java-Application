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

/**
 *
 * @author salma
 */
public class AccountsImplementation extends UnicastRemoteObject implements AccountsInterface, Serializable{
    
    public AccountsImplementation() throws RemoteException{
    
    }
    
    private ArrayList <User> Container = new ArrayList <User>(); 
    
    private void readFromFile(){
    try{
         FileInputStream fileIn = new FileInputStream("Users.txt");
         ObjectInputStream objectIn = new ObjectInputStream(fileIn);
         Container = (ArrayList<User>) objectIn.readObject();
         objectIn.close();
         fileIn.close();
     }
     
     catch(IOException | ClassNotFoundException e){
         System.out.println("Error with text file or class!");
     }    
    }
    
    private void writeToFile(){
     
     try{
         FileOutputStream fileOut = new FileOutputStream("Users.txt");
         ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
         objectOut.writeObject(Container);
         fileOut.close();
         objectOut.close();
     }
     
     catch(IOException e){
         System.out.println("Error with text file!");
 }
    }
    
    public String add(User user){
     readFromFile();
     
     boolean isFound = false;
     
     User[] userList = new User[Container.size()];
     
     for(int i = 0; i < Container.size(); i++){
         
         userList[i] = Container.get(i);
         
         if(userList[i] == user){
             isFound = true;
             break;
            }
     }
     
     if(isFound == true){
     
         return "Failure to add new user, probably already exists! Try logging in with username and passport numeber!";
         
     }
     
     
     Container.add(user);
     writeToFile();
     
     return "Successfully added!";
    }
    
    public String login(String name, String passport) throws RemoteException{
    
    readFromFile();
    String[] firstandlastNames = name.split(" ");
    
     boolean isFound = false;
     
     User[] userList = new User[Container.size()];
     
     for(int i = 0; i < Container.size(); i++){
         
         userList[i] = Container.get(i);
         
         if(userList[i].getFirstName().equals(firstandlastNames[0]) && (userList[i].getLastName().equals(firstandlastNames[1]) && (userList[i].getPassportNumber().equals(passport)))){
             isFound = true;
             break;
            }
     }
     
        if(isFound == true){
        return "Logged in successfully!";
        }
        
        return "No account found with given username and passport number!";
    }
    
    
}
