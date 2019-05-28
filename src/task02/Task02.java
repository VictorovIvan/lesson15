package task02;

import java.sql.*;

/**
 * Task 02
 */
public class Task02 {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/Task01";
    static final String USER = "postgres";
    static final String PASS = "333999";
    static final String sqlUserRole = "INSERT INTO  \"public\".\"USER_ROLE\" " +
            "(\"id\", \"user_id\", \"role_id\") VALUES (1,0,0)";
    static final String sqlRole = "INSERT INTO  \"public\".\"ROLE\" " +
            "(\"id\", \"name\", \"description\") " +
            "VALUES (1, 'Administration', 'some text about administration')";
    static final String sqlUser = "INSERT INTO  \"public\".\"USER\" " +
            "(\"id\", \"name\", \"birthday\", \"login_ID\", \"city\",\"email\",\"description\")" +
            " VALUES (1,'ABC','1990-11-05',1, 'London', 'abra@cadab.ra', 'Somebody user')";


    /**
     * SQL query in JDBC, when param is String
     * @param param SQL query
     */
    public void query(String param) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            if (param.equals("param")) {
                paramQueryExec(conn);
            }
            if (param.equals("batch")) {
                batchQueryExec(stmt);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    /**
     * INSERT with Parametric query
     * @param connection Creates aobject for sending SQL statements to the database.
     * @throws SQLException An exception that provides information on a database access
     *  error or other errors.
     */
    private void paramQueryExec(Connection connection) throws SQLException {
        PreparedStatement preparedStatementmt = connection.prepareStatement(sqlUserRole);
        preparedStatementmt.executeUpdate();
        preparedStatementmt = connection.prepareStatement(sqlRole);
        preparedStatementmt.executeUpdate();
        preparedStatementmt = connection.prepareStatement(sqlUser);
        preparedStatementmt.executeUpdate();
    }

    /**
     * INSERT with batch
     * @param statement The object used for executing a static SQL statement and returning the results it produces.
     * @throws SQLException An exception that provides information on a database access
     *  error or other errors.
     */
    private void batchQueryExec(Statement statement) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
        connection.createStatement();
        statement.addBatch(sqlUserRole);
        statement.executeBatch();
        statement.addBatch(sqlRole);
        statement.executeBatch();
        statement.addBatch(sqlUser);
        statement.executeBatch();
        statement.close();
        connection.close();
    }
}
