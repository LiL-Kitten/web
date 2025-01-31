package cringe.back.service;

import cringe.back.dao.PointDAO;
import cringe.back.dao.UserDAO;
import cringe.back.dto.PointDTO;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class UserServiceFactory {
    @EJB
    private UserDAO userDAO;

    @EJB
    private PointDAO pointDAO;

    public ServiceResponse<List<PointDTO>> getPoints(Long id) {
        List<PointDTO> list = pointDAO.findAll(id);
        if (list.isEmpty()) {
            return new ServiceResponse<>(true, "У вас нет каких либо точек сделайте первый запрос!");
        }
        return new ServiceResponse<>(true, "Success", list);
    }

    public ServiceResponse<String> delPoints(Long id) {
        pointDAO.deleteAll(id);

        return new ServiceResponse<>(true, "All points deleted");
    }

    public ServiceResponse<String> addPoints(Long userId, PointDTO pointDTO) {
        pointDAO.save(userDAO.findById(userId), pointDTO);

        return new ServiceResponse<>(true, "Point added successfully");
    }
}
