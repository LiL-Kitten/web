package cringe.back.service;

import cringe.back.dao.PointDAO;
import cringe.back.dao.UserDAO;
import cringe.back.dto.PointDTO;
import cringe.back.exceptions.EmptyDBException;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class UserService {
    @EJB
    private UserDAO userDAO;

    @EJB
    private PointDAO pointDAO;

    public List<PointDTO> getPoints(Long id) throws EmptyDBException {
        List<PointDTO> list = pointDAO.findAll(id);
        if (list.isEmpty()) {
            throw new EmptyDBException("простите но в бд ничего нет поэтому отправьте свой первый запрос");
        }
        return list;
    }

    public String delPoints(Long id) throws EmptyDBException {
        if (pointDAO.findAll(id).isEmpty()) {
            throw new EmptyDBException("Нет точек для удаления");
        }

        pointDAO.deleteAll(id);

        return "All points deleted";
    }

    public String addPoints(Long userId, PointDTO pointDTO) {
        pointDAO.save(userDAO.findById(userId), pointDTO);

        return "Point added successfully";
    }
}
