package dao;

import object.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class UserRoleDAO
 */
public class UserRoleDAO {
    private Connection connectDataBase;

    /**
     * Constructor RoleDAO
     * @param connectDataBase Creates a Statement object for sending SQL statements to the database.
     */
    public UserRoleDAO(Connection connectDataBase) {
        this.connectDataBase = connectDataBase;
    }

    /**
     * Insert object UserRole to database USER_ROLE with parametric query
     *
     * @param userRole Object of the UserRole
     */
    public void addRoleParametric(UserRole userRole) {
        try (PreparedStatement prepStatement = connectDataBase.prepareStatement("INSERT INTO \"public\".\"USER_ROLE\" VALUES (?, ?, ?)")) {
            prepStatement.setInt(1, userRole.id);
            prepStatement.setInt(2, userRole.user_id);
            prepStatement.setInt(3, userRole.role_id);
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert object UserRole to database USER_ROLE with batch
     *
     * @param userRole Object of userRole
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */
    public void addUserRoleBatch(UserRole userRole) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = HandlerUserRole(userRole);
        preparedStatement.addBatch();
        preparedStatement.executeBatch();
    }

    /**
     * Handler SQL from user object UserRole
     *
     * @param userRoleHandler Object of the UserRole
     * @return Modifed Prepared Statement
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */
    private PreparedStatement HandlerUserRole(UserRole userRoleHandler) throws SQLException {
        PreparedStatement preparedStatementHandler = connectDataBase.prepareStatement("INSERT INTO \"public\".\"USER_ROLE\" VALUES (?, ?, ?)");
        preparedStatementHandler.setInt(1, userRoleHandler.id);
        preparedStatementHandler.setInt(2, userRoleHandler.user_id);
        preparedStatementHandler.setInt(3, userRoleHandler.role_id);
        return preparedStatementHandler;
    }
}
