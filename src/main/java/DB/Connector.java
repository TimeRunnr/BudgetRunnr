package DB;

import java.sql.*;

public class Connector {

    private static final String createSqlTblAccounts = "create table if not exists tblAccounts" +
            "(" +
            "    intID                 integer not null," +
            "    strAccountName        TEXT    not null," +
            "    dateAccountCreation   integer default 1641495862 not null," +
            "    dateLastTransaction   integer default 1641495862," +
            "    doubleAvailableAmount REAL    default 0 not null," +
            "    constraint tblAccounts_pk" +
            "        primary key (intID autoincrement)" +
            ");" +
            "create unique index tblAccounts_intID_uindex" +
            "    on tblAccounts (intID);" +
            "create unique index tblAccounts_strAccountName_uindex" +
            "    on tblAccounts (strAccountName);";
    private static final String createSqlTblTransactionTypes =
            "create table if not exists tblTransactionTypes" +
                    "(" +
                    "    intID                  integer not null," +
                    "    strTransactionType     TEXT    not null," +
                    "    strTransactionType_DE  TEXT," +
                    "    strTransactionType_ES  TEXT," +
                    "    strTransactionType_EST TEXT," +
                    "    constraint tblTransactionTypes_pk" +
                    "        primary key (intID autoincrement)" +
                    ");" +
                    "create unique index tblTransactionTypes_intID_uindex" +
                    "    on tblTransactionTypes (intID);" +
                    "create unique index tblTransactionTypes_strTransactionType_uindex" +
                    "    on tblTransactionTypes (strTransactionType);";
    private static final String createSqlTblTransactions = "create table if not exists tblTransactions" +
            "(" +
            "    intID                   integer not null," +
            "    intTransactionType      integer default 1 not null," +
            "    doubleTransactionAmount REAL    default 0.0 not null," +
            "    dateTransactionExecuted integer," +
            "    dateTransactionEntered  integer," +
            "    strCause                TEXT," +
            "    intAccountID            integer," +
            "    constraint tblTransactions_pk" +
            "        primary key (intID autoincrement)," +
            "    foreign key (intTransactionType) references tblTransactionTypes," +
            "    foreign key (intAccountID) references tblAccounts" +
            ");" +
            "create unique index tblTransactions_intID_uindex" +
            "    on tblTransactions (intID);";


    /**
     * Opens a connection to the BudgetRunnr SQLite3 database. Creates the database if it does not exist.
     *
     * @return Returns a {@link Connection Connection} object, containing the  opened connection.
     */
    public static Connection openConnection() {
        Connection c = null;
        try {
            String url = "jdbc:sqlite:src/main/java/DB/BudgetRunnr";
            c = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.out.println(e.getMessage());
        }

        //Execute Create Statements
        try {
            assert c != null;
            Statement statement = c.createStatement();
            statement.executeUpdate(createSqlTblAccounts);
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.out.println(e.getMessage());
        }

        try {
            Statement statement = c.createStatement();
            statement.executeUpdate(createSqlTblTransactionTypes);
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.out.println(e.getMessage());
        }

        try {
            Statement statement = c.createStatement();
            statement.executeUpdate(createSqlTblTransactions);
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.out.println(e.getMessage());
        }

        return c;
    }

    /**
     * Tests the {@link Connection Connection} given against tblAccounts, printing its contents to console.
     *
     * @param c {@link Connection Connection} to be tested.
     * @return Returns a boolean, true if the connection was successful, false if not. TODO properly implement true/false result of test connection.
     */
    public static boolean testConnection(Connection c) {
        Statement statement;
        try {
            statement = c.createStatement();
            ResultSet rs = statement.executeQuery("select * from tblAccounts");
            //Test read to see if connection successful
            while (rs.next()) {
                // read the result set
                System.out.println("intID = " + rs.getInt("intID"));
                System.out.println("id = " + rs.getDouble("doubleAvailableAmount"));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Closes the given {@link Connection Connection}.
     *
     * @param c {@link Connection Connection} to be closed.
     */
    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
                System.out.println("Connection to SQLite has been closed.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Connection conn;
        conn = openConnection();
        testConnection(conn);
        closeConnection(conn);
    }


}
