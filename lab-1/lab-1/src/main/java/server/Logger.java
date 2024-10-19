package server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Logger {
    private final String logFilePath;

    public Logger(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public void log(String message) {
        log("|INFO| ", message);
    }

    public void logError(String message) {
        log("|ERROR| ", message);
    }

    private void log(String level, String message) {
        String logMessage = String.format("%s [%s]%n", level, message);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            writer.write(logMessage);
        } catch (IOException e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }
}
