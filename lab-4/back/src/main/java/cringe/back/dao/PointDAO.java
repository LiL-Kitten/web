package cringe.back.dao;

import cringe.back.entity.Point;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface PointDAO {
    boolean checkArea(float x, float y, float r);

    void save(Point point);

    void deleteAll(Long userId);

    List<Point> findAll(Long userId);
}
