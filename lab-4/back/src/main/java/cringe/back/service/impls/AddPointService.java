package cringe.back.service.impls;

import cringe.back.dao.PointDAO;
import cringe.back.dto.PointDTO;
import cringe.back.service.ServiceName;
import cringe.back.service.ServiceResponse;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class AddPointService extends Service<PointDTO> {

    @EJB
    private PointDAO pointDAO;

    public AddPointService() {
        super(ServiceName.ADD_POINT);
    }

    @Override
    public ServiceResponse<PointDTO> execute(PointDTO point) {
        pointDAO.save(point);
        return new ServiceResponse<>(true, "Point added successfully");
    }
}
