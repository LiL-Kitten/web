package cringe.backend.service.commands;

import cringe.backend.entity.Point;
import cringe.backend.service.Service;
import cringe.backend.service.ServicesName;

import java.util.List;

public class SavePoints extends Service {

    public SavePoints() {
        super(ServicesName.SAVE);
    }

    @Override
    public void action(List<Point> points) {
        notifyToSave(points);
    }

}
