package cringe.back.service;

import cringe.back.dao.UserDAO;
import cringe.back.dto.UserDTO;
import cringe.back.exceptions.InvalidPasswordException;
import cringe.back.exceptions.UserExistException;
import cringe.back.exceptions.UserNotFoundException;
import cringe.back.util.JwtUtil;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class AuthServiceFactory {
    @EJB
    private UserDAO userDAO;
    
    public ServiceResponse<String> authenticate(UserDTO user) throws UserNotFoundException, InvalidPasswordException {
        if(userDAO.exists(user)) {
            if (userDAO.authenticate(user)) {
                String token = new JwtUtil().generateToken(userDAO.getId(user));
                return new ServiceResponse<>(true, "Successfully logged in", token);
            }
            throw new InvalidPasswordException("неверный пароль((");
        }
        throw new UserNotFoundException("пользователь с таким username не найден: " + user.getUsername());
    }
    
    public ServiceResponse<String> registration(UserDTO user) throws UserExistException {
        if (userDAO.exists(user)) {
            throw new UserExistException("Вы уже зарегистрированы попробуйте вспомнить пароль и войти");
        }

        userDAO.save(user);
        String token = new JwtUtil().generateToken(userDAO.getId(user));
        return new ServiceResponse<>(true, "Пользователь успешно зарегистрирован \n UserName:"
                + user.getUsername() + "\nUserId:" + userDAO.getId(user), token);
    }
    
}
