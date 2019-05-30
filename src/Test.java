import jdbc.ConnectDataBase;
import jdbc.RoleJDBC;
import jdbc.UserJDBC;
import jdbc.UserRoleJDBC;
import object.Role;
import object.User;
import object.UserRole;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.time.LocalDate;
import java.time.Month;

/*
1) Спроектировать базу
  - Таблица USER содержит поля id, name, birthday, login_ID, city, email, description
  - Таблица ROLE содержит поля id, name (принимает значения Administration, Clients, Billing), description
  - Таблица USER_ROLE содержит поля id, user_id, role_id

Типы полей на ваше усмотрению, возможно использование VARCHAR(255)

2) Через jdbc интерфейс сделать запись данных(INSERT) в таблицу
  a) Используя параметризированный запрос
  b) Используя batch процесс

3) Сделать параметризированную выборку по login_ID и name одновременно

4) Перевести connection в ручное управление транзакциями
  a) Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE) между sql
     операциями установить логическую точку сохранения(SAVEPOINT)
  б) Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE) между sql
     операциями установить точку сохранения (SAVEPOINT A), намеренно ввести некорректные данные на последней операции,
     что бы транзакция откатилась к логической точке SAVEPOINT A
 */

public class Test {
    public static void main(String[] args) throws SQLException {
        LocalDate localDate = LocalDate.of(1950, Month.DECEMBER, 30);
        Role someRole = new Role(1, Role.enumName.Administration, "Some description about Administration");
        User someUser = new User(1, "ABC", localDate, 1, "London", "abra@cadab.ra", "Somebody user");
        UserRole someUserRole = new UserRole(1, 0, 0);
        Connection connectDataBase = ConnectDataBase.connectionDataBase(null);
        connectDataBase.setAutoCommit(false);
        RoleJDBC roleJDBC = new RoleJDBC(connectDataBase);
        UserJDBC userJDBC = new UserJDBC(connectDataBase);
        UserRoleJDBC userRoleJDBC = new UserRoleJDBC(connectDataBase);

        // Task02.a
//        roleJDBC.addRoleParametric(someRole);
//        connectDataBase.commit();
//        userJDBC.addUserParametric(someUser);
//        connectDataBase.commit();
//        userRoleJDBC.addRoleParametric(someUserRole);
//        connectDataBase.commit();
        // Task02.b
//        roleJDBC.addRoleBatch(someRole);
//        connectDataBase.commit();
//        userJDBC.addUserBatch(someUser);
//        connectDataBase.commit();
//        userRoleJDBC.addUserRoleBatch(someUserRole);
//        connectDataBase.commit();

        // Task03
        User findingUser;
        findingUser = userJDBC.getUserByLoginIdAndName(1, "ABC");
        if (findingUser != null) {
            System.out.println(findingUser.toString());
        } else {
            System.out.println("No query");
        }

        // Task04.a
//        roleJDBC.addRoleParametric(someRole);
//        connectDataBase.commit();
//        Savepoint savepoint01 = connectDataBase.setSavepoint();
//        userJDBC.addUserParametric(someUser);
//        connectDataBase.commit();
//        Savepoint savepoint02 = connectDataBase.setSavepoint();
//        userRoleJDBC.addRoleParametric(someUserRole);
//        connectDataBase.commit();
//        Savepoint savepoint03 = connectDataBase.setSavepoint();

        // Task04.b
        roleJDBC.addRoleParametric(someRole);
        connectDataBase.commit();
        Savepoint savepointA01 = connectDataBase.setSavepoint();
        userJDBC.addUserParametric(someUser);
        connectDataBase.commit();
        Savepoint savepointA02 = connectDataBase.setSavepoint();
        try{
        userRoleJDBC.addRoleParametric(null);}
        catch(NullPointerException nullPointer){
            connectDataBase.rollback(savepointA02);
            System.out.println("Return to savepointA02");
            }
        }
}
