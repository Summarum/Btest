# Btest
Test program reading files and saving results in mysql database

in order for it to work you should change login and password for jdbc - its located in Btest/src/main/java/com/summarum/btest/DBConnect.java. Provided one is just an example and shouldnt be used.
The same applies for database url - this one should be changed aswell.
MySQL database used in this program was named btest, and scripts for both tables is below:

CREATE TABLE IF NOT EXISTS CUSTOMERS (ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255) NOT NULL, SURNAME VARCHAR(255) NOT NULL, AGE INT DEFAULT NULL);

CREATE TABLE IF NOT EXISTS CONTACTS (ID INT AUTO_INCREMENT PRIMARY KEY, ID_CUSTOMER INT NOT NULL,TYPE INT(1) NOT NULL CHECK(TYPE >= 1 AND TYPE <= 3), CONTACT VARCHAR(255) NOT NULL, FOREIGN KEY (ID_CUSTOMER) REFERENCES CUSTOMERS (ID));

Main.java contains main menu loop.
DBConnect.java contains everything regarding database usage (writing data to it).
ModelContacts and ModelCustomers contains vital information used later to save records to database.
ParserCSV contains logic behind reading csv files and preparing them to be saved in the database.
ParserXML contains logic behind reading XML files and preparing them to be saved in the database.

JDBC was used as connector with mySQL database.
SAX was used to read XML files - reason behind this is the requirement of parser being able to read huge files as it doesnt read them as whole.




Main known problem is that CSV parser might have problems with distinguishing other contacts methods than phone and email since jabber (to my knowledge) username can contain letters and numbers. This is reason why its hard to check if said contact method is, for example, icq number or even skype username. It would be advised to add further information into files that are going to be read, but that involves changes in parser structure and can pose problems for older files that were created using this template.

# USAGE
Main menu consists of 3 options - reading csv file, reading xml file and exit.
In order to read file user needs to write down path to file.
