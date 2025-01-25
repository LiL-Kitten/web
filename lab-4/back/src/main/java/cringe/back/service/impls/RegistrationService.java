package cringe.back.service.impls;

import cringe.back.dao.UserDAO;
import cringe.back.entity.User;
import cringe.back.service.Service;
import cringe.back.service.ServiceName;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class RegistrationService extends Service<User> {

    @EJB
    UserDAO userDAO;

    public RegistrationService() {
        super(ServiceName.REGISTRATION);
    }

    @Override
    public String execute(User user) {
        userDAO.save(user);
        return "User registered successfully: " + user.getUsername() + "\nuser id:" + user.getId();
    }
}
