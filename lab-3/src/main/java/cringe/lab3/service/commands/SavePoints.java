package cringe.lab3.service.commands;

import cringe.lab3.entity.Point;
import cringe.lab3.service.Service;
import cringe.lab3.service.ServicesName;

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
