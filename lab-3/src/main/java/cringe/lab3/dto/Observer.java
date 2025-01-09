package cringe.lab3.dto;

import cringe.lab3.bean.Point;

import java.util.List;

public interface Observer {
    void save(List<Point> points);
    void delete();
}
