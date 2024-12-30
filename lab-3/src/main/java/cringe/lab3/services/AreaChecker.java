package cringe.lab3.services;

import java.io.Serializable;

public class AreaChecker implements Serializable {

    public boolean check(float x, float y, float r) {
        boolean inRectangle = (x <= 0 && x >= -r && y >= -r/2 && y <= 0);

        boolean inTriangle = (x <= 0 && y >= 0 && y <= 0.5 * (x + r));

        boolean inCircle = (x >= 0 && y <= 0 && (x * x + y * y <= r / 2 * r / 2));

        return inRectangle || inTriangle || inCircle;
    }
}
