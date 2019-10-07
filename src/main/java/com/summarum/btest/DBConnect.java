/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summarum.btest;
import java.sql.*;
import java.util.ArrayList;

public class DBConnect {
    private static Connection con = null;
    public static void connectToDatabase() throws Exception {
    	
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/btest","root","123456789"); 
    	} catch (Exception e) {
            throw e;
        }
    	
    }
    
    
    
    public void saveData(ModelCustomers customers, ArrayList<ModelContacts> contacts) throws Exception{
        int customerId = 0;
    try{
        connectToDatabase();
        
        Statement stmt=con.createStatement();  
        ResultSet rs=stmt.executeQuery("select * from CUSTOMERS WHERE NAME='jan' AND SURNAME = 'kowalski'");  
       if(customers.age != 0){
        stmt.executeUpdate("INSERT INTO CUSTOMERS (NAME, SURNAME, AGE) VALUES ('"+customers.getName()+"', '"+customers.getSurname()+"', '"+customers.getAge()+"')",Statement.RETURN_GENERATED_KEYS);
       }else{
       stmt.executeUpdate("INSERT INTO CUSTOMERS (NAME, SURNAME, AGE) VALUES ('"+customers.getName()+"', '"+customers.getSurname()+"', NULL)",Statement.RETURN_GENERATED_KEYS);
       
       
       }
        rs = stmt.getGeneratedKeys();
        if (rs.next()){
        customerId = rs.getInt(1);
 
        }
        for(int i = 0; i < contacts.size();i++){
         stmt.executeUpdate("INSERT INTO CONTACTS (ID_CUSTOMER, TYPE, CONTACT) VALUES ('"+customerId+"', '"+contacts.get(i).getType()+"', '"+contacts.get(i).getContact()+"')");
        
                 }
        
        con.close();  
        
        
    }catch(ClassNotFoundException | SQLException e){
    System.out.println(e);
    System.out.println("Operation failed");
    
    }
    
    
    
    
    }
    
    
    
    
    
}
