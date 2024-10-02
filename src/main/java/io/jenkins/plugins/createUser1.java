package io.jenkins.plugins;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.model.User;
import jenkins.security.SecurityListener;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

@Extension
public class createUser1 extends SecurityListener {

    private static final Logger LOGGER = Logger.getLogger(createUser1.class.getName());

    // Specify the path to store the logs
    private static final String LOCAL_LOG_FILE_PATH = "C:\\Users\\Ammar\\Desktop\\file-reader\\logs.txt";  // Change this to your desired file path

    // Write to file method
    private void writeToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOCAL_LOG_FILE_PATH, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            LOGGER.warning("Failed to write to local log file: " + e.getMessage());
        }
    }

    @Override
    protected void loggedIn(String username) {
        String logMessage = "User LoggedIn: " + username;
        LOGGER.info(logMessage);
        writeToFile(logMessage);
    }

    @Override
    protected void failedToAuthenticate(String username) {
        String logMessage = "Failed authentication attempt: " + username;
        LOGGER.warning(logMessage);
        writeToFile(logMessage);
    }

    @Override
    protected void loggedOut(String username) {
        String logMessage = "User logged out: " + username;
        LOGGER.info(logMessage);
        writeToFile(logMessage);
    }

    User user;
    @Override
    protected void userCreated(@NonNull String user1) {
        String logMessage = "New user created: " + user1 + " " + (user != null ? user.getFullName() : "");
        LOGGER.info(logMessage);
        writeToFile(logMessage);
    }
}
