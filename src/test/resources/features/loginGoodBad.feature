Feature: As a client, I should be able to log in and log out


  Scenario Outline: As a client, I should not be able to log in with the wrong username

    Given I am on the login page
    When I enter wrong "<username>"
    When I enter my "<password>"
    When  I click submit button
    When I am notified of bad input
    Then I am redirected to login page

    Examples:
    |username|password|
    |mb      |pass    |


  Scenario Outline: As a client, I should not be able to log in with the wrong password

    Given I am on the login page
    When I enter wrong "<username>"
    When  I enter my "<password>"
    When I click submit button
    When I am notified of bad input
    Then I am redirected to login page

    Examples:
    |username|password|
    |mb1     |pas     |


  Scenario Outline: As a client, I should be able to log in with my credentials

    Given I am on the login page
    When I enter wrong "<username>"
    When  I enter my "<password>"
    When I click submit button
    When I am notified of bad input
    Then I am redirected to employee home page

    Examples:
      |username|password|
      |mb1     |pass   |

  Scenario: As a client, I should be able to log out
    Given I am on the client home page
    When I click the logout button
    When I am notified of a successful logout
    When I click the continue button
    Then I am redirected to the login page

Feature: As a technician, I should be able to log in and log out
  Scenario Outline: As a technician, I should be able to log in

    Given I am on the login page
    When I enter my <username>
    When I enter my <password>
    When I click the submit button
    Then I am redirected to the technician home page
    Examples:
      |username  |password|
      |  mb1     | pass   |

  Scenario Outline: As a technician, I should not be able to log in with the wrong username

    Given I am on the login page
    When I enter wrong <username>
    When I enter my <password>
    When I click submit button
    When I notified for bad <username> input
    Then I am redirected to login page
    Examples:
      | username | password |
      |mb1       | pass     |

  Scenario Outline: As a technician, I should not be able to log in with the wrong password

    Given I am on the login page
    When I enter <username>
    When I enter my wrong <password>
    When I click submit button
    When I notified for bad <password> input
    Then I am redirected to login page
    Examples:
      | username | password  |
      | mb1      | pass      |

  Scenario: As a technician, I should be able to log out

    Given I am on the technician home page
    When I click the logout button
    When I am notified of a successful logout
    When I click the continue button
    Then I am redirected to the login page