Feature: As a client, I should be able to log in and log out

  Scenario Outline: As a client, I should not be able to log in with the wrong username

    Given I am on the login page
    When I enter my username "<username>"
    When I enter my password "<password>"
    When I click login button
    When I am notified of bad input
    Then I am redirected to login page
    Examples:
    |username|password|
    |mb      |pass    |


  Scenario Outline: As a client, I should not be able to log in with the wrong password

    Given I am on the login page
    When I enter my username "<username>"
    When I enter my password "<password>"
    When I click login button
    When I am notified of bad input
    Then I am redirected to login page

    Examples:
    |username|password|
    |mb1     |pas     |


  Scenario Outline: As a client, I should be able to log in with my credentials

    Given I am on the login page
    When I enter my username "<username>"
    When I enter my password "<password>"
    When I click login button
    When I click the continue button after being notified of a successful login
    Then I am redirected to employee home page

    Examples:
      |username|password|
      |mb1     |pass   |
