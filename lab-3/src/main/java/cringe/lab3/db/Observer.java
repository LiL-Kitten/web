package cringe.lab3.db;

import cringe.lab3.entity.Point;

import java.util.List;

public interface Observer {
    void save(List<Point> points);
    void delete();
    List<Point> getPoints();
}
