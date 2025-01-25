package cringe.back.service.impls;

import cringe.back.dao.PointDAO;
import cringe.back.entity.Point;
import cringe.back.service.Service;
import cringe.back.service.ServiceName;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class AddPointService extends Service<Point> {

    @EJB
    private PointDAO pointDAO;

    public AddPointService() {
        super(ServiceName.ADD_POINT);
    }

    @Override
    public String execute(Point point) {
        pointDAO.save(point);
        return "Success";
    }
}
