@UserRegistration
Feature: As a User
  I want to be able to register to the movie review application
  so that I can be registered


  Scenario: A user registers successfully
    Given I have a user
    And the user has a name of "Foo"
    And the user has an email of "foo.bar@email.com"
    And the user has a password of "passfoobar"
    And the user has an enabled flag of "true"
    And the user has an account type of "USER"
    When I register the user
    Then I the user is registered

