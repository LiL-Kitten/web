package cringe.lab4.service;

import cringe.lab4.entity.Point;
import cringe.lab4.db.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Service implements Subject {
    private final  List<Observer> observers = new ArrayList<>();
    private final ServicesName serviceName;

    protected Service(ServicesName serviceName) {
        this.serviceName = serviceName;
    }

    public ServicesName getServiceName() {
        return serviceName;
    }

    public abstract void action(List<Point> points);

    @Override
    public void attach(Observer... observers) {
        Collections.addAll(this.observers, observers);
    }

    @Override
    public void detach(Observer... observers) {
        for (Observer observer : observers) {
            this.observers.remove(observer);
        }
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
