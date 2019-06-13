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
    Then the user should be registered

  Scenario Outline: Unsuccessful registration due to erroneous data
    Given I have a user
    And the user has a name of "<name>"
    And the user has an email of "<email>"
    And the user has a password of "<password>"
    And the user has an enabled flag of "<is_enabled>"
    And the user has an account type of "<type>"
    When I register the user
    Then the error message should be "<error_message>"
    Examples:
      | name | email                | password    | is_enabled | type | error_message                             |
      |      | john.world@gmail.com | password123 | true       | USER | Name cannot be empty                      |
      | John | john.worldgmail      | password123 | true       | USER | Email must be a well formed email address |
      | John | john.world@gmail.com | passw       | true       | USER | Password length must be greater than 5    |




