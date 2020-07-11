# openimis-dhis2integration

# About this project:
OpenIMIS to Dhis2 adapter made in Java using Spring Boot Framework. 

# How to run the program:
1) First step is to make .jar file.
You can do this by going into the project directory and typing the below command in the terminal.
mvn -Dmaven.test.skip=true clean compile package
2) The above step should create a .jar file in your project directory->target
You can copy this .jar file to any other directory or machine.
3) Copy the project's /src/main/resources/->application.properties file to the same folder as this above .jar file.
Any config variables can be changed directly by updating this application.properties file, no need to remake this jar again.
You should only update the openimis and dhis2 baseUrl, username and password values. Touch the other config values, if you know what you are doing.
3) Using the terminal go to the directory with the .jar file and run it using:
java -Xms512m -Xmx2048m -jar JAR_FILE_NAME_HERE.jar &
4) After running the program, a 'logs' directory will be created in the same directory as your .jar file.
This will contain the logs.


# What do you need to run this .jar file:
1) Java 8 installed. 
2) Working internet connection.
3) At least 2GB of RAM is recommended. The size mostly depends on the constants fetched in the 1) point of the Basic workflow section.
For the current version the memory requirement is almost negligible, but that might change if we decide to fetch the openIMIS Patient or Claim data in bigger page-size and store them in-memory cache too.
