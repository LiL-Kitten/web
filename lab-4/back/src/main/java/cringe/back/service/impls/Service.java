package cringe.back.service.impls;

import cringe.back.service.ServiceName;
import cringe.back.service.ServiceResponse;
import jakarta.ejb.Stateless;

@Stateless
public abstract class Service<T> {
    private final ServiceName serviceName;

    protected Service(ServiceName serviceName) {
        this.serviceName = serviceName;
    }

    public abstract ServiceResponse<?> execute(T argument);

    public ServiceName getServiceName() {
        return serviceName;
    }
}
