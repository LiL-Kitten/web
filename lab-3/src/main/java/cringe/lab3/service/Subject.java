package cringe.lab3.service;

import cringe.lab3.entity.Point;
import cringe.lab3.db.Observer;

import java.util.List;

public interface Subject {
    void attach(Observer ... observer);
    void detach(Observer ... observer);
    void notifyToSave(List<Point> points);
    void notifyToDelete();
}
