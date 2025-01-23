package cringe.lab4.bean;

import cringe.lab4.db.DBManager;
import cringe.lab4.entity.Point;

import cringe.lab4.service.ServiceManager;
import cringe.lab4.service.ServicesName;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;

import java.io.Serializable;
import java.util.List;

@Remote
@Stateless(mappedName = "controller")
public class PointController implements Serializable {

    private CoordinateHandler coordinateHandler;
    private CheckBoxChecker checkBoxChecker;
    private transient ServiceManager serviceManager;
    private DBManager dbManager;

    public PointController() {}

    public void init() {
        this.serviceManager = new ServiceManager();
        this.dbManager = new DBManager();
        serviceManager.registerObserver(dbManager);
    }
    public void save() {
        Float[] selectedCheckBoxes = checkBoxChecker.getSelectedCheckBoxes();
        List<Point> points = coordinateHandler.createPoints(selectedCheckBoxes);

        serviceManager.execute(ServicesName.AREA_CHECKER, points);
        serviceManager.execute(ServicesName.SAVE, points);
    }

    public void delete() {
        serviceManager.execute(ServicesName.DELETE, dbManager.getPoints());
    }

    public List<Point> getPoints() {
        return dbManager.getPoints();
    }

    public void destroy() {
        serviceManager.unregisterObserver(dbManager);
        dbManager.close();
    }
}
