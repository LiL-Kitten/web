package cringe.back.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private String username;
    private long password;

    public UserDTO(String username, long password) {
        this.username = username;
        this.password = password;
    }

    public UserDTO() {}

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
