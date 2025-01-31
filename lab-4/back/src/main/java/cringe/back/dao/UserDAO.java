package cringe.back.dao;

import cringe.back.dto.UserDTO;
import cringe.back.entity.User;
import jakarta.ejb.Remote;

@Remote
public interface UserDAO {
    void save(UserDTO userDTO);

    boolean exists(UserDTO userDTO);

    boolean authenticate(UserDTO userDTO);

    Long getId(UserDTO userDTO);

    public User findById(Long id);
}
