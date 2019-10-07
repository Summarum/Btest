/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summarum.btest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


public class Main {
       
    public static void main(String[] args) {
     /*   DBConnect connect = new DBConnect();
        ModelCustomers modeltest = new ModelCustomers();
        modeltest.setName("Waclaw");
        modeltest.setSurname("Januszowski");
        modeltest.setAge(20);
        modeltest.setCity("Lublin");
        connect.connectToDB(modeltest);
        */
     
     //ParserCSV parCsv = new ParserCSV();
    // parCsv.readCsvFile();
     
    // ParsXML parser = new ParsXML();
    // parser.parssXML();
     
     SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    try {
        SAXParser saxParser = saxParserFactory.newSAXParser();
        ParserXML handler = new ParserXML();
        saxParser.parse(new File("dane-osoby.xml"), handler);
        //Get Employees list
       // List<ModelCustomers> empList = handler.getTmpCustomerList();
        //print employee information
       // for(ModelCustomers emp : empList)
         //   System.out.println(emp);
    } catch (ParserConfigurationException | SAXException | IOException e) {
        e.printStackTrace();
    }
      
}

}
