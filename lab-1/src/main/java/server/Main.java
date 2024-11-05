package server;

import com.fastcgi.FCGIInterface;
import server.data.Data;
import server.io.*;
import server.util.Logger;
import server.util.Parser;

public class Main {

    private static final FCGIInterface fcgiInterface = new FCGIInterface();
    private static final Logger logger = new Logger();
    private static final IReceiver receiver = new Receiver(logger);
    private static final ISender sender = new Sender(logger);
    private static final Parser parse = new Parser(logger);
    private static final ResponseMaker maker = new ResponseMaker(logger);

    public static void main(String[] args) {
        while (fcgiInterface.FCGIaccept() >= 0) {
            String method = FCGIInterface.request.params.getProperty("REQUEST_METHOD");
            logger.log("method: " + method);
            if (method.equals("POST")) {
                try {
                    String msg = receiver.receive();

                    Data data = parse.parseData(msg);
                    maker.createResponse(data);
                    String response = maker.getResponse();

                    sender.sendData(response);
                    logger.log("---------------------------------------");
                } catch (Exception e) {
                    logger.logError("we have terrible problem.... \n " + e.getMessage() + "---------------------------------------");
                }
            } else {
                maker.someResponse(HttpMessage.STRANGE_REQUEST.getTemplate());
                String response = maker.getResponse();
                sender.sendData(response);
                logger.logError("we have some strange HTTP method which my server not supported");
            }
        }
    }
}
