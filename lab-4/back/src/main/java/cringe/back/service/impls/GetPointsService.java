package cringe.back.service.impls;

import cringe.back.dao.PointDAO;
import cringe.back.entity.Point;
import cringe.back.entity.User;
import cringe.back.service.Service;
import cringe.back.service.ServiceName;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class GetPointsService extends Service<User> {

    @EJB
    PointDAO pointDAO;

    public GetPointsService() {
        super(ServiceName.GET_POINTS);
    }

    @Override
    public String execute(User user) {
        List<Point> points = pointDAO.findAll(user.getId());
        return points.stream()
                .map(p -> new Point(p.getX(), p.getY(), p.getR(), p.isCondition()))
                .toList().toString();
    }
}
