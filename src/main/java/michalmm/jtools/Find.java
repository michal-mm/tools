package michalmm.jtools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class Find {
    private static final String YELLOW = "\033[38;5;220m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Find <searchTerm>");
            System.exit(1);
        }

        var searchTerm = args[0];
        var currentPath = Paths.get("");

        try (var paths = Files.walk(currentPath)) {
            paths.filter(path -> matches(path, searchTerm))
                    .map(Path::normalize)
                    .map(Path::toString)
                    .map(pathStr -> highlight(pathStr, searchTerm))
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Error during file search: " + e.getMessage());
        }
    }

    private static boolean matches(Path path, String searchTerm) {
        return path.toString().toLowerCase().contains(searchTerm.toLowerCase());
    }

    private static String highlight(String text, String searchTerm) {
        return text.replaceAll("(?i)(" + Pattern.quote(searchTerm) + ")", 
                               YELLOW + "$1" + RESET);
    }
}
