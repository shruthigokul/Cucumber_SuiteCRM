@tag
Feature: Create leads using parameterization

  @Scenario2
  Scenario: Open the Leads page and add multiple lead accounts
    Given user navigates to Sales -> Leads -> Create Lead
    When user fills in Leads details with lastName "App12"
    And user clicks save leads
    Then user navigates to View Leads page to see results