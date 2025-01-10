package cringe.lab3.service;

import cringe.lab3.bean.Point;
import cringe.lab3.storage.Observer;

import java.util.List;

public interface Subject {
    void attach(Observer ... observer);
    void detach(Observer ... observer);
    void notifyToSave(List<Point> points);
    void notifyToDelete();
}
