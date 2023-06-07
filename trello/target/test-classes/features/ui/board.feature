Feature: Board UI features verification
	
	
	Background:
    Given user navigates to trello home page
    When user clicks on login link
    And user enters email ID as "<please provide your email in base64 encoded format>"
    And user clicks on continue button
    And user enters password as "<please provide your password in base64 encoded format>"
    And user clicks on login button
    Then user should be successfully logged in
     
  @ui
  Scenario: create board on trello
    Given user is logged in to trello portal
    When user clicks on create board button
    And user enters board title as "Knab"
    And user clicks on create button
    Then board should be created
   
  @ui
  Scenario: create list in board
    Given user is logged in to trello portal
    And user goes to board name "Knab"
    When user clicks on add list
    And user enters list name as "To Do"
    And user clicks on add list submit button
    Then list with name "To Do" should be created
    
  @ui
  Scenario: add card named "Install" to list "To Do"
    Given user is logged in to trello portal
    And user goes to board name "Knab"
    When user adds card named "Install" to list "To Do"
    Then card "Install" should be added to list "To Do"
    
  @ui
  Scenario: update card named with "Install" to "In Stock" in "To Do" list
    Given user is logged in to trello portal
    And user goes to board name "Knab"
    When user udpates card name of "Install" card to "In Stock" in "To Do" list 
    Then card name should be updated to "In Stock" in list "To Do"
  
  @ui
  Scenario: delete all list which has name "To Do" under board "Knab"
    Given user is logged in to trello portal
    And user goes to board name "Knab"
    When user delete all list which has name "To Do"
    Then list with name "To Do" should not be there on board "Knab"
    