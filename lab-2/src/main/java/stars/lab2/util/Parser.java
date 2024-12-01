package stars.lab2.util;

import stars.lab2.bean.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Parser {

    private static final String REGEX = "([xyr])=([^&]*)";

    public Data parse(String txt) throws ParsingException {
        Map<String, Float> map = new HashMap<>();
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(txt);

        try {
            while (matcher.find()) {
                String key = matcher.group(1);
                Float value = Float.parseFloat(matcher.group(2));
                map.put(key, value);
            }

            Float x = map.get("x");
            Float y = map.get("y");
            Float r = map.get("r");

            if (x != null && y != null && r != null) {

                return new Data(x, y, r);
            } else {
                throw new ParsingException("все бэд(( не парсится, де  косяк! надо продумать " +
                        "как ошибки отправлять");
            }
        } catch (NumberFormatException e) {
            throw new ParsingException("ошибка во время парсинга строки");
        } catch (Exception e) {
            throw new ParsingException("неизвестная ошибка", e);
        }
    }
}

