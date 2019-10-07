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
    
	private ModelCustomers tmpCustomers = null;
        private ArrayList<ModelContacts> tmpContactsList = null;
        private ModelContacts tmpContact = null;
        private DBConnect dbconnect = new DBConnect();
        
   
    
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
                //This function is called whenever starting tag is encountered. It checks what value it will contain and sets corrensponding flag
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
                    //this is for other, unknown methods of contact
                        tmpContact = new ModelContacts();
			bOther = true;
		}
                
                
	}}}}}}}}}
        
        
    @Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//this one is called whenever closing tag is encountered, im using it to check if closing tag for contacts is present, 
                //and if its true - save data to database as its ending tag for one person
            if(qName.equalsIgnoreCase("contacts")){
                
            try{
            dbconnect.saveData(tmpCustomers, tmpContactsList);
            }catch(Exception e){
            
            }
            }

            
            
	}
    
    
    public void characters(char ch[], int start, int length) throws SAXException {
                //this one reads contents that are present between opening and closing tag
                    String a = new String(ch, start, length);
		if (bName) {
                        tmpCustomers.setName(a);
			bName = false;
		}

		if (bSurname) {
                       tmpCustomers.setSurname(a);
			bSurname = false;
		}

		if (bAge) {
                       tmpCustomers.setAge(Integer.parseInt(a));
			bAge = false;
		}

		if (bCity) {
                       tmpCustomers.setCity(a);
			bCity = false;
		}
                if (bEmail) {
                       tmpContact.setType(1);
                       tmpContact.setContact(a);
                       tmpContactsList.add(tmpContact);
			bEmail = false;
		}
                if (bPhone) {
                       tmpContact.setType(2);
                       a=a.replace(" ", "");
                       tmpContact.setContact(a);
                       tmpContactsList.add(tmpContact);
			bPhone = false;
		}
                if (bJabber) {
                       tmpContact.setType(3);
                       tmpContact.setContact(a);
                       tmpContactsList.add(tmpContact);
                        bJabber = false;
		}
                if (bOther) {
                       tmpContact.setType(0);
                       tmpContact.setContact(a);
                       tmpContactsList.add(tmpContact);
			bOther = false;
		}

	}
   
    
    
    
}
