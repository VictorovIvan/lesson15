package task03;

import java.sql.*;

/**
 * Class Task03
 */
public class Task03 {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/Task01";
    static final String USER = "postgres";
    static final String PASS = "333999";
    static final String sqlQuery = "SELECT t.name, login_id FROM public.\"USER\" t";

    /**
     * SQL query with parametric selection
     */
    public void query() {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            PreparedStatement preparedStatementmt = conn.prepareStatement(sqlQuery);
            preparedStatementmt.executeQuery();
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
}
