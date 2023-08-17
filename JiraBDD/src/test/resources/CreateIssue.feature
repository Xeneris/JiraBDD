Feature: Create Issue
  As a logged in user
  I want to be able to create a new issue.

  @selenium
  Scenario Outline: Create new issue successfully
    Given I am logged in with valid credentials
    When I click the Create button in the navbar
    And Set project to <project>
    And Set issue to <issue>
    And Set summary to <summary>
    And click the Create button
    And click the popup window on the top right
    Then I check the summary of the newly created issue, which equals <summary>

    Examples:
      | project          | issue   | summary      |
      | "COALA project"  | "Bug"   | "Test issue" |
      | "JETI project"   | "Story" | "Test issue" |
      | "TOUCAN project" | "Bug"   | "Test issue" |
