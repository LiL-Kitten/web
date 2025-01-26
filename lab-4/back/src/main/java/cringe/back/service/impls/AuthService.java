package cringe.back.service.impls;

import cringe.back.dao.UserDAO;
import cringe.back.dto.UserDTO;
import cringe.back.exceptions.InvalidPasswordException;
import cringe.back.exceptions.UserNotFoundException;
import cringe.back.service.ServiceName;
import cringe.back.service.ServiceResponse;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class AuthService extends Service<UserDTO> {

    @EJB
    private UserDAO userDAO;

    public AuthService() {
        super(ServiceName.AUTHENTICATION);
    }

    @Override
    public ServiceResponse<UserDTO> execute(UserDTO user) {
        if(userDAO.exists(user)) {
            if (userDAO.authenticate(user)) {
                return new ServiceResponse<>(true, "Successfully logged in");
            }
            throw new InvalidPasswordException("Invalid password");
        }
        throw new UserNotFoundException("User not found: " + user.getUsername());
    }
}
