package cringe.lab4.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CheckBoxChecker implements Serializable {

    private final Map<Float, Boolean> checkBoxes = new HashMap<>();

    public Map<Float, Boolean> getCheckBoxes() {
        return checkBoxes;
    }

    public Float[] getValues() {
        return checkBoxes.keySet().toArray(new Float[0]);
    }

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
