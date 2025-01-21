package cringe.lab3.service;

import cringe.lab3.entity.Point;
import cringe.lab3.service.commands.AreaChecker;
import cringe.lab3.service.commands.DeletePoints;
import cringe.lab3.service.commands.SavePoints;
import cringe.lab3.db.Observer;

import java.util.EnumMap;
import java.util.List;

public class ServiceManager {
    private final EnumMap<ServicesName, Service> services = new EnumMap<>(ServicesName.class);

    public ServiceManager() {
        addService(new AreaChecker());
        addService(new DeletePoints());
        addService(new SavePoints());
    }

    public void addService(Service service) {
        services.put(service.getServiceName(), service);
    }

    public void registerObserver(Observer... observer) {
        for (Service service : services.values()) {
            service.attach(observer);
        }
    }

    public void unregisterObserver(Observer... observer) {
        for (Service service : services.values()) {
            service.detach(observer);
        }
    }

    public void execute(ServicesName serviceName, List<Point> points) {
        Service service = services.get(serviceName);
        service.action(points);
    }

}
