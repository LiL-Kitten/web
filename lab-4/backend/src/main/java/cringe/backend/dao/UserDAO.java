package cringe.backend.dao;

import cringe.backend.entity.User;


public interface UserDAO {
    void save(User user);

    void delete(String username);

    boolean exists(String username);

    boolean check(String username, long password);

    User findByName(String username);
}
