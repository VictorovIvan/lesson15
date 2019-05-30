package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class ConnectDataBase
 */
public class ConnectDataBase {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Task01";
    private static final String USER = "postgres";
    private static final String PASS = "333999";

    /**
     * Connect to data base Task01
     * @param connection Parametric connection to database
     * @return Condition of the connection
     */

    public static Connection connectionDataBase(Connection connection)  {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
