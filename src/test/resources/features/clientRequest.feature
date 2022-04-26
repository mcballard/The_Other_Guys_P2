Feature: As a client, I should be able to login and submit a help request

  Scenario: as client I should be informed for too long description
  Given I am logged in and on the client home page
  When I click create request button
  When I enter request description
  When I click the submit button
  Then I am notified that the request has been too long

  Scenario: As a client, I should be able to submit a help request

  Given I am logged in and on the client home page
  When I click click help request button
  When I enter my request comment
  When I click the submit button
  Then I am notified that the request has been submitted


  Scenario: As a client, I should be able to update my help request

  When I click the update request button
  When I edit my help request description
  When I click the submit button
  Then I am notified of a successful update


  Scenario: As a client, I should be able to update my personal information

  Given I am logged in and on the client home page
  When I click the update information button
  When I am redirected to the client profile page
  When I enter my first name
  When I enter my last name
  When I click the update button
  When I am notified of a successful update
  When I click the continue button
  Then I am redirected to the client home page


  Scenario: As a client, I should be informed if I have no help request
  Given I am logged in and on the client home page
  When I click the cancel request button
  When I am notified with a popup that I have no open help request
  When I click the continue button
  Then I remain on the client home page

  Scenario: As a client, I should be able to cancel a help request

  Given I am logged in and on the client home page
  When I click the cancel request button
  When I am prompted with a confirm button
  When I click confirm
  When I am notified of a successful request deletion
  When I click the continue button
  Then I remain on the client home page


  Scenario: As a client, I should be informed if I have no help request
  Given I am logged in and on the client home page
  When I click the view status button
  When I am notified with a popup that I have no open help request
  When I click the continue button
  Then I remain on the client home page

  Scenario: As a client, I should be able to view my help request status

  Given I am logged in and on the client home page
  When I click the view status button
  When I am notified with a popup of my request status
  When I click the continue button
  Then I remain on the client home page