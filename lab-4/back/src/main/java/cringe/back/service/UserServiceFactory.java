package cringe.back.service;

import cringe.back.dto.UserDTO;
import cringe.back.service.impls.*;
import jakarta.ejb.EJB;

public class UserServiceFactory implements ServiceFactory {
    @EJB
    private AddPointService addPointService;

    @EJB
    private DelPointsService delPointsService;

    @EJB
    private GetPointsService getPointsService;

    @Override
    public Service<UserDTO> createService(ServiceName name) {
        return switch (name) {
            case DELETE_POINTS -> delPointsService;
            case GET_POINTS -> getPointsService;
            default -> throw new IllegalArgumentException("Unknown service " + name);
        };
    }

    public AddPointService getAddPointService() {
        return addPointService;
    }

}
