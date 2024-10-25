package server.io;


import com.fastcgi.FCGIInterface;
import com.fastcgi.FCGIOutputStream;
import server.util.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class Sender implements ISender {

    private final Logger logger;

    public Sender(Logger log) {
        this.logger = log;
    }

    @Override
    public void sendData(String response) {
        try {
            FCGIOutputStream out = FCGIInterface.request.outStream;
            logger.log(response);
            out.write(response.getBytes(StandardCharsets.UTF_8));
            out.flush();

        } catch (IOException e) {
            logger.log("Houston we have problems! \n" + e.getMessage());
        }
    }
}
