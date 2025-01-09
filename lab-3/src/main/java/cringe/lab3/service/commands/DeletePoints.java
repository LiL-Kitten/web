package cringe.lab3.service.commands;

import cringe.lab3.bean.CollectionBean;
import cringe.lab3.bean.Point;
import cringe.lab3.service.Service;
import cringe.lab3.service.ServicesName;

import java.util.List;

public class DeletePoints extends Service {

    private final CollectionBean collectionBean;

    public DeletePoints(CollectionBean collectionBean) {
        super(ServicesName.DELETE);
        this.collectionBean = collectionBean;
    }

    @Override
    public void action(List<Point> points) {
        collectionBean.delete();
    }
}
