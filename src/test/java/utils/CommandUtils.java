package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Predicate;

public class CommandUtils {
    public static String execute(String[] commands, Predicate<String> condition) throws IOException {
        ProcessBuilder pb = new ProcessBuilder();
        Process p = pb.command(commands).start();
        InputStream is = p.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while((line = br.readLine()) != null) {
            if (condition.test(line)) {
                return line;
            }
        }
        return null;
    }
}
