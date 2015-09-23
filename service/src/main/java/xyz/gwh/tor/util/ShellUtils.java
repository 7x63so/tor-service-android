package xyz.gwh.tor.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Utility class for shell commands.
 */
public class ShellUtils {

    /**
     * Executes the command in runtime.
     */
    public static int execute(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuffer output = new StringBuffer();
            char[] buffer = new char[4096];
            int read;

            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }

            reader.close();
            process.waitFor();
            return process.exitValue();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Unable to execute '" + command + "'", e);
        }
    }
}
