package server;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Data implements Validate {
    private float x;
    private float y;
    private float r;

    private boolean condition;

    private String date;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private LocalDateTime now = LocalDateTime.now();
    private long time;

    Data(float x, float y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;

        var start = Instant.now();
        this.condition = validate();
        var end = Instant.now();

        this.time = ChronoUnit.NANOS.between(start, end);

        this.date = now.format(formatter);


    }

    public float getR() {
        return r;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public boolean isCondition() {
        return condition;
    }

    public String getDate() {
        return date;
    }

    public long getTime() {
        return time;
    }

    @Override
    public boolean validate() {
        return ((y <= r) && (y >= 0) && (x >= -r) && (x <= 0)) ||
                ((x >= 0) && (y >= 0) && (y <= (-0.5 * x + (double) r / 2))) ||
                ((Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2)) && (x <= 0) && (y <= 0));
    }

    @Override
    public String toString() {
        return String.format("Data{x=%.2f, y=%.2f, r=%.2f, condition=%b, date='%s', time=%d nanoseconds}",
                x, y, r, condition, date, time);
    }

}
