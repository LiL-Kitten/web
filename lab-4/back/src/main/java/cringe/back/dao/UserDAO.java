package cringe.back.dao;

import cringe.back.entity.User;
import jakarta.ejb.Remote;

@Remote
public interface UserDAO {
    void save(User user);

    boolean exists(String username);

    boolean check(String username, long password);
}
