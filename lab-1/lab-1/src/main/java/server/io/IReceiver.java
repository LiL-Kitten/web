package server.io;

import java.io.IOException;

public interface IReceiver {
    String receive() throws IOException;
}
