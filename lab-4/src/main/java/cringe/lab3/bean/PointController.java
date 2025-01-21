package cringe.lab3.bean;

import cringe.lab3.db.DBManager;
import cringe.lab3.entity.Point;

import cringe.lab3.service.ServiceManager;
import cringe.lab3.service.ServicesName;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named(value = "controller")
@SessionScoped
public class PointController implements Serializable {

    @Inject
    private CoordinateHandler coordinateHandler;

    @Inject
    private CheckBoxChecker checkBoxChecker;

    private transient ServiceManager serviceManager;
    private DBManager dbManager;

    @Inject
    public PointController() {}

    @PostConstruct
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

    @PreDestroy
    public void destroy() {
        serviceManager.unregisterObserver(dbManager);
        dbManager.close();
    }
}
