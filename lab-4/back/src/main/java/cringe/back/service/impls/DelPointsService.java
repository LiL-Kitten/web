package cringe.back.service.impls;

import cringe.back.dao.PointDAO;
import cringe.back.dao.UserDAO;
import cringe.back.dto.UserDTO;
import cringe.back.exceptions.UserNotFoundException;
import cringe.back.service.ServiceName;
import cringe.back.service.ServiceResponse;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class DelPointsService extends Service<UserDTO> {

    @EJB
    PointDAO pointDAO;

    @EJB
    UserDAO userDAO;

    public DelPointsService() {
        super(ServiceName.DELETE_POINTS);
    }

    @Override
    public ServiceResponse<UserDTO> execute(UserDTO userDTO) {

        if (userDAO.exists(userDTO)) {
            pointDAO.deleteAll(userDAO.getId(userDTO));

            return new ServiceResponse<>(true, "All points deleted");
        }
        throw new UserNotFoundException("простите но точки удалить не выйдет, пользователь с вашим логином не найден(");
    }
}
