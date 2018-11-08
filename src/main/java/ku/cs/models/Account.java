package ku.cs.models;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private ArrayList<Transaction> transactions;

    public Account(float amount, String date) throws ParseException {
        this();
        transactions.add(new Transaction(amount, (formatter.parse(date))));
    }

    public Account() {
        transactions = new ArrayList<Transaction>();
    }

    public float getBalance() {
        float amount = 0;
        for (Transaction transaction : transactions) {
            amount += transaction.getAmount();
        }
        return amount;
    }

    public float getIncome() {
        float amount = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                amount += transaction.getAmount();
            }
        }
        return amount;
    }

    public float getExpense() {
        float amount = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                amount -= transaction.getAmount();
            }
        }
        return amount;
    }

    public void deposit(float income, String date) throws ParseException {
        deposit(income, date, "");
    }

    public void deposit(float income, String date, String description) throws ParseException {
        transactions.add(new Transaction(income, formatter.parse(date), description));
    }

    public void withdraw(float expense, String date) throws ParseException, IllegalArgumentException {
        withdraw(expense, date, "");
    }

    public void withdraw(float expense, String date, String description) throws ParseException, IllegalArgumentException {
        if (expense > getBalance()) {
            throw new IllegalArgumentException("Invalid amount of money");
        }
        transactions.add(new Transaction(-expense, formatter.parse(date), description));
    }

    public Transaction findTransactionByDate(String strDate) {
        for (Transaction transaction : transactions) {
            if (formatter.format(transaction.getDate()).equals(strDate)) {
                return transaction;
            }
        }
        return null;
    }

    public boolean editTransactionChangeDate(String strDate, String newDate) throws ParseException {
        Transaction transaction = findTransactionByDate(strDate);
        if (transaction == null)
            return false;
        transaction.setDate(formatter.parse(newDate));
        return true;
    }

    public boolean editTransactionChangeDescription(String strDate, String description) {
        Transaction transaction = findTransactionByDate(strDate);
        if (transaction == null)
            return false;
        transaction.setDescription(description);
        return true;
    }

    public boolean editTransactionChangeAmount(String strDate, float amount) {
        Transaction transaction = findTransactionByDate(strDate);
        if (transaction == null)
            return false;
        transaction.setAmount(amount);
        return true;
    }

    public List<Transaction> getTransactions(){
        return transactions;
    }
}
