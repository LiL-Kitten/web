package cringe.backend.bean;

import cringe.backend.db.DBManager;
import cringe.backend.entity.Point;

import cringe.backend.service.ServiceManager;
import cringe.backend.service.ServicesName;

import java.io.Serializable;
import java.util.List;


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
