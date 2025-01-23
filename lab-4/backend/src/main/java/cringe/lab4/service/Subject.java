package cringe.lab4.service;

import cringe.lab4.entity.Point;
import cringe.lab4.db.Observer;

import java.util.List;

public interface Subject {
    void attach(Observer ... observer);
    void detach(Observer ... observer);
    void notifyToSave(List<Point> points);
    void notifyToDelete();
}
