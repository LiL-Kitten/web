package cringe.lab4.service.commands;

import cringe.lab4.entity.Point;
import cringe.lab4.service.Service;
import cringe.lab4.service.ServicesName;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AreaChecker extends Service {

    public AreaChecker() {
        super(ServicesName.AREA_CHECKER);
    }

    @Override
    public void action(List<Point> points) {
        points.forEach(point -> {
            var start = Instant.now();

            float x = point.getX();
            float y = point.getY();
            float r = point.getR();

            boolean inRectangle = (x <= 0 && x >= -r && y >= -r / 2 && y <= 0);
            boolean inTriangle = (x <= 0 && y >= 0 && y <= 0.5 * (x + r));
            boolean inCircle = (x >= 0 && y <= 0 && (x * x + y * y <= r / 2 * r / 2));
            boolean condition = inRectangle || inTriangle || inCircle;

            point.setCondition(condition);

            var finish = Instant.now();
            var time = ChronoUnit.NANOS.between(start, finish);

            point.setTime(time);
            point.setDate();
        });
    }
}
