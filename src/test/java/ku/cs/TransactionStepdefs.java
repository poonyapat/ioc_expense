package ku.cs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ku.cs.models.Account;
import ku.cs.models.Transaction;
import org.junit.jupiter.api.Assertions;

import java.text.ParseException;

public class TransactionStepdefs {
    private Account account;

    @Given("^a user with initial balance (.+) exists at (.*?)$")
    public void aUserWithInitialBalanceExistsAt(float amount, String date) throws ParseException {
        // Write code here that turns the phrase above into concrete actions
        account = new Account(amount, date);
    }

    @When("^I deposit (.+) to my account at (.*?)$")
    public void iDepositToMyAccountAt(float amount, String date) throws ParseException {
        // Write code here that turns the phrase above into concrete actions
        account.deposit(amount, date);
    }

    @Then("^my account balance is (.*?)$")
    public void myAccountBalanceIs(float balance) {
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertEquals(balance, account.getBalance());
    }

    @And("^my account income at (.+) is (.+)$")
    public void myAccountIncomeAtIs(String date, float amount) {
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertEquals(amount, account.findTransactionByDate(date).getAmount());
    }

    @And("^my account income is (.+)$")
    public void myAccountIncomeIs(float amount) {
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertEquals(amount, account.getIncome());
    }

    @When("^I withdraw (.+) from my account at (.+)$")
    public void iWithdrawFromMyAccountAt(float amount, String date) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        account.withdraw(amount, date);
    }

    @And("^my account expense at (.+) is (.+)$")
    public void myAccountExpenseAtIs(String date, float amount) {
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertEquals(amount, -account.findTransactionByDate(date).getAmount());
    }

    @And("^my account expense is (.+)$")
    public void myAccountExpenseIs(float amount) {
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertEquals(amount, account.getExpense());
    }

    @And("^(.+) my account has no transaction$")
    public void myAccountHasNoExpense(String date) {
        // Write code here that turns the phrase above into concrete actions
        Transaction transaction = account.findTransactionByDate(date);
        Assertions.assertNull(transaction);
    }

    @When("^I change transaction amount at (.+) to (.+)$")
    public void iChangeTransactionAmountAtTo(String date, float amount) {
        // Write code here that turns the phrase above into concrete actions
        account.editTransactionChangeAmount(date, amount);
    }

    @And("^my transaction at (.+) is income$")
    public void myTransactionAmountAtIsIncome(String date) {
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertTrue(account.findTransactionByDate(date).isIncome());
    }

    @And("^my transaction amount at (.+) is (.+)$")
    public void myTransactionAmountAtIs(String date, float amount) {
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertEquals(amount, account.findTransactionByDate(date).getAmount());
    }

    @And("^my transaction at (.+) is expense$")
    public void myTransactionAmountAtIsExpense(String date) {
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertTrue(account.findTransactionByDate(date).isExpense());
    }

    @When("^I change transaction date at (.+) to (.+)$")
    public void iChangeTransactionDateAtTo(String date, String newDate) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        account.editTransactionChangeDate(date, newDate);
    }

    @When("^I change transaction description at (.+) to (.+)$")
    public void iChangeTransactionDescriptionAtToTesting(String date, String description) {
        // Write code here that turns the phrase above into concrete actions
        account.editTransactionChangeDescription(date, description);
    }

    @Then("^my transaction description at (.+) is (.+)$")
    public void myTransactionDescriptionAtIsTesting(String date, String description) {
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertEquals(description, account.findTransactionByDate(date).getDescription());
    }
}