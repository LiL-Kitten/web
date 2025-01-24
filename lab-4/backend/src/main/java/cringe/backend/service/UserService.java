package cringe.backend.service;

import cringe.backend.dao.PointDAO;
import cringe.backend.dao.UserDAO;
import cringe.backend.entity.Point;
import cringe.backend.entity.User;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UserService {

    @EJB
    private UserDAO userDAO;

    @EJB
    private PointDAO pointDAO;

    public List<Point> getUserPoints(Long userId) throws IllegalAccessException {
        User user = userDAO.findByName(userId.toString());
        if (user == null) {
            throw new IllegalAccessException("User not found");
        }

        List<Point> points = pointDAO.findAll(userId);
        return points.stream()
                .map(p -> new Point(p.getX(), p.getY(), p.getR(), p.isCondition()))
                .collect(Collectors.toList());
    }

    public void addUserPoint(Long userId, Point point) throws IllegalAccessException {
        User user = userDAO.findByName(userId.toString());
        if (user == null) {
            throw new IllegalAccessException("User not found");
        }


        pointDAO.save(user.getUsername(), point);
    }

    public void deleteUserPoints(Long userId) {
//        User user = userDAO.findByName(userId.toString());

        pointDAO.deleteAll(userId);
    }
}
