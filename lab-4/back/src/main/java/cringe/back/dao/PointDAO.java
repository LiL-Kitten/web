package cringe.back.dao;

import cringe.back.dto.PointDTO;
import cringe.back.entity.User;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface PointDAO {
    void save(User user, PointDTO point);

    void deleteAll(Long userId);

    List<PointDTO> findAll(Long userId);
}
