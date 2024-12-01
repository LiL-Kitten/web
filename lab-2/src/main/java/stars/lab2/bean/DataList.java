package stars.lab2.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "list")
@SessionScoped
public class DataList implements Serializable {

    @Serial
    private static final long serialVersionUID = 1351351341461314151L;

    private final List<Data> list = new ArrayList<>();

    public DataList() {}

    public List<Data> getList() {
        return list;
    }

    public void addData(Data data) {
        list.add(data);
    }
}
