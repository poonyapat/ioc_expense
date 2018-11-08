package ku.cs.views;

import ku.cs.jdbc.DBConnector;
import ku.cs.models.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static void main(String[] args) throws ParseException {

        ApplicationContext context = new ClassPathXmlApplicationContext("Config.xml");
        String input;
        Scanner scanner = new Scanner(System.in);
        Account account = context.getBean("emptyAccount", Account.class);
        DBConnector dbConnector = DBConnector.getInstance();
        boolean connectedToDB = false;
        while (true) {
            System.out.println("Command A) deposit B) withdraw C) edit transaction D) get amount E) connect to database F) exit");
            input = scanner.next();
            char cmd = input.charAt(0);
            if (cmd == 'A' || cmd == 'B') {
                System.out.println("Please input amount of money and description [not require description]: ");
                System.out.println("\"[amount] [description]\"");
                int value = scanner.nextInt();
                String description = scanner.nextLine();
                if (cmd == 'A') {
                    account.deposit(value, formatter.format(new Date()), description);
                    if (connectedToDB){
                        dbConnector.addTransaction(formatter.format(new Date()), value, description);
                    }
                } else {
                    try {
                        account.withdraw(value, formatter.format(new Date()), description);
                        if (connectedToDB){
                            dbConnector.addTransaction(formatter.format(new Date()), -value, description);
                        }
                    } catch (IllegalArgumentException exception) {
                        System.err.println(exception.getMessage());
                    }
                }
            } else if (cmd == 'C') {
                System.out.println("Please input transaction date");
                String date = scanner.nextLine();
                try {
                    System.out.println("Select attribute A) date B) amount C) description");
                    cmd = scanner.next().charAt(0);
                    scanner.nextLine();
                    if (cmd == 'A') {
                        System.out.println("Please input new date");
                        String newDate = scanner.nextLine();
                        account.editTransactionChangeDate(date, newDate);
                        if (connectedToDB){
                            dbConnector.updateTransaction(date, "Date",newDate);
                        }
                    } else if (cmd == 'B') {
                        System.out.println("Please input new amount of money");
                        float amount = scanner.nextFloat();
                        account.editTransactionChangeAmount(date, amount);
                        if (connectedToDB){
                            dbConnector.updateTransaction(date, "Amount",String.valueOf(amount));
                        }
                    } else if (cmd == 'C') {
                        System.out.println("Please input new description");
                        String newDescription = scanner.nextLine();
                        account.editTransactionChangeDescription(date, newDescription);
                        if (connectedToDB){
                            dbConnector.updateTransaction(date, "Description",newDescription);
                        }
                    } else {
                        System.err.println("Invalid Command");
                    }
                } catch (ParseException exception) {
                    System.err.println(exception.getMessage());
                }
            } else if (cmd == 'D') {
                System.out.println("Please select attribute A) Balance B) Income C) Expense D) Transaction");
                char attr = scanner.next().charAt(0);
                if (attr == 'A') {
                    System.out.println(account.getBalance());
                } else if (attr == 'B') {
                    System.out.println(account.getIncome());
                } else if (attr == 'C') {
                    System.out.println(account.getExpense());
                } else if (attr == 'D') {
                    scanner.nextLine();
                    System.out.println("Please input date");
                    String date = scanner.nextLine();
                    System.out.println(account.findTransactionByDate(date));
                } else {
                    System.err.println("Invalid attribute");
                }
            }else if (cmd == 'E'){
                System.out.println("Connecting to Database...");
                System.out.println("Overriding Account...");
                account = dbConnector.loadAccount();
                connectedToDB = true;
            } else if (cmd == 'F') {
                break;
            } else {
                System.err.println("Invalid Command");
            }
        }
    }
}
