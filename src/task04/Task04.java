package task04;

import java.sql.*;

public class Task04 {
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
    static final String sqlRoleError = "INSERT INTO  \"public\".\"ROLE\" " +
            "(\"idError\", \"nameError\", \"descriptionError\") " +
            "VALUES (1Error, 'AdministrationError', 'some text about administration')";


    /**
     * SQL query in JDBC, when param is String
     */
    public void query() {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            setSavepoint(conn);
            setSavepointAWithError(conn);

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
     * Set and create SAVEPOINT
     *
     * @param connection Creates aobject for sending SQL statements to the database.
     * @throws SQLException An exception that provides information on a database access
     *                      error or other errors.
     */

    private void setSavepoint(Connection connection) throws SQLException {
        PreparedStatement preparedStatementmt = connection.prepareStatement(sqlUserRole);
        preparedStatementmt.executeUpdate();
        connection.commit();
        connection.setSavepoint("SAVEPOINT");
        preparedStatementmt = connection.prepareStatement(sqlRole);
        preparedStatementmt.executeUpdate();
        connection.commit();
        preparedStatementmt = connection.prepareStatement(sqlUser);
        preparedStatementmt.executeUpdate();
        connection.commit();
    }

    /**
     * Set and create SAVEPOINTA and later will make error
     *
     * @param connection Creates aobject for sending SQL statements to the database.
     * @throws SQLException An exception that provides information on a database access
     *                      error or other errors.
     */

    private void setSavepointAWithError(Connection connection) throws SQLException {
        PreparedStatement preparedStatementmt = connection.prepareStatement(sqlUserRole);
        preparedStatementmt.executeUpdate();
        connection.commit();
        connection.setSavepoint("SAVEPOINT");
        preparedStatementmt = connection.prepareStatement(sqlRoleError);
        preparedStatementmt.executeUpdate();
        connection.commit();
    }
}
