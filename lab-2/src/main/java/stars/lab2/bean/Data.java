package stars.lab2.bean;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public final class Data {

    public Data(float x, float y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;

        createData();
    }

    private final float x;
    private final float y;
    private final float r;

    private boolean condition;

    private String date;

    private long time;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getR() {
        return r;
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

    public boolean validate() {
        double scaledX = x ;
        double scaledY = y ;

        boolean inRectangle = (scaledX >= 0 && scaledX <= r && scaledY >= -r && scaledY <= 0);
        boolean inTriangle = (scaledX >= 0 && scaledX <= r && scaledY >= 0 && scaledY <= r / 2 && scaledY <= (-0.5 * scaledX + r / 2));
        boolean inCircle = (scaledX < 0 && scaledY < 0 && (Math.pow(scaledX, 2) + Math.pow(scaledY, 2) <= Math.pow(r / 2, 2)));

        return inRectangle || inTriangle || inCircle;
    }

    @Override
    public String toString() {
        return String.format("Data{x=%.2f, y=%.2f, r=%.2f, condition=%b, date='%s', time=%d nanoseconds}",
                x, y, r, condition, date, time);
    }

}
