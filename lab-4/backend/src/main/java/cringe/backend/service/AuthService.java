package cringe.backend.service;

import cringe.backend.dao.UserDAO;
import cringe.backend.entity.User;
import jakarta.ejb.EJB;

public class AuthService {

    @EJB
    private UserDAO userDAO;

    public String registerUser(String username, long password) {
        if (!userDAO.exists(username)) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            userDAO.save(newUser);
            return "User registered successfully: " + username;
        }
        return "User already exists: " + username;
    }

    public String authenticateUser(String username, long password) {
        if (userDAO.check(username, password)) {
            return "User authenticated successfully: " + username;
        }
        return "Authentication failed for user: " + username;
    }
}
