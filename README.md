This simple maven project follows the structure of the required document presented by World of Books.

Necessary properties such as database and ftp parameters, api endpoints, filenames and extensions collected in a single config.properties file.
These values are replaceable. The only purpose I left them filled,to show that I have a working configuration.
Please find the structure of the project on the following link: https://lucid.app/documents/embeddedchart/6a95d1ab-bf74-47ba-9547-cc30442edcbd

Packages and classes are separated by their duties. The program's workflow is the following:
Instantiation, gathering data from api endpoints, validating this data by certain parameters, creating csv, creating database tables, inserting data into the database, creating reports, turn the reports back to json format and upload them to a ftp server.




Technologies used:

- Java 16
- JDBC
- Maven
- Junit 5
- Mockito
- Gson and Apache libraries
- MySQL 8.0.25
