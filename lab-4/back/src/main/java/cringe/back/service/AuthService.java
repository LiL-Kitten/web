package cringe.back.service;

import cringe.back.dao.UserDAO;
import cringe.back.dto.UserDTO;
import cringe.back.exceptions.InvalidPasswordException;
import cringe.back.exceptions.UserExistException;
import cringe.back.exceptions.UserNotFoundException;
import cringe.back.util.JwtUtil;
import cringe.back.util.PasswordHashing;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class AuthService {
    @EJB
    private UserDAO userDAO;

    private final PasswordHashing passwordHashing = new PasswordHashing();

    public String authenticate(UserDTO user) throws UserNotFoundException, InvalidPasswordException {
        String storedHash = userDAO.getUserPassword(user.getUsername());

        if (storedHash != null) {
            if (passwordHashing.verify(user.getPassword(), storedHash)) {
                user.setPassword(passwordHashing.hash(user.getPassword()));
                return new JwtUtil().generateToken(userDAO.getId(user));
            } else {
                throw new InvalidPasswordException("Неверный пароль((");
            }
        } else {
            throw new UserNotFoundException("Пользователь с таким username не найден: " + user.getUsername());
        }
    }

    public String registration(UserDTO user) throws UserExistException {
        if (userDAO.exists(user)) {
            throw new UserExistException("Вы уже зарегистрированы. Попробуйте вспомнить пароль и войти.");
        }

        user.setPassword(passwordHashing.hash(user.getPassword()));
        userDAO.save(user);

        Long userId = userDAO.getId(user);

        return new JwtUtil().generateToken(userId);
    }
}