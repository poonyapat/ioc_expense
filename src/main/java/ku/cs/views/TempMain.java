package ku.cs.views;

import ku.cs.jdbc.DBConnector;
import ku.cs.models.Account;

public class TempMain {
    public static void main(String[] args) {
        DBConnector connector = DBConnector.getInstance();
        Account account = connector.loadAccount();
        System.out.println(account.getBalance());
    }
}
