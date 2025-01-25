package cringe.back.service.impls;

import cringe.back.dao.PointDAO;
import cringe.back.entity.User;
import cringe.back.service.Service;
import cringe.back.service.ServiceName;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class DelPointsService extends Service<User> {

    @EJB
    PointDAO pointDAO;

    public DelPointsService() {
        super(ServiceName.DELETE_POINTS);
    }

    @Override
    public String execute(User argument) {
        pointDAO.deleteAll(argument.getId());
        return "Ваши точки удалены!";
    }
}
