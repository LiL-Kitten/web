package cringe.lab3.service.commands;

import cringe.lab3.bean.Point;
import cringe.lab3.service.Service;
import cringe.lab3.service.ServicesName;

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
