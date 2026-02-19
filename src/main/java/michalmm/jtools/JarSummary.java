package michalmm.jtools;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarSummary {

    void main(String[] args){
        if (args.length != 1) {
            System.err.println("Usage: java JarSummary <path-to-jar-file>");
            System.exit(1);
        }

        summarizeTheJar(args[0]);
    }

    public static void summarizeTheJar(String jarPath){
        try (JarFile jarFile = new JarFile(jarPath)){
            jarFile.stream()
                    .filter(jarEntry -> jarEntry.getSize() > 0)
                    .forEach(jarEntry ->
                            IO.println(jarEntry.getName() + " size: " +
                                    Colors.format(Colors.ColorEnum.BRIGHT_RED, jarEntry.getSize()) +
                                    " compressed size:[" +
                                    Colors.format(Colors.ColorEnum.BRIGHT_YELLOW,jarEntry.getCompressedSize())
                                    + "]"));

        } catch (IOException e) {
            IO.println("Error: could not open jar file: " + jarPath);
            throw new RuntimeException(e);
        }
    }
}
