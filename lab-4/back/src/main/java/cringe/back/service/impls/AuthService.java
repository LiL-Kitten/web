package cringe.back.service.impls;

import cringe.back.dao.UserDAO;
import cringe.back.entity.User;
import cringe.back.exceptions.InvalidPasswordException;
import cringe.back.exceptions.UserNotFoundException;
import cringe.back.service.Service;
import cringe.back.service.ServiceName;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class AuthService extends Service<User> {

    @EJB
    private UserDAO userDAO;

    public AuthService() {
        super(ServiceName.AUTHENTICATION);
    }

    @Override
    public String execute(User user) {
        if(userDAO.exists(user.getUsername())) {
            if (userDAO.check(user.getUsername(), user.getPassword())) {
                return "User authenticated successfully: " + user.getUsername();
            }
            throw new InvalidPasswordException("Invalid password");
        }
        throw new UserNotFoundException("User not found: " + user.getUsername());
    }
}
