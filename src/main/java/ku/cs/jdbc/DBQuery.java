package ku.cs.jdbc;

class DBQuery {
    static final String findAllTransaction = "SELECT * FROM Transactions";

    static String addTransaction(String date, float amount, String description){
        return String.format("INSERT INTO table_name VALUES (%s, %f, %s);", date, amount, description);
    }

    static String updateTransaction(String date, String column, String newValue){
        if ("Amount".equals(column)){
            return String.format("UPDATE table_name SET %s = %s WHERE Date='%s';", column, newValue, date);
        }
        return String.format("UPDATE table_name SET %s = '%s' WHERE Date='%s';", column, newValue, date);
    }

}
