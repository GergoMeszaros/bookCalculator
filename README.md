This simple maven project follows the structure of the required document presented by World of Books.

Necessary properties such as database and ftp parameters, api endpoints, filenames and extensions collected in a single config.properties file.
These values are replaceable. The only purpose I left them filled,to show that I have a working configuration.
Please find the data structure of the project on the following link: https://lucid.app/documents/embeddedchart/6a95d1ab-bf74-47ba-9547-cc30442edcbd

Packages and classes have separated according to their duties. 

The program's workflow: 

- getting data from the apis
- turn the response string into the appropriate objects via Gson library
- in case the type of objects are instance of Listing, they are passed to the ApiDataValidator class which creates a report about invalid fields into csv format. That follows the separation of the objects. Only those will be forwarded that can be valid database entities.
- at this point the program checks if any from the required tables exist, if so they will be dropped and recreated.
- the following operation is the insertion of objects into the database via JDBC
- creating database queries to create Report objects (the actual calculation for reporting has made by the database) 
- turning Report objects into .json file through the ReportToJsonWriter class
- creating an FTP connection and upload the .json file into the required server


Technologies used:

- Java 16
- JDBC
- Maven
- Junit 5
- Mockito
- Gson and Apache libraries
- MySQL 8.0.25
