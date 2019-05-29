package dao;

import object.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class UserDAO
 */
public class UserDAO {
    public Connection connectDataBase;

    /**
     * Constructor UserDAO
     * @param connectDataBase Creates a Statement object for sending SQL statements to the database.
     */
    public UserDAO(Connection connectDataBase) {
        this.connectDataBase = connectDataBase;
    }


    /**
     * Insert object User to database USER with parametric query
     *
     * @param user Object of the User
     */
    public void addUserParametric(User user) throws SQLException {
        PreparedStatement prepStatement;
        prepStatement = HandlerUser(user);
        prepStatement.executeUpdate();
    }

    /**
     * Insert object User to database USER with batch
     *
     * @param user Object user
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */
    public void addUserBatch(User user) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = HandlerUser(user);
        preparedStatement.addBatch();
        preparedStatement.executeBatch();
    }

    /**
     * Handler SQL from user object
     *
     * @param userHandler Object of the User
     * @return Modifed Prepared Statement
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */
    private PreparedStatement HandlerUser(User userHandler) throws SQLException {
        PreparedStatement preparedStatementHandler = connectDataBase.prepareStatement("INSERT INTO \"public\".\"USER\" VALUES (?, ?, ?, ?, ?, ?, ?)");
        preparedStatementHandler.setInt(1, userHandler.id);
        preparedStatementHandler.setString(2, userHandler.name);
        preparedStatementHandler.setObject(3, userHandler.birthday);
        preparedStatementHandler.setInt(4, userHandler.login_id);
        preparedStatementHandler.setString(5, userHandler.description);
        preparedStatementHandler.setString(6, userHandler.email);
        preparedStatementHandler.setString(7, userHandler.description);
        return preparedStatementHandler;
    }

    /**
     * Get user using login_id and name
     * @return
     * @throws SQLException
     */
    public User getUserByLoginIdAndName(Integer userLoginId, String userName) throws SQLException {
        User user = null;
        PreparedStatement prepStatement = connectDataBase.prepareStatement("SELECT * FROM \"public\".\"USER\" WHERE (login_id = ?) AND (name = ?)");
        prepStatement.setInt(1, userLoginId);
        prepStatement.setString(2, userName);
        User queryUser = null;
        ResultSet resultSet = prepStatement.executeQuery();
        if (resultSet.next()) {
            queryUser = new User(resultSet.getInt("id"), resultSet.getString("name"),
                    resultSet.getObject("birthday", LocalDate.class), resultSet.getInt("login_id"),
                    resultSet.getString("city"), resultSet.getString("email"),
                    resultSet.getString("description"));
        }
        return queryUser;
    }
}
