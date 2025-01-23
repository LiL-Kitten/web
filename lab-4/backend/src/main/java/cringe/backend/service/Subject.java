package cringe.backend.service;

import cringe.backend.entity.Point;
import cringe.backend.db.Observer;

import java.util.List;

public interface Subject {
    void attach(Observer ... observer);
    void detach(Observer ... observer);
    void notifyToSave(List<Point> points);
    void notifyToDelete();
}
