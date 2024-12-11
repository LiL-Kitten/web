package cringe.lab3.util;

import cringe.lab3.bean.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Parser {

    private static final String REGEX = "([xyr])=([^&]*)";

    public Data parse(String txt) throws ParsingException {
        Map<String, Double> map = new HashMap<>();
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(txt);

        try {
            while (matcher.find()) {
                String key = matcher.group(1);
                Double value = Double.parseDouble(matcher.group(2));
                map.put(key, value);
            }

            double x = map.get("x");
            double y = map.get("y");
            double r = map.get("r");

            return new Data(x, y, r);
        } catch (NumberFormatException e) {
            throw new ParsingException("Ошибка во время парсинга строки: неверный формат числа.", e);
        } catch (Exception e) {
            throw new ParsingException("Неизвестная ошибка при парсинге строки.", e);
        }
    }
}

