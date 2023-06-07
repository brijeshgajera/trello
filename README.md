# Trello UI & API Automation
This is a project for automating WebUI and API test cases of trello
## Tools and Packages used 
- Eclipse IDE 
- Java
- Selenium
- Cucumber
- RestAssured
- TestNG
- Maven
- Extent Report
## Project Structure
![alt text](https://github.com/knabnl-incubator/brijesh-gajera-knab/blob/main/ProjectStructure.JPG?raw=true)

### Brief Overview
- src/main/java/api 
    - This package contains classes with API methods.
- src/main/java/base
    - This package contains all selenium actions.
- src/main/java/pages
    - This package has all pages. we are following POM(page object model) pattern so for each page we will have corrosponding class file. 
- src/main/java/steps
    - This package has step defination for cucmber steps.
- src/main/java/util
    - common utils being written in this dir
- src/main/resources/config
    - This package contains all properties files where we write element locators.
- src/main/resources/json
    - This is for API testing. we will put json schema here to validate API response.
- src/test/java/features
    - It contains feature files for API and UI test cases.
- src/test/java/runner
    - Here we have defined glue to find steps for feature file.
- src/test/resources
    - contains drivers, testdata and properties files

## Prerequisite to run project
- JDK 17 [Installed](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- Account created on [trello](https://trello.com/) UI 
- Enable trello API access. Generate **key** and **token**
- Chrome or Firefox browser installed
- Maven [installed](https://maven.apache.org/install.html)

### Note
:exclamation::exclamation::exclamation: Please update your credentials to below file before you run project :exclamation::exclamation::exclamation:<br/> 
1. **src/test/java/features/ui/board.feature**
    - email id: update base64(UTF-8) encoded email
    - password: update base64(UTF-8) encoded password
```java
Background:
    Given user navigates to trello home page
    When user clicks on login link
    And user enters email ID as "<base64 encoded emailID>"
    And user clicks on continue button
    And user enters password as "<base64 encoded password>"
    And user clicks on login button
    Then user should be successfully logged in
```
2. **src/main/resources/config/credentials.properties**
    - update **key** and **token** value which you have generated earlier



## How to run project
In order to execute test cases written for UI and API, go to directory **trello** in your setup and hit below command from terminal/cmd.
```bash
mvn test
```
## Report Structure
Here we have used extent report library to generate nice html report. It will automatically create report directory with timestamp inside **target** dir everytime you trigger test.<br />
We are capturing screenshots as well in case of failed step and it will be added to report.

## What Next (Enhancements)?
- Integration of project with Jenkins CI/CD pipeline
- Run project in container
- Improve reporting part

## Test Cases
This feature is huge and it can have hundred of test case. I have included few here for demonstration. 
### UI

```java
Scenario: create board on trello
  Given user is logged in to trello portal
  When user clicks on create board button
  And user enters board title as "Knab"
  And user clicks on create button
  Then board should be created
 
Scenario: create list in board
  Given user is logged in to trello portal
  And user goes to board name "Knab"
  When user clicks on add list
  And user enters list name as "To Do"
  And user clicks on add list submit button
  Then list with name "To Do" should be created
  
Scenario: add card named "Install" to list "To Do"
  Given user is logged in to trello portal
  And user goes to board name "Knab"
  When user adds card named "Install" to list "To Do"
  Then card "Install" should be added to list "To Do"
  
Scenario: update card named with "Install" to "In Stock" in "To Do" list
  Given user is logged in to trello portal
  And user goes to board name "Knab"
  When user udpates card name of "Install" card to "In Stock" in "To Do" list 
  Then card name should be updated to "In Stock" in list "To Do"

Scenario: delete all list which has name "To Do" under board "Knab"
  Given user is logged in to trello portal
  And user goes to board name "Knab"
  When user delete all list which has name "To Do"
  Then list with name "To Do" should not be there on board "Knab"

Scenario: move card from one list to another
  Given user is logged in to trello portal
  And user goes to board name "Knab"
  When user moves card named "In Stock" from list "To Do" to "Done"
  Then card has been moved successfully

Scenario: user adds 5001 cards to list
  Given user is logged in to trello portal
  And user goes to board name "Knab"
  When user adds 5000 card to list "To Do"
  And user adds 1 more card to list
  Then card should not be added and it should give error
```

### API
```java
Scenario: Get all boards from trello portal for user
  Given user is authorized to access trello portal API
  When user sends api request for getting all the boards
  Then user should be able to get all boards

Scenario: Create new list on board "Knab"
  Given user is authorized to access trello portal API
  When user sends api request for creating list named as "To Do" to board "Knab"
  Then list "To Do" should be created successfully

Scenario: Update list name from "To Do" to "Done" on board "Knab"
  Given user is authorized to access trello portal API
  When user sends api request to update list name "To Do" with "Done" on board "Knab"
  Then name of list "To Do" should be changed to "Done"

Scenario: Archive "Done" list on board "Knab"
  Given user is authorized to access trello portal API
  When user sends api request to archive "Done" list on board "Knab"
  Then list should be archived

Scenario: user tries to access API with invalid key
  Given user has invalid api key and valid token
  When user tries to acess API with invalid key
  Then user should get "401" unauthorized with error response "invalid key"

Scenario: user tries to access API with invalid token
  Given user has valid api key and invalid token
  When user tries to acess API with invalid token
  Then user should get "401" unauthorized with error response "invalid token"
```