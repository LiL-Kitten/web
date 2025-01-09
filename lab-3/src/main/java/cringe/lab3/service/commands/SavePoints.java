package cringe.lab3.service.commands;

import cringe.lab3.bean.CollectionBean;
import cringe.lab3.bean.Point;
import cringe.lab3.service.Service;
import cringe.lab3.service.ServicesName;

import java.util.List;

public class SavePoints extends Service {

    private final CollectionBean collectionBean;

    public SavePoints(CollectionBean collectionBean) {
        super(ServicesName.SAVE);
        this.collectionBean = collectionBean;
    }

    @Override
    public void action(List<Point> points) {
        collectionBean.save(points);
    }

}
