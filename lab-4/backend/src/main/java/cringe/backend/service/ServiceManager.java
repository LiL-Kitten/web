package cringe.backend.service;

import cringe.backend.entity.Point;
import cringe.backend.service.commands.AreaChecker;
import cringe.backend.service.commands.DeletePoints;
import cringe.backend.service.commands.SavePoints;
import cringe.backend.db.Observer;

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
