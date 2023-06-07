#Author: brijeshgajera1234@gmail.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Board API features verification
  I want to use this template for my feature file

  @api
  Scenario: Get all boards from trello portal for user
    Given user is authorized to access trello portal API
    When user sends api request for getting all the boards
    Then user should be able to get all boards

  @api
  Scenario: Create new list on board "Knab"
    Given user is authorized to access trello portal API
    When user sends api request for creating list named as "To Do" to board "Knab"
    Then list "To Do" should be created successfully
  
  @api
  Scenario: Update list name from "To Do" to "Done" on board "Knab"
    Given user is authorized to access trello portal API
    When user sends api request to update list name "To Do" with "Done" on board "Knab"
    Then name of list "To Do" should be changed to "Done"
  
  @api
  Scenario: Archive "Done" list on board "Knab"
    Given user is authorized to access trello portal API
    When user sends api request to archive "Done" list on board "Knab"
    Then list should be archived