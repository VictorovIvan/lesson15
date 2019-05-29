package object;

/**
 * Class Role
 */
public class Role {
    public Role(Integer id, enumName name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public enum enumName{Administration, Clients, Billing}
    public Integer id;
    public enumName name;
    public String description;


    @Override
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name=" + name +
                ", description='" + description + '\'' +
                '}';
    }
}
