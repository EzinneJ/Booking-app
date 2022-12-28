Feature: CucumberJava

  Scenario: Sign up using DataTables

    Given I have a valid endpoint
    When I sign up on the server using DataTables
      | Field                 | Values               |
      | email                 | "chidi@gmail.com"      |
      | name                  | "chidi"                |
      | seat_number            | 2                    |
    Then return statusCode 200