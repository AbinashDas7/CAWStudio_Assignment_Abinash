@TestTable
Feature: Verify if user is able to get the table showing correct order or not

  Background: 
    Given Data retreived from jsonFile

  Scenario Outline: User is checking whether the table showing correct order or not
    When User Login to Dynamic table Page
    And User able to click on Table Data button
    Then Add details from json file
    And Click on Refresh Table button
		Then Validate the Table for copied user