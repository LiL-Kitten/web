package server;

import java.io.IOException;

public interface DataReceiver {
    Data receiveData() throws IOException;
}
