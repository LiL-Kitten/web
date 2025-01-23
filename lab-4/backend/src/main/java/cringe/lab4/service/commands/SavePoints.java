package cringe.lab4.service.commands;

import cringe.lab4.entity.Point;
import cringe.lab4.service.Service;
import cringe.lab4.service.ServicesName;

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
