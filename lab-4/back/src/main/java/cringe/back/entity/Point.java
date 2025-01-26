package cringe.back.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "POINT_TABLE")
public class Point extends AbstractEntity implements Serializable {

    private float x;
    private float y;
    private float r;
    private boolean condition;
    private String date;
    private long time;

    @ManyToOne
    private User user;

    public Point(float x, float y, float r, boolean condition) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.condition = condition;
    }

    public Point() {}

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

    public boolean isCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.date = now.format(formatter);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
