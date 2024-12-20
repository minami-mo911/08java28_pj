package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    private static final String JDBC_URL = "jdbc:h2:~/desktop/workspace/08java28_pj/DB/ikimono";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "";

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
    }
}