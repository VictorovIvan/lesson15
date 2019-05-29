package object;

/**
 * Class UserRole
 */
public class UserRole {
    public UserRole(Integer id, Integer user_id, Integer role_id) {
        this.id = id;
        this.user_id = user_id;
        this.role_id = role_id;
    }

    public Integer id;
    public Integer user_id;
    public Integer role_id;

    @Override
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", role_id=" + role_id +
                '}';
    }
}
