/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summarum.btest;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParserXML extends DefaultHandler { 
    
        private List<ModelCustomers> tmpCustomersList = null;
	private ModelCustomers tmpCustomers = null;
        private ArrayList<ModelContacts> tmpContactsList = null;
        private ModelContacts tmpContact = null;
	private StringBuilder data = null;
        private DBConnect dbconnect = new DBConnect();
        
    public List<ModelCustomers> getTmpCustomerList() {
		return tmpCustomersList;
	}
    
	boolean bName = false;
	boolean bSurname = false;
	boolean bAge = false;
	boolean bCity = false;
        boolean bPhone = false;
        boolean bEmail = false;
        boolean bJabber = false;
        boolean bOther = false;
    
        
    
    
    	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

            
           // System.out.println("Znalezione element: " + qName);
            
            System.out.println("Start Element :" + qName);
                if (qName.equalsIgnoreCase("person")) {
			tmpCustomers = new ModelCustomers();
                        tmpContactsList = new ArrayList<ModelContacts>();
                        tmpContact = new ModelContacts();
		}else{
            
		if (qName.equalsIgnoreCase("name")) {
			bName = true;
		}else{

		if (qName.equalsIgnoreCase("surname")) {
			bSurname = true;
		}else{

		if (qName.equalsIgnoreCase("age")) {
			bAge = true;
		}else{

		if (qName.equalsIgnoreCase("city")) {
			bCity = true;
		}else{
                if (qName.equalsIgnoreCase("phone")) {
                        tmpContact = new ModelContacts();
			bPhone = true;
		}else{
                if (qName.equalsIgnoreCase("email")) {
                        tmpContact = new ModelContacts();
			bEmail = true;
		}else{
                if (qName.equalsIgnoreCase("jabber")) {
                        tmpContact = new ModelContacts();
			bJabber = true;
		}else{
                if (!qName.equalsIgnoreCase("contacts") && !qName.equalsIgnoreCase("persons") ) {
                        tmpContact = new ModelContacts();
			bOther = true;
		}
                
                
	}}}}}}}}}
        
        
    @Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
            if(qName.equalsIgnoreCase("contacts")){
                
            try{
            dbconnect.saveData(tmpCustomers, tmpContactsList);
            }catch(Exception e){
            
            }
            }
            //System.out.println("End Element :" + qName);
            
            
	}
    
    
    public void characters(char ch[], int start, int length) throws SAXException {
                    String a = new String(ch, start, length);
		if (bName) {
                        tmpCustomers.setName(a);
			System.out.println("Name : " + tmpCustomers.getName());
			bName = false;
		}

		if (bSurname) {
                       tmpCustomers.setSurname(a);
			System.out.println("Surname : " +  tmpCustomers.getSurname());
			bSurname = false;
		}

		if (bAge) {
                       tmpCustomers.setAge(Integer.parseInt(a));
                       System.out.println("Age : " +  tmpCustomers.getAge());
			bAge = false;
		}

		if (bCity) {
                       tmpCustomers.setCity(a);
			System.out.println("City : " + tmpCustomers.getCity());
			bCity = false;
		}
                if (bEmail) {
                       tmpContact.setType(1);
                       tmpContact.setContact(a);
                       tmpContactsList.add(tmpContact);
			System.out.println("Contact : "+tmpContact.getContact());
			bEmail = false;
		}
                if (bPhone) {
                       tmpContact.setType(2);
                       a=a.replace(" ", "");
                       tmpContact.setContact(a);
                       tmpContactsList.add(tmpContact);
			System.out.println("Contact : " +  tmpContact.getContact());
			bPhone = false;
		}
                if (bJabber) {
                       tmpContact.setType(3);
                       tmpContact.setContact(a);
                       tmpContactsList.add(tmpContact);
			System.out.println("Jabber : " + tmpContact.getContact());
			bJabber = false;
		}
                if (bOther) {
                       tmpContact.setType(0);
                       tmpContact.setContact(a);
                       tmpContactsList.add(tmpContact);
			System.out.println("OTHER : " + tmpContact.getContact());
			bOther = false;
		}

	}
   
    
    
    
}
