package server;

import com.fastcgi.FCGIInterface;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Receiver implements DataReceiver {

    private Logger log;

    public Receiver(Logger log) {
        this.log = log;
    }

    @Override
    public Data receiveData() {
        try {
            FCGIInterface.request.inStream.fill();
            var contentLength = FCGIInterface.request.inStream.available();
            var buffer = ByteBuffer.allocate(contentLength);
            var readBytes = FCGIInterface.request.inStream.read(buffer.array(), 0, contentLength);
            var requestBodyRaw = new byte[readBytes];
            buffer.get(requestBodyRaw);
            buffer.clear();

            var request = new String(requestBodyRaw, StandardCharsets.UTF_8);
            var elements = request.split("&");

            if (elements.length < 3) {
                throw new IllegalArgumentException("Not enough parameters in the request");
            }

            return new Data(
                    Float.parseFloat(elements[0].split("=")[1]),
                    Float.parseFloat(elements[1].split("=")[1]),
                    Float.parseFloat(elements[2].split("=")[1])
            );

        } catch (IOException e) {
            log.logError("I hate this shit " + e.getMessage());
        } catch (NumberFormatException e) {
            log.logError("maybe i invalid... \n" + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            log.logError("you peace of shit >.< \n " + e.getMessage());
        } catch (Exception e) {
            log.logError("Houston  we have problem \n " + e.getMessage());
        }
        return new Data(0, 0, 0);
    }
}
