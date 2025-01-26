package cringe.back.service;

import cringe.back.service.impls.Service;

public interface ServiceFactory {
    Service<?> createService(ServiceName name);
}
