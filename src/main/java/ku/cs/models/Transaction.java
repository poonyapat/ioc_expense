package ku.cs.models;

import java.util.Date;

public class Transaction {
    private float amount;
    private Date date;
    private String description;

    Transaction(float amount, Date date, String description) {
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    Transaction(float amount, Date date) {
        this(amount, date, "");
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIncome() {
        return amount > 0;
    }

    public boolean isExpense() {
        return amount < 0;
    }

    @Override
    public String toString() {
        return String.format("Date: %s, Amount: %.2f, Description: %s", date.toString(), amount, description);
    }
}
