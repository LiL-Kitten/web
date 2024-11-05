package server.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public final class Data implements Validate {

    private final float x;
    private final float y;
    private final float r;

    private boolean condition;

    private String date;

    private long time;

    @JsonCreator
    public Data(@JsonProperty("x") float x,
                @JsonProperty("y") float y,
                @JsonProperty("r") float r) {
        this.x = x;
        this.y = y;
        this.r = r;

        createData();
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

    public void setDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.date = now.format(formatter);
    }

    public void createData() {
        var start = Instant.now();
        this.condition = validate();
        var end = Instant.now();

        this.time = ChronoUnit.NANOS.between(start, end);

        setDate();
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
