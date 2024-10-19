package server;

import com.fastcgi.FCGIInterface;

import java.io.IOException;

public class Main {

    private static FCGIInterface fcgiInterface = new FCGIInterface();
    private static Logger logger = new Logger("server.log");
    private static DataReceiver receiver = new Receiver(logger);
    private static DataSender sender = new Sender(logger);

    public static void main(String[] args) {
        logger.log("prog start");
        while (fcgiInterface.FCGIaccept() >= 0) {
            String method = FCGIInterface.request.params.getProperty("REQUEST_METHOD");
            logger.log("method: " + method);
            if (method.equals("POST")) {
                try {

                    Data data = receiver.receiveData();
                    logger.log(data.toString());
                    sender.sendData(data);
                    logger.log("prog finished, all OK");
                } catch (Exception e) {
                    logger.logError("wtf?");
                }
            }
        }
    }
}
