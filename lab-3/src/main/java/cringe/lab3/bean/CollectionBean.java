package cringe.lab3.bean;

import cringe.lab3.dto.Observer;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CollectionBean implements Serializable, Subject {
    private final  List<Observer> observers = new ArrayList<>();
    private final List<Point> points = new ArrayList<>();

    public List<Point> getPoints() {
        return points;
    }

    public void save(List<Point> points) {
        this.points.addAll(points);
        notifyToSave(points);
    }

    public void delete() {
        points.clear();
        notifyToDelete();
    }


    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyToSave(List<Point> points) {
        for (Observer observer : observers) {
            observer.save(points);
        }
    }

    @Override
    public void notifyToDelete() {
        for (Observer observer : observers) {
            observer.delete();
        }
    }
}

