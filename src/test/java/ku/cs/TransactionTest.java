package ku.cs;

import ku.cs.models.Account;
import ku.cs.models.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionTest {

    private Account account;

    @BeforeEach
    void setUp() throws ParseException {
        account = new Account(100, "14/09/2018 12:00");
    }

    @Test
    void testGetBalance() {
        assertEquals(100, account.getBalance());
    }

    @Test
    void testWithdraw() throws ParseException {
        account.withdraw(50, "14/09/2018 12:00");
        assertEquals(50, account.getExpense());
    }

    @Test
    void testDeposit() throws ParseException {
        account.deposit(50, "14/09/2018 12:00");
        assertEquals(150, account.getIncome());
    }

    @Test
    void testWithdrawWithDescription() throws ParseException {
        account.withdraw(50, "14/09/2018 12:00", "description description description description");
        assertEquals(50, account.getExpense());
    }

    @Test
    void testDepositWithDescription() throws ParseException {
        account.deposit(50, "14/09/2018 12:00", "description description description");
        assertEquals(150, account.getIncome());
    }

    @Test
    void testFindTransactionByDate() throws ParseException {
        account.deposit(50, "14/09/2018 13:00", "description description description");
        account.deposit(150, "14/09/2018 14:00", "description description description");
        Transaction transaction = account.findTransactionByDate("14/09/2018 13:00");
        assertEquals(50, transaction.getAmount());
    }

    @Test
    void testTransactionWithInvalidDateFormat() {
        assertThrows(ParseException.class, () -> {
            account.deposit(50, "14/09/2018 99000", "description description description");
        });
    }

    @Test
    void testEditTransactionChangeDate() throws ParseException {
        account.deposit(50, "14/09/2018 13:00", "description description description");
        account.editTransactionChangeDate("14/09/2018 13:00", "20/09/2018 13:00");
        assertEquals(50, account.findTransactionByDate("20/09/2018 13:00").getAmount());
    }

    @Test
    void testEditTransactionChangeAmount() throws ParseException {
        account.deposit(50, "14/09/2018 13:00", "description description description");
        account.editTransactionChangeAmount("14/09/2018 13:00", 99);
        assertEquals(99, account.findTransactionByDate("14/09/2018 13:00").getAmount());
    }

    @Test
    void testEditTransactionChangeDescription() throws ParseException {
        account.deposit(50, "14/09/2018 13:00", "description description description");
        account.editTransactionChangeDescription("14/09/2018 13:00", "edited description");
        assertEquals("edited description", account.findTransactionByDate("14/09/2018 13:00").getDescription());
    }
}