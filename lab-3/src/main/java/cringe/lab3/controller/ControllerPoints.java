package cringe.lab3.controller;

import cringe.lab3.bean.Point;
import cringe.lab3.services.AreaChecker;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Named
public class ControllerPoints implements Serializable {

    @Serial
    private static final long serialVersionUID = 1351351341461314151L;

    private final List<Point> list = new ArrayList<>();
    private final List<Float> checkboxValues = List.of(-4.0f, -3.0f, -2.0f, -1.0f, 0.0f, 1.0f, 2.0f, 3.0f, 4.0f);
    private final AreaChecker areaChecker = new AreaChecker();
    private Point point = new Point();

    @Inject
    public ControllerPoints() {
        System.out.println("ControllerPoints");
    }

    public void setX(float x) {
        point.setX(x);
    }

    public void setY(float y) {
        point.setY(y);
    }

    public void setR(float r) {
        point.setR(r);
    }

    public List<Point> getList() {
        return list;
    }

    public void addData(Point data) {
        list.add(data);
    }

    public List<Float> getCheckboxValues() {
        return checkboxValues;
    }

    public Point getPoint() {
        return point;
    }

    public void createData() {
        List<Float> selectedXValues = new ArrayList<>();
        for (int i = 0; i < checkboxValues.size(); i++) {
            if (point.getCheckboxStates()[i]) {
                selectedXValues.add(checkboxValues.get(i));
            }
        }

        float y = point.getY();
        float r = point.getR();

        for (float x : selectedXValues) {
            Point newPoint = new Point();
            newPoint.setX(x);
            newPoint.setY(y);
            newPoint.setR(r);
            newPoint.setDate();

            var start = System.nanoTime();
            boolean condition = areaChecker.check(x, y, r);
            var end = System.nanoTime();
            newPoint.setCondition(condition);
            newPoint.setTime(end - start);

            addData(newPoint);
        }

        point = new Point();
    }

    public void clearList() {
        list.clear();
        System.out.println("clearList");
    }

}
