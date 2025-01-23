package cringe.lab4.db;

import cringe.lab4.entity.Point;

import java.util.List;

public interface Observer {
    void save(List<Point> points);
    void delete();
    List<Point> getPoints();
}
