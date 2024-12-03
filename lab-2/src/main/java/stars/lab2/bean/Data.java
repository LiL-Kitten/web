package stars.lab2.bean;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public final class Data implements Serializable{

    @Serial
    private static final long serialVersionUID = 2524062387050598152L;

    public Data(BigDecimal x, BigDecimal y, BigDecimal r) {
        this.x = x;
        this.y = roundY(y);
        this.r = r;

        createData();
    }

    @DecimalMin("-3")
    @DecimalMax("3")
    private final BigDecimal x;
    private final BigDecimal y;
    @DecimalMin("2")
    @DecimalMax("5")
    private final BigDecimal r;

    private boolean condition;

    private String date;

    private long time;

    public BigDecimal getX() {return x;}

    public BigDecimal getY() {return y;}

    public BigDecimal getR() {return r;}

    public boolean isCondition() {
        return condition;
    }

    public String getDate() {
        return date;
    }

    public long getTime() {return time;}

    private BigDecimal roundY(BigDecimal y) {

        BigDecimal[] validYValues = {
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(1.5),
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(2.5),
                BigDecimal.valueOf(3)
        };

        BigDecimal closestValue = validYValues[0];
        BigDecimal minDiff = y.subtract(closestValue).abs();

        for (int i = 1; i < validYValues.length; i++) {
            BigDecimal diff = y.subtract(validYValues[i]).abs();
            if (diff.compareTo(minDiff) < 0) {
                minDiff = diff;
                closestValue = validYValues[i];
            }
        }

        return closestValue;
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
        BigDecimal scaledX = x ;
        BigDecimal scaledY = y ;
        BigDecimal zero = BigDecimal.ZERO;
        BigDecimal rHalf = r.divide(BigDecimal.valueOf(2));

        boolean inRectangle = (scaledX.compareTo(zero) >= 0 && scaledX.compareTo(r) <= 0 &&
                scaledY.compareTo(rHalf.negate()) >= 0 && scaledY.compareTo(zero) <= 0);

        boolean inTriangle = (scaledX.compareTo(zero) >= 0 && scaledX.compareTo(r) <= 0 &&
                scaledY.compareTo(zero) >= 0 && scaledY.compareTo(rHalf) <= 0 &&
                scaledY.compareTo(rHalf.subtract(scaledX.multiply(BigDecimal.valueOf(0.5)))) <= 0);

        boolean inCircle = (scaledX.compareTo(zero) < 0 && scaledY.compareTo(zero) < 0 &&
                (scaledX.multiply(scaledX).add(scaledY.multiply(scaledY)).compareTo(rHalf.multiply(rHalf)) <= 0));

        return inRectangle || inTriangle || inCircle;
    }

    @Override
    public String toString() {
        return String.format("Data{x=%.2f, y=%.2f, r=%.2f, condition=%b, date='%s', time=%d nanoseconds}",
                x, y, r, condition, date, time);
    }

}
