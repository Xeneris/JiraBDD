Feature: Browse Project
  As a logged in user
  I want to be able to browse existing projects.

  @selenium
  Scenario Outline: Browse existing features successfully
    Given I am logged in with valid credentials
    When I redirect to the given <url>
    Then The key on the right side of the page matches <expected_key>

    Examples:
      | url                                                               | expected_key |
      | "https://jira-auto.codecool.metastage.net/projects/MTP/summary"   | "MTP"        |
      | "https://jira-auto.codecool.metastage.net/projects/COALA/summary" | "COALA"      |