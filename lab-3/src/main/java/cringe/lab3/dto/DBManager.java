package cringe.lab3.dto;

import cringe.lab3.bean.Point;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.List;

@ApplicationScoped
public class DBManager implements Serializable, Observer{

    @Override
    public void save(List<Point> points) {

    }

    @Override
    public void delete() {

    }

}
