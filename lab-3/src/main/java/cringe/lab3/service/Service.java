package cringe.lab3.service;

import cringe.lab3.bean.Point;

import java.util.List;

public abstract class Service {
    private final ServicesName serviceName;

    protected Service(ServicesName serviceName) {
        this.serviceName = serviceName;
    }

    public ServicesName getServiceName() {
        return serviceName;
    }

    public abstract void action(List<Point> points);
}
