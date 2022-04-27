
Feature: As a technician, I should be able to view the request pool

  Scenario Outline: As a technician, I should not be able to create work tickets with no description

  Given I am logged in and on the technician home page
  When I click the create ticket collapsible button
  When I click the view open help requests button
  When I click the create ticket button
  When I enter my ticket description "<description>"
  When I select a category in the drop down bar "<category>"
  When I click the second create ticket button
  When I am notified of a failed creation and click the continue button
  Then I remain on the technician home page
    Examples:
      | description    | category |
      |test description|1|


  Scenario Outline: As a technician, I should be able to create work tickets

  Given I am logged in and on the technician home page
  When I click the create ticket collapsible button
  When I click the view open help requests button
  When I click the create ticket button
  When I enter my ticket description "<description>"
  When I select a category in the drop down bar "<category>"
  When I click the second create ticket button
  When I am notified of a successful creation and click the continue button
  Then I remain on the technician home page
    Examples:
      | description    | category |
      |test description|1         |

  Scenario: As a technician, I should be able to view the open ticket

  Given I am logged in and on the technician home page
  When I click the view my open ticket collapsible button
  When I click the view tickets button
  When I am shown my open ticket and click the continue button
  Then I remain on the technician home page


  Scenario Outline: As a technician, I should not be able to update ticket if I do not have one

  Given I am logged in and on the technician home page
  When I click the update ticket collapsible button
  When I enter the ticket id "<ticket_id>"
  When I input the comment into the text field "<comment>"
  When I click the update ticket button
  When I am notified of a failed ticket update and I click the continue button
  Then I remain on the technician home page
    Examples:
      | ticket_id | comment |
      |4          |something|

  Scenario Outline: As a technician, I should be able to update ticket information

  Given I am logged in and on the technician home page
  When I click the update ticket collapsible button
  When I enter the ticket id "<ticket_id>"
  When I input the comment into the text field "<comment>"
  When I click the update ticket button
  When I am notified of a successful ticket update and I click the continue button
  Then I remain on the technician home page
    Examples:
      | ticket_id | comment |
      |2          |something else|


  Scenario Outline: As a technician, I should be able to resolve tickets

  Given I am logged in and on the technician home page
  When I click the resolve ticket collapsible button
  When I click the view open tickets button
  When I click the resolve ticket button
  When I enter the ticket resolution comment"<comment>"
  When I click the second resolve ticket button
  When I am notified of the ticket resolution and I click the continue button
  Then I remain on the technician home page
    Examples:
      | comment |
      |are we there yet?|


  Scenario Outline: As a technician, I should be able to update my personal information

  Given I am logged in and on the technician home page
  When I click the update information button
  When I enter my first name "<first_name>"
  When I enter my last name "<last_name>"
  When I click the update button
  When I am notified of a successful update and I click the continue button
  Then I remain on the technician home page
    Examples:
      | first_name | last_name |
      |new         | name      |

  Scenario: As a technician, I should not be able to create a ticket with no open help requests
    Given I am logged in and on the technician home page
    When I click the create ticket collapsible button
    When I click the view open help requests button
    When I am notified that there are no help requests and I click the continue button
    Then I remain on the technician home page

  Scenario: As a technician, I should not be able to view my ticket if I do not have one
    Given I am logged in and on the technician home page
    When I click the view my open ticket collapsible button
    When I click the view tickets button
    When I am notified of a failed ticket viewing and click the continue button
    Then I remain on the technician home page

  Scenario: As a technician, I should not be able to resolve ticket if I do not have one

    Given I am logged in and on the technician home page
    When I click the resolve ticket collapsible button
    When I click the view open tickets button
    When I am notified with a popup that I have no open tickets and i click the continue button
    Then I remain on the technician home page