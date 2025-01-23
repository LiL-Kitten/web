package cringe.lab4.service.commands;

import cringe.lab4.entity.Point;
import cringe.lab4.service.Service;
import cringe.lab4.service.ServicesName;

import java.util.List;

public class DeletePoints extends Service {

    public DeletePoints() {
        super(ServicesName.DELETE);
    }

    @Override
    public void action(List<Point> points) {
        notifyToDelete();
    }
}
