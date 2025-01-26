package cringe.back.dao;

import cringe.back.dto.PointDTO;
import cringe.back.dto.UserDTO;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface PointDAO {
    void save(PointDTO point);

    void deleteAll(Long userId);

    List<PointDTO> findAll(Long userId);
}
