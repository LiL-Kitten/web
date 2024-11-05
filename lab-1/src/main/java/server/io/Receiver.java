package server.io;

import com.fastcgi.FCGIInterface;
import server.util.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Receiver implements IReceiver {

    private final Logger logger;

    public Receiver(Logger logger) {
        this.logger = logger;
    }

    @Override
    public String receive() {
        return handleRequest();
    }

    public String handleRequest() {
        try {
            FCGIInterface.request.inStream.fill();
            var contentLength = FCGIInterface.request.inStream.available();
            var buffer = ByteBuffer.allocate(contentLength);
            var readBytes = FCGIInterface.request.inStream.read(buffer.array(), 0, contentLength);
            var requestBodyRaw = new byte[readBytes];
            buffer.get(requestBodyRaw);
            buffer.clear();

            String data = new String(requestBodyRaw, StandardCharsets.UTF_8);
            logger.log(data);
            return data;
        } catch (IOException e) {
            logger.logError(e.getMessage());
            return null;
        }
    }
}
