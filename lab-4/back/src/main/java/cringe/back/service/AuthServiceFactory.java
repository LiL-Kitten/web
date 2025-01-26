package cringe.back.service;

import cringe.back.dto.UserDTO;
import cringe.back.service.impls.AuthService;
import cringe.back.service.impls.RegistrationService;
import cringe.back.service.impls.Service;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class AuthServiceFactory implements ServiceFactory {
    @EJB
    private AuthService authService;

    @EJB
    private RegistrationService registrationService;

    @Override
    public Service<UserDTO> createService(ServiceName name) {
        return switch (name) {
            case AUTHENTICATION -> authService;
            case REGISTRATION -> registrationService;
            default -> throw new IllegalArgumentException("Unknown service " + name);
        };
    }
}
