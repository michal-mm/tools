package michalmm.jtools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

public class RenameFile {

    void help(int exitCode) {
        IO.println("Usage: java RenameFile <filename> --prefix=a_prefix --suffix=a_suffix");
        IO.println("");
        IO.println("At least one parameter is required, either prefix or suffix");
        IO.println("It will create a copy of a file in its folder");
        IO.println("with prefix at the beginning and suffix at the end");
        IO.println("");
        IO.println("Example java RenameFile /foo/bar.txt --prefix=pre_ --suffix=_suff");
        IO.println("will create /foo/pre_bar.txt_suff as a copy of /foo/bar.txt");
        System.exit(exitCode);
    }

    void main(String[] args) {
        if (args.length < 2) {
            help(1);
        }

        var filePath = args[0];
        var prefix = Stream.of(args)
                .filter(s -> s.startsWith("--prefix="))
                .findFirst()
                .orElse("--prefix=")
                .substring("--prefix=".length());
        var suffix = Stream.of(args)
                .filter(s -> s.startsWith("--suffix="))
                .findFirst()
                .orElse("--suffix=")
                .substring("--suffix=".length());

        if (!existsAndIsFile(filePath)) {
            IO.println("File not found: " + filePath + "\n");
            help(2);
        }

        if (prefix.isEmpty() && suffix.isEmpty()) {
            help(1);
        }

        createCopy(filePath, prefix, suffix);
    }

    private void createCopy(String filePath, String prefix, String suffix) {
        var path = Paths.get(filePath);
        var oldFileName = path.getFileName().toString();
        var newFileName = prefix + oldFileName + suffix;
        var dir = path.getParent();

        try {
            Files.copy(path, dir.resolve(newFileName), StandardCopyOption.COPY_ATTRIBUTES);
            IO.println("Successfully created: " + dir.resolve(newFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean existsAndIsFile(String filePath) {
        var path = Paths.get(filePath);
        return Files.exists(path) && Files.isRegularFile(path);
    }
}
