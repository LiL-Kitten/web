package cringe.back.service.impls;

import cringe.back.dao.UserDAO;
import cringe.back.dto.UserDTO;
import cringe.back.service.ServiceName;
import cringe.back.service.ServiceResponse;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class RegistrationService extends Service<UserDTO> {

    @EJB
    UserDAO userDAO;

    public RegistrationService() {
        super(ServiceName.REGISTRATION);
    }

    @Override
    public ServiceResponse<UserDTO> execute(UserDTO userDTO) {
        if (userDAO.exists(userDTO)) {
            return new ServiceResponse<>(false, "Вы уже зарегистрированы попробуйте " +
                    "вспомнить пароль и войти");
        }

        userDAO.save(userDTO);
        return new ServiceResponse<>(true, "Пользователь успешно зарегистрирован \n UserName:"
                + userDTO.getUsername() + "\nUserId:" + userDAO.getId(userDTO));
    }
}
