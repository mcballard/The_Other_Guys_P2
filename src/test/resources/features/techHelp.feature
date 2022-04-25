
Feature: As a technician, I should be able to view the request pool

  Scenario: As a technician, I should be able to view the request

  Given I am  logged in on and the technician home page
  When I click the technician home page collapsible button
  When I click the view help requests button
  Then I am shown the open requests on the right-hand side of the page

Feature: As a technician, I should be able to create work tickets

  Scenario: As a technician, I should not be able to create a ticket
  Given I am logged in and on the technician home page
  When I click the technician home page collapsible button
  When I click the create ticket button
  When I click the continue button
  Then I remain on the technician home page

  Scenario: As a technician, I should not be able to create work tickets with missing info

  Given I am logged in and on the technician home page
  When I click the technician home page collapsible button
  When I click the create ticket button
  When I do not input the comment into the text field
  When I do not set the category
  When I click the create button
  When I am notified that I am missing required information
  When I click the continue button
  Then I remain on the create ticket page

  Scenario: As a technician, I should be able to create work tickets

  Given I am logged in and on the technician home page
  When I click the technician home page collapsible button
  When I click the create ticket button
  When I input the comment into the text field
  When I set the category
  When I click the create button
  When I am notified of a successful creation
  When I click the continue button
  Then I remain on the technician home page

Feature: As a technician, I should be able to update ticket information

  Scenario: As a technician, I should not be able to update ticket if I do not have one

  Given I am logged in and on the technician home page
  When I click the technician home page collapsible button
  When I click the update ticket button
  When I am notified with a popup that I have no open tickets
  When I click the continue button
  Then I remain on the technician home page

  Scenario: As a technician, I should be able to update ticket information

  Given I am logged in and on the technician home page
  When I click the technician home page collapsible button
  When I click the update ticket button
  When I input the comment into the text field
  When I set the category
  When I click the update button
  When I am notified of a successful update
  When I click the continue button
  Then I remain on the technician home page

Feature: As a technician, I should be able to resolve tickets

  Scenario: As a technician, I should not be able to resolve ticket if I do not have one

  Given I am logged in and on the technician home page
  When I click the resolve ticket collapsible button
  When I am notified with a popup that I have no open tickets
  When I click the continue button
  Then I remain on the technician home page

  Scenario: As a technician, I should be able to resolve tickets

  Given I am logged in and on the technician home page
  When I click the resolve ticket collapsible button
  When I enter the resolution text
  When I click the resolve
  When I am notified of the ticket resolution
  When I click the continue button
  Then I remain on the technician home page

Feature: As a technician, I should be able to update my personal information

  Scenario: As a technician, I should be able to update my personal information

  Given I am logged in and on the technician home page
  When I click the update information button
  When I am redirected to the technician profile page
  When I enter my first name
  When I enter my last name
  When I click the update button
  When I am notified of a successful update
  When I click the continue button
  Then I am redirected to the technician home page