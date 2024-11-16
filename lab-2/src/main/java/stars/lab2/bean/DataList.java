package stars.lab2.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("data")
@SessionScoped
public class DataList implements Serializable {
    private final List<Data> list = new ArrayList<>();

    public DataList() {}

    public List<Data> getList() {
        return list;
    }

    public void addData(Data data) {
        list.add(data);
    }
}
