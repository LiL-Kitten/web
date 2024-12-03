package stars.lab2.util;

import stars.lab2.bean.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Parser {

    private static final String REGEX = "([xyr])=([^&]*)";

    public Data parse(String txt) throws ParsingException {
        Map<String, BigDecimal> map = new HashMap<>();
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(txt);

        try {
            while (matcher.find()) {
                String key = matcher.group(1);
                BigDecimal value = new BigDecimal(matcher.group(2));
                map.put(key, value);
            }

            BigDecimal x = map.get("x");
            BigDecimal y = map.get("y");
            BigDecimal r = map.get("r");

            if (x != null && y != null && r != null) {
                return new Data(x, y, r);
            } else {
                throw new ParsingException("Ошибка: не все необходимые параметры были найдены в строке.");
            }
        } catch (NumberFormatException e) {
            throw new ParsingException("Ошибка во время парсинга строки: неверный формат числа.", e);
        } catch (Exception e) {
            throw new ParsingException("Неизвестная ошибка при парсинге строки.", e);
        }
    }
}

