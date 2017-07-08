@tag
Feature: Login test Verification

  @tag1
  Scenario: Login with valid username and password
    Given I go to "qaurl" with "Chrome_browser"
    When I enter "Tester" in the "username_id"
    And I enter "test" in the "password_name"
    And I click on "singbtn_xpath" button
    Then I validate that "LstOfOdrs_xpath" is displayed
    And I logout from the application
    Then I close broswer

  @tag2
  Scenario: Login with Invalid username and valid password
    Given I go to "qaurl" with "Chrome_browser"
    When I enter "ABCDEFG" in the "username_id"
    And I enter "test" in the "password_name"
    And I click on "singbtn_xpath" button
    Then I validate that "InvalidLogInMSg_xpath" is displayed
    Then I close broswer

    
      @tag3
  Scenario: Login with blank username and blank password
    Given I go to "qaurl" with "Chrome_browser"
    When I enter "" in the "username_id"
    And I enter "" in the "password_name"
    And I click on "singbtn_xpath" button
    Then I validate that "InvalidLogInMSg_xpath" is displayed
    Then I close broswer
    