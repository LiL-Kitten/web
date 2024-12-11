package cringe.lab3.bean;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Data implements Serializable {

    @Serial
    private static final long serialVersionUID = 2524062387050598152L;

    public Data(double x, double y, double r) {
        this.x = x;
        this.y = roundY(y);
        this.r = r;

        createData();
    }

    @DecimalMin("-3")
    @DecimalMax("3")
    private final double x;
    private final double y;
    @DecimalMin("2")
    @DecimalMax("5")
    private final double r;

    private boolean condition;

    private String date;

    private long time;

    public double getX() { return x; }

    public double getY() { return y; }

    public double getR() { return r; }

    public boolean isCondition() {
        return condition;
    }

    public String getDate() {
        return date;
    }

    public long getTime() { return time; }

    public void setDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.date = now.format(formatter);
    }

    public void createData() {
        var start = System.nanoTime();
        this.condition = validate();
        var end = System.nanoTime();

        this.time = end - start;

        setDate();
    }

    public boolean validate() {

        boolean inRectangle = (x >= 0 && x <= r &&
                y >= -r && y <= 0);

        boolean inTriangle = (x >= 0 && x <= r &&
                y >= 0 && y <= r/2 &&
                y <= r/2 - (x * 0.5));

        boolean inCircle = (x < 0 && y < 0 &&
                (x * x + y * y <= r/2 * r/2));

        return inRectangle || inTriangle || inCircle;
    }

    @Override
    public String toString() {
        return String.format("Data{x=%.2f, y=%.2f, r=%.2f, condition=%b, date='%s', time=%d nanoseconds}",
                x, y, r, condition, date, time);
    }

    private double roundY(double y) {
        return Math.round(y * 100.0) / 100.0; // Округление до двух знаков после запятой
    }
}
