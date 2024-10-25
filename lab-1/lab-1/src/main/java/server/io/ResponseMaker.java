package server.io;

import server.data.Data;
import server.util.Logger;

import java.nio.charset.StandardCharsets;

public class ResponseMaker {

    private String response;
    private final Logger logger;

    public ResponseMaker(Logger logger) {
        this.logger = logger;
    }

    public String getResponse() {
        logger.log("check response: \n" + response);
        return response;
    }

    public void createResponse(Data data) {
        logger.log("start creating response \n example data: \n" + data);
        if (data == null) {
            response = String.format(HttpMessage.ERROR.getTemplate());
            logger.log("we make error response");
        } else {
            response = String.format(HttpMessage.RESULT_JSON.getTemplate(),
                    data.getX(),
                    data.getY(),
                    data.getR(),
                    data.getTime(),
                    data.getDate(),
                    data.isCondition());
            logger.log("we make good response");
        }

        this.response = String.format(HttpMessage.HTTP_RESPONSE.getTemplate(),
                response.getBytes(StandardCharsets.UTF_8).length,
                response);
            logger.log("final response");
    }

    public void someResponse(String response) {
        this.response = String.format(HttpMessage.HTTP_RESPONSE.getTemplate(),
                response.getBytes(StandardCharsets.UTF_8).length,
                response);
    }
}
