Feature: As a client, I should be able to login and submit a help request


  Scenario Outline: As a client, I should be able to submit a help request

    Given I am logged in and on the client home page
    When I click create help request collapsible button
    When I enter my request description "<comment>"
    When I click the create request button
    When I click the continue button after being notified of successful creation
    Then I remain on the client home page
    Examples:
      | comment |
      | something here|


  Scenario Outline: as client I should be informed for too long description
  Given I am logged in and on the client home page
  When I click create help request collapsible button
  When I enter my request description "<comment>"
  When I click the create request button
  When I am notified that the request comment is too long and i click the continue button
    Examples:
      | comment |
      | 102938475610293847561029384756102938475610293847561029384756102938475610293847561029384756102938475610293847561029384756102938475610293847561029384756102938475610293847561029384756102938475610293847561029384756102938475610293847561029384756|


  Scenario Outline: As a client, I should be able to update my help request
  Given I am logged in and on the client home page
  When I click the update request collapsible button
  When I update my request description "<comment>"
  When I click the update request submit button
  When I am notified of a successful update and I click the continue button
  Then I remain on the client home page
    Examples:
      | comment |
      |some other comment|


  Scenario Outline: As a client, I should be able to update my personal information

  Given I am logged in and on the client home page
  When I click the update information button
  When I enter my first name "<first_name>"
  When I enter my last name "<last_name>"
  When I click the update button
  When I am notified of a successful update and I click the continue button
  Then I remain on the client home page
    Examples:
      | first_name | last_name |
      |something   |different  |


  Scenario: As a client, I should be able to view my help request status

    Given I am logged in and on the client home page
    When I click the view status collapsible button
    When I click the view request button
    When I am notified with a popup of my request status and I click continue
    Then I remain on the client home page


  Scenario Outline: As a client, I should be able to cancel a help request

  Given I am logged in and on the client home page
  When I click the cancel request collapsible button
  When I enter the "<request_id>"
  When I click the cancel request submit button
  When I am notified of a successful request deletion and click the continue button
  Then I remain on the client home page
    Examples:
      | request_id |
      |2           |


  Scenario: As a client, I should be informed if I have no help request
    Given I am logged in and on the client home page
    When I click the view status collapsible button
    When I click the view request button
    When I am notified with a popup that I have no open help request and I click the continue button
    Then I remain on the client home page

  Scenario Outline: As a client, I should be informed if I have no help request
    Given I am logged in and on the client home page
    When I click the cancel request collapsible button
    When I enter the "<request_id>"
    When I click the cancel request submit button
    When I am notified with a popup that I have no open help request and I click the continue button
    Then I remain on the client home page
    Examples:
      | request_id |
      |2           |