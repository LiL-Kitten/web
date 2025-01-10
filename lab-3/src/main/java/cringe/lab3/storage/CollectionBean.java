package cringe.lab3.storage;

import cringe.lab3.bean.Point;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CollectionBean implements Serializable, Observer {
    private final List<Point> points = new ArrayList<>();

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public void save(List<Point> points) {
        this.points.addAll(points);
    }

    @Override
    public void delete() {
        points.clear();
    }
}

