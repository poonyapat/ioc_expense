Feature: transaction modification
  As a user
  I want to edit amount, date, description of my transactions

  Background:
    Given a user with initial balance 20 exists at 14/09/2018 12:00
    And I deposit 50.0 to my account at 14/09/2018 13:00
    And I withdraw 10 from my account at 14/09/2018 14:00

  Scenario: change amount of income
    When I change transaction amount at 14/09/2018 13:00 to 20
    Then my account balance is 30.0
    And my transaction at 14/09/2018 13:00 is income
    And my transaction amount at 14/09/2018 13:00 is 20
    And my account income is 40
    And my account expense is 10

  Scenario: change amount of expense
    When I change transaction amount at 14/09/2018 14:00 to -50
    Then my account balance is 20
    And my transaction at 14/09/2018 14:00 is expense
    And my transaction amount at 14/09/2018 14:00 is -50
    And my account expense is 50
    And my account income is 70

  Scenario: change income to expense
    When I change transaction amount at 14/09/2018 13:00 to -10
    Then my account balance is 0
    And my transaction at 14/09/2018 13:00 is expense
    And my transaction amount at 14/09/2018 13:00 is -10
    And my account expense is 20
    And my account income is 20

  Scenario: change transaction date
    When I change transaction date at 14/09/2018 13:00 to 15/10/2018 14:00
    Then 14/09/2018 13:00 my account has no transaction
    And my transaction amount at 15/10/2018 14:00 is 50

  Scenario: change transaction description
    When I change transaction description at 14/09/2018 13:00 to testing
    Then my transaction description at 14/09/2018 13:00 is testing