Feature: As a client, I should be able to log in and log out


  Scenario Outline: As a client, I should not be able to log in with the wrong username

    Given I am on the login page
    When I enter wrong <username>
    When I enter my <password>
    When  I click submit button
    When I am notified of bad input
    Then I am redirected to login page

    Examples:
    |username|password|
    |mb      |pass    |


  Scenario Outline: As a client, I should not be able to log in with the wrong password

    Given I am on the login page
    When I enter wrong <username>
    When  I enter my <password>
    When I click submit button
    When I am notified of bad input
    Then I am redirected to login page

    Examples:
    |username|password|
    |mb1     |pas     |


  Scenario Outline: As a client, I should be able to log in with my credentials

    Given I am on the login page
    When I enter wrong <username>
    When  I enter my <password>
    When I click submit button
    When I am notified of bad input
    Then I am redirected to login page

    Examples:
      |username|password|
      |mb1     |pass   |