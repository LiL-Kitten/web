package server;


import com.fastcgi.FCGIInterface;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class Sender implements DataSender {

    private Logger log;

    public Sender(Logger log){
        this.log = log;
    }

    @Override
    public void sendData(Data data) {
        var json = String.format(HttpMessage.RESULT_JSON.getTemplate(),
                data.getX(),
                data.getY(),
                data.getR(),
                data.getTime(),
                data.getDate(),
                data.isCondition());

        var response = String.format(HttpMessage.HTTP_RESPONSE.getTemplate(),
                json.getBytes(StandardCharsets.UTF_8).length,
                json);

        try {
            FCGIInterface.request.outStream.write(response.getBytes(StandardCharsets.UTF_8));
            FCGIInterface.request.outStream.flush();
        } catch (IOException e) {
            log.log("Houston we have problems! \n" + e.getMessage());
        }
    }
}
