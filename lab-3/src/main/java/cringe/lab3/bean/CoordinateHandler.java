package cringe.lab3.bean;

import cringe.lab3.entity.Point;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named
public class CoordinateHandler implements Serializable {

    private float y;
    private float r;

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

        return points;
    }
}
