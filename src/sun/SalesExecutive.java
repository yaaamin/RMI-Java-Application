/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sun;

import java.io.Serializable;

/**
 *
 * @author salma
 */
public class SalesExecutive implements Serializable{
    
    private String firstName;
    private String lastName;
    private String passportNumber;
    
    public SalesExecutive(){}
    
    public SalesExecutive(String firstName, String lastName, String passportNumber){

    this.firstName = firstName;
    this.lastName = lastName;
    this.passportNumber = passportNumber;
    
    };
    
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
    
     public String recordPurchase() {
        // Record record = new Record();
        return "WIP";
    }
    
}
