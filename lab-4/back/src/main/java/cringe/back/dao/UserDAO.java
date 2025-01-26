package cringe.back.dao;

import cringe.back.dto.UserDTO;
import jakarta.ejb.Remote;

@Remote
public interface UserDAO {
    void save(UserDTO userDTO);

    boolean exists(UserDTO userDTO);

    boolean authenticate(UserDTO userDTO);

    Long getId(UserDTO userDTO);
}
