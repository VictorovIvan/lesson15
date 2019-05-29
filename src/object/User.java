package object;

import java.time.LocalDate;

/**
 * Class User
 */
public class User {
    public User(Integer id, String name, LocalDate birthday, Integer login_id, String city, String email, String description) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.login_id = login_id;
        this.city = city;
        this.email = email;
        this.description = description;
    }

    public Integer id;
    public String name;
    public LocalDate birthday;
    public Integer login_id;
    public String city;
    public String email;
    public String description;

    @Override
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", login_id=" + login_id +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
