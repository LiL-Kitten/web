package server.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.data.Data;

public class Parser {

    private final ObjectMapper mapper = new ObjectMapper();
    private final Logger logger;

    public Parser(Logger logger) {
        this.logger = logger;
    }

    public Data parseData(String data) {
            logger.log("start parsing");
        try {
            Data txt = mapper.readValue(data, Data.class);
            logger.log("nice parsing man!");
            return  mapper.readValue(data, Data.class);
        } catch (JsonProcessingException e) {
            logger.log("all baaaaad((");
            return null;
        }
    }
}
