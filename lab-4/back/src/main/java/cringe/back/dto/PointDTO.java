package cringe.back.dto;

import java.io.Serializable;

public class PointDTO implements Serializable {
    private float x;
    private float y;
    private float r;
    private boolean condition;

    public PointDTO(float x, float y, float r, boolean condition) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.condition = condition;
    }

    public PointDTO() {}

    public boolean isCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
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

    public boolean areaChecker() {
        boolean inRectangle = (x <= 0 && x >= -r && y >= -r / 2 && y <= 0);
        boolean inTriangle = (x <= 0 && y >= 0 && y <= 0.5 * (x + r));
        boolean inCircle = (x >= 0 && y <= 0 && (x * x + y * y <= r / 2 * r / 2));

        return inRectangle || inTriangle || inCircle;
    }
}