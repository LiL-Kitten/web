package cringe.lab3.bean;

import cringe.lab3.dto.Observer;

import java.util.List;

public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyToSave(List<Point> points);
    void notifyToDelete();
}
