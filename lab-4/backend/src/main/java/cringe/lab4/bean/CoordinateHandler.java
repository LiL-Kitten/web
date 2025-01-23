package cringe.lab4.bean;

import cringe.lab4.entity.Point;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CoordinateHandler implements Serializable {

    private Float x;
    private float y;
    private float r;

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public List<Point> createPoints(Float[] values) {
        List<Point> points = new ArrayList<>();

        for (Float xValue : values) {
            Point point = new Point(xValue, y, r);
            points.add(point);
        }

        if (x != null) points.add(new Point(x, y, r));

        return points;
    }
}
