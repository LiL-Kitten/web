package cringe.backend.dao;

import cringe.backend.entity.Point;
import cringe.backend.entity.User;

import java.util.List;

public interface PointDAO {
    void save(String username, Point point);

    void deleteAll(Long userId);

    List<Point> findAll(Long userId);

    boolean exist(String username);

    User findByUsername(String username);
}
