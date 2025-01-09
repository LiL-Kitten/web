package cringe.lab3.controller;

import cringe.lab3.bean.CollectionBean;
import cringe.lab3.bean.Point;
import cringe.lab3.bean.coordinate.CheckBoxChecker;
import cringe.lab3.bean.coordinate.CoordinateHandler;

import cringe.lab3.dto.DBManager;

import cringe.lab3.service.ServiceManager;
import cringe.lab3.service.ServicesName;

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

    private final CollectionBean collectionBean;
    private final ServiceManager serviceManager;
    private final DBManager dbManager;

    @Inject
    public PointController() {
        this.collectionBean = new CollectionBean();
        this.dbManager = new DBManager();
        this.serviceManager = new ServiceManager(collectionBean);
        collectionBean.attach(dbManager);
    }

    public void save() {


        Float[] selectedCheckBoxes = checkBoxChecker.getSelectedCheckBoxes();
        List<Point> points = coordinateHandler.createPoints(selectedCheckBoxes);

        serviceManager.execute(ServicesName.AREA_CHECKER, points);
        serviceManager.execute(ServicesName.SAVE, points);
    }

    public void delete() {
        serviceManager.execute(ServicesName.DELETE, collectionBean.getPoints());
    }

    public List<Point> getPoints() {
        return collectionBean.getPoints();
    }

    @PreDestroy
    public void destroy() {
        collectionBean.detach(dbManager);
    }
}
