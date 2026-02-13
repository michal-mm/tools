package michalmm.jtools;

import java.io.File;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Duplicates {
    
    void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Duplicates <env_variable>");
            System.exit(1);
        }

        var varName = args[0];
        var envWithDuplicates = System.getenv(varName);

        if (envWithDuplicates == null || envWithDuplicates.isEmpty()) {
            System.err.println("Env variable empty or incorrect name");
            System.err.println("Make sure to pass just the variable name without % or $ chars");
            System.exit(1);
        }

        var uniqueEnvValue = Stream.of(envWithDuplicates.split(File.pathSeparator))
            .filter(path -> !path.isEmpty())
            .map(Path::of)
            .distinct()
            .map(Path::toString)
            .collect(Collectors.joining(File.pathSeparator));

            IO.println();
            IO.println(uniqueEnvValue);
            IO.println();
    }
}
