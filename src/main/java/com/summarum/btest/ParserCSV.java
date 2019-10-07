/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summarum.btest;

import com.mysql.cj.util.StringUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class ParserCSV {
    public ModelCustomers populateCustomers(String[] data){
        ModelCustomers customer = new ModelCustomers();
        customer.setName(data[0]);
        customer.setSurname(data[1]);
        if(data[2].isEmpty()){
        customer.setAge(0);
        }else{
        customer.setAge(Integer.parseInt(data[2]));
        }
        customer.setCity(data[3]);
    
    return customer;
    }
    
    public ArrayList<ModelContacts> populateContacts(String[] data){
        ArrayList<ModelContacts> userContacts = new ArrayList <ModelContacts>();
        ModelContacts contact;
        for(int i = 4; i < data.length; i++){
            if(data[i].contains("@")){
                contact = new ModelContacts();
                contact.setType(1);
                contact.setContact(data[i]);
                userContacts.add(contact);
                continue;
            }
            
            //temporary string for checking if its phone number we're dealing
            String temp;
            temp = data[i].replaceAll(" ", "");
            
            if(StringUtils.isStrictlyNumeric(temp) && temp.length() == 9){
            contact  = new ModelContacts();
            contact.setType(2);
            contact.setContact(temp);
            userContacts.add(contact);
            continue;
            }
            
            contact  = new ModelContacts();
            contact.setType(3);
            contact.setContact(data[i]);
            userContacts.add(contact);
        }
        
        
        
        return userContacts;
    }
    public void readCsvFile(){
        ModelCustomers cust;
        ArrayList<ModelContacts> contactsList;
        DBConnect dbconnect = new DBConnect();
    File file = new File("dane-osoby.csv");
    String[] tempDataTable;
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        
        String line;
        while ((line = br.readLine()) != null) {
            
            tempDataTable = line.split(",");
            System.out.println(line);
           cust = populateCustomers(tempDataTable);
           contactsList = populateContacts(tempDataTable);
           dbconnect.saveData(cust, contactsList);
        }
    }catch(Exception e){


    }
    
    
    
    }
    
    
    
}
