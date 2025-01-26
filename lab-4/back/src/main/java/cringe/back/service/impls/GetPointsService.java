package cringe.back.service.impls;

import cringe.back.dao.PointDAO;
import cringe.back.dao.UserDAO;
import cringe.back.dto.PointDTO;
import cringe.back.dto.UserDTO;
import cringe.back.exceptions.UserNotFoundException;
import cringe.back.service.ServiceName;
import cringe.back.service.ServiceResponse;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class GetPointsService extends Service<UserDTO> {

    @EJB
    PointDAO pointDAO;

    @EJB
    UserDAO userDAO;

    public GetPointsService() {
        super(ServiceName.GET_POINTS);
    }

    @Override
    public ServiceResponse<List<PointDTO>> execute(UserDTO user) {

        if(userDAO.exists(user)){
            List<PointDTO> list = pointDAO.findAll(userDAO.getId(user));
            if(list.isEmpty()){
                return new ServiceResponse<>(true, "У вас нет каких либо точек сделайте первый запрос!");
            }
            return new ServiceResponse<>(true, "Success", list);
        }

        throw new UserNotFoundException("простите но судя по всему вы не авторизованный пользователь" +
                " и вы не получите никаких точек!");
    }
}
