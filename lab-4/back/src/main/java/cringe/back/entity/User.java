package cringe.back.entity;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "USER_TABLE")
@NamedQuery(
        name = "User.findByUsername",
        query = "SELECT u FROM User u WHERE u.username = :username"
)
@NamedQuery(
        name = "User.authenticate",
        query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password"
)
public class User extends AbstractEntity implements Serializable {
    private String username;
    private long password;

    public User(String username, long password) {
        this.username = username;
        this.password = password;
    }

    public User() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getPassword() {
        return password;
    }

    public void setPassword(long password) {
        this.password = password;
    }
}
