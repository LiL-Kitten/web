package server.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Logger {

    private String filePath;

    public Logger(String filePath) {
        this.filePath = filePath;
    }

    public void log(String message) {
        log("|INFO| ", message);
    }

    public void logError(String message) {
        log("|ERROR| ", message);
    }

    private void log(String level, String message) {
        String logMessage = String.format("%s [%s]%n", level, message);

        String filePath = "server.log";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(logMessage);
        } catch (IOException e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }
}
