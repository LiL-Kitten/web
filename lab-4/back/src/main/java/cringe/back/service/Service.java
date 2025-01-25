package cringe.back.service;

import jakarta.ejb.Stateless;

@Stateless
public abstract class Service<T> {
    private final ServiceName serviceName;

    public Service(ServiceName serviceName) {
        this.serviceName = serviceName;
    }

    public abstract String execute(T argument);

    public ServiceName getServiceName() {
        return serviceName;
    }
}
