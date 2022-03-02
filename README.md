# JUnit Test Automation

This is an End to End framework that covers WEB and API Automation using Java / Selenium /Rest Assured and JUnit
System Requirements - prerequisites --Java 17 –Maven
UI Scenarios:
1.	Validating image on Home / Welcome Page.
2.	Go to Find Owners Tab and Add Owner and validate owner is added -Name, Address, City, Telephone
3.	For the added owner Add Pet as a new Dog and validate the details Dog Name and BirthDate.

API Scenarios:
Booking - CreateBooking
Booking - UpdateBooking
Booking- DeleteBooking 

Steps to execute the tests:
There two ways to execute the tests from this framework:
1. ![image](https://user-images.githubusercontent.com/43815574/156432925-acd0ff03-b70e-447d-9b87-129c047fd085.png)

2. To Run and generate report using maven surefire run below command:
  mvn surefire-report:report
  
Report:

![image](https://user-images.githubusercontent.com/43815574/156433099-732917af-3444-4a0d-b029-57aecd54bab8.png)
![image](https://user-images.githubusercontent.com/43815574/156433161-a3f217c1-3d1b-4677-8b30-6f6460c12ff4.png)


