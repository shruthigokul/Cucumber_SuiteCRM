@tag
Feature: Schedule a meeting and invite members

  @Scenario3
  Scenario Outline: To schedule a meeting and include at least 3 invitees
    Given user navigates to Activities -> Meetings -> Schedule a Meeting
    When user fills meeting details with a "<heading>","<startTime>","<endTime>"
    And user searches and adds members with last names "<name1>" and "<name2>"
    And user clicks save the meeting
    Then user navigates to View Meetings page to confirm meeting schedule

    Examples: 
      | heading  | startTime | endTime  | name1  | name2  |
      | HarSample1 |  05 | 06 | App  | App1  |
      | HarSample2 |  10 | 11 | App  | App1  |
