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
import java.util.ArrayList;
/**
 *
 * @author salma
 */
public class Admin implements Serializable{
    
    private String firstName;
    private String lastName;
    private String passportNumber;
    
    public Admin(){}
    
    public Admin(String firstName, String lastName, String passportNumber){
    this.firstName = firstName;
    this.lastName = lastName;
    this.passportNumber = passportNumber;
    }
    
    public String getFirstName(){
    
        return this.firstName;
        
    }
    
    public String getLastName(){
        
        return this.lastName;
        
    }
    
    public String getPassportNumber(){
    
    return this.passportNumber;
        
    }
    
    public void modifyPassport(String newPassport){
    this.passportNumber = newPassport;
    }
    
}
