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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


public class Main {
       private static Scanner input;
       private static String tmpFileName;
       public static void chooseXML(){
           
       SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    try {
        SAXParser saxParser = saxParserFactory.newSAXParser();
        ParserXML handler = new ParserXML();
        System.out.println("Enter file name/path to file: ");
        tmpFileName = input.next();
        
        saxParser.parse(new File(tmpFileName), handler);
        System.out.println("Success");
    } catch (ParserConfigurationException | SAXException | IOException e) {
        e.printStackTrace();
    }
       
       
       }
       public static void chooseCSV(){
       ParserCSV parCsv = new ParserCSV();
       System.out.println("Enter file name/path to file: ");
       tmpFileName = input.next();
           try {
               parCsv.readCsvFile(tmpFileName);
           } catch (Exception ex) {
               System.out.println(ex);
           }
       System.out.println("Success");
       
       }
       
       
    public static void main(String[] args) {
        input = new Scanner(System.in);
        int in;
        while(true){
        System.out.println("Hello, what type of file would you like to process?");
        System.out.println("1. CSV");
        System.out.println("2. XML");
        System.out.println("3. Exit");
        in = input.nextInt();
        if(in == 1){
        chooseCSV();
        }
        if(in == 2){
        chooseXML();
        
        }
        if(in == 3){
        break;
        }

        }

}

}
