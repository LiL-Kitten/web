package cringe.back.service.impls;

import cringe.back.dao.PointDAO;
import cringe.back.entity.Point;
import cringe.back.service.Service;
import cringe.back.service.ServiceName;
import jakarta.ejb.EJB;

public class AreaCheckerService extends Service<Point> {

    @EJB
    PointDAO pointDAO;

    public AreaCheckerService() {
        super(ServiceName.AREA_CHECKER);
    }

    @Override
    public String execute(Point argument) {
        return String.valueOf(pointDAO.checkArea(argument.getX(), argument.getY(), argument.getR()));
    }
}
