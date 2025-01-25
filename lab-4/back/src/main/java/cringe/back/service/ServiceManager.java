package cringe.back.service;

import cringe.back.entity.User;
import cringe.back.service.impls.*;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.EnumMap;

@Stateless
public class ServiceManager {
    private final EnumMap<ServiceName, Service<User>> services = new EnumMap<>(ServiceName.class);

    @EJB
    private AddPointService addPointService;

    @EJB
    private AreaCheckerService areaCheckerService;

    @EJB
    private AuthService authService;

    @EJB
    private DelPointsService delPointsService;

    @EJB
    private GetPointsService getPointsService;

    @EJB
    private RegistrationService registrationService;

    @PostConstruct
    public void init() {
        addService(authService,
//                addPointService,
//                areaCheckerService,
                delPointsService,
                getPointsService,
                registrationService);
    }

    @SafeVarargs
    public final void addService(Service<User>... service) {
        for (Service<User> s : service) {
            services.put(s.getServiceName(), s);
        }
    }

    public String execute(ServiceName serviceName, Object argument) {
        Service service = services.get(serviceName);
        return service.execute(argument);
    }
}
