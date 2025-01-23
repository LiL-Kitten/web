package cringe.backend.db;

import cringe.backend.entity.Point;

import java.util.List;

public interface Observer {
    void save(List<Point> points);
    void delete();
    List<Point> getPoints();
}
