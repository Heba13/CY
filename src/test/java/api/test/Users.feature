Feature: Users

  Scenario Outline: check that API successfully returns a list of users.
    When  retrieve list of users
    Then  retrieve users response status code should be <code>
    And  Schema of response should be valid as "<path>"
    Examples:
      | code |path          |
      | 200  | schema.json  |

  Scenario Outline: check that API successfully create new users.
    When  create new record of user with "<name>" and "<job>"
    Then  create users response status code should be <code>
    And   check that data of user created successfully
    Examples:
      | code |   name |job|
      | 201  |   name |job|

  Scenario Outline: check that API successfully update user.
    When  update user with "<name>" and "<job>" through "<id>"
    Then  update users response status code should be <code>
    And   check that data of user updated successfully

    Examples:
      | code |   name |job|id|
      | 200  |   name |job| 7|

