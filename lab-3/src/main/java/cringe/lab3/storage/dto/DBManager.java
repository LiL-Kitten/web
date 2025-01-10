package cringe.lab3.storage.dto;

import cringe.lab3.bean.Point;
import cringe.lab3.storage.Observer;

import java.io.Serializable;
import java.util.List;

public class DBManager implements Serializable, Observer {

    @Override
    public void save(List<Point> points) {

    }

    @Override
    public void delete() {

    }

}
