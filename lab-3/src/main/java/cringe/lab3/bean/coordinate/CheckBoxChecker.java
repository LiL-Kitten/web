package cringe.lab3.bean.coordinate;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SessionScoped
@Named(value = "checkbox")
public class CheckBoxChecker implements Serializable {

    private final Map<Float, Boolean> checkBoxes = new HashMap<>();

    public CheckBoxChecker() {
    }

    public Map<Float, Boolean> getCheckBoxes() {
        return checkBoxes;
    }

    public Float[] getValues() {
        return checkBoxes.keySet().toArray(new Float[0]);
    }

    @PostConstruct
    public void init() {
        initCheckBoxes();
    }

    public void initCheckBoxes() {
        this.checkBoxes.putAll(
                Stream.iterate(-4.0f, n -> n + 1.0f)
                        .limit(9)
                        .collect(Collectors.toMap(n -> n, n -> false))
        );
    }

    public void selectCheckBox(float value) {
        this.checkBoxes.put(value, true);
    }

    public Float[] getSelectedCheckBoxes() {
        return checkBoxes.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .toArray(Float[]::new);
    }
}
