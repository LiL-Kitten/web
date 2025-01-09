package cringe.lab3.service;

import cringe.lab3.bean.CollectionBean;
import cringe.lab3.bean.Point;
import cringe.lab3.service.commands.AreaChecker;
import cringe.lab3.service.commands.DeletePoints;
import cringe.lab3.service.commands.SavePoints;

import java.util.EnumMap;
import java.util.List;

public class ServiceManager {
    private final EnumMap<ServicesName, Service> services = new EnumMap<>(ServicesName.class);

    public ServiceManager(CollectionBean collection) {
        addService(new AreaChecker());
        addService(new DeletePoints(collection));
        addService(new SavePoints(collection));
    }

    public void addService(Service service) {
        services.put(service.getServiceName(), service);
    }

    public void execute(ServicesName serviceName, List<Point> points) {
        Service service = services.get(serviceName);
        if (service != null) {
            service.action(points);
        } else {
            throw new IllegalArgumentException("Service not found: " + serviceName);
        }
    }
}
