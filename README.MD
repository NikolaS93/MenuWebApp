# WebAppMenu project
---------------------------------
* Language: Java  

`## Frameworks:`
* TestNG
* Selenium
* Maven

## Pattern
Using page object pattern.

### POM.xml
The place for all the project dependencies.

### Drivers
* chromedriver

### BaseTest.class
The place to set all the capabilities in this class and initializing the Driver here.

### BasePage.class
Where Web driver is passed to page objects. 

### pages
all the test data is stored over here (Steps+Elements)

### Utilities
helper methods for waits and conversions 

### test folder
tests are stored here.