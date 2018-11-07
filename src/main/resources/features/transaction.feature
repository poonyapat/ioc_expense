Feature: transaction
  As a user
  I want to deposit and withdraw money with my account

Background:
  Given a user with initial balance 100 exists at 14/09/2018 12:00

Scenario: Deposit money to my account
  When I deposit 50.0 to my account at 14/09/2018 13:00
  Then my account balance is 150.0
  And my transaction at 14/09/2018 13:00 is income
  And my transaction amount at 14/09/2018 13:00 is 50
  And my account income is 150

Scenario: Withdraw money from my account
  When I withdraw 50 from my account at 14/09/2018 14:00
  Then my account balance is 50.0
  And my transaction at 14/09/2018 14:00 is expense
  And my transaction amount at 14/09/2018 14:00 is -50
  And my account expense is 50

Scenario: Withdraw money over the balance
  When I withdraw 200 from my account at 14/09/2018 15:00
  Then my account balance is 100
  And 14/09/2018 15:00 my account has no transaction
  And my account expense is 0