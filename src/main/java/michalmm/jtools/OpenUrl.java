package michalmm.jtools;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.net.URI;

public class OpenUrl {

    void main (String[] args) {
        if (args.length  > 0 && args[0].equals("--help")) {
            IO.println("Program will open default web browser and open a file either passed");
            IO.println("as a parameter or content from the clipboard");
            IO.println("");
            IO.println("Usage: java OpenUrl <URL>  OR");
            IO.println("Usage: java OpenUrl  --> without params, program will use the content");
            IO.println("from the clipboard");
            System.exit(1);
        }

        var url = getUrl(args);
        openDefaultWebBrowser(url);
    }

    void openDefaultWebBrowser (String url) {
        IO.println("Opening default web browser... " + url);
        try {
            Desktop.getDesktop().browse(URI.create(url));
        } catch (IOException e) {
            IO.println("Failed to open default web browser: " + e.getMessage());
        }
    }

    String getUrl(String[] args) {
        return switch(args.length) {
            case 0 -> clipboardContent();
            default -> args[0];
        };
    }

    String clipboardContent() {
        try {
            var clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            var avail = Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .isDataFlavorAvailable(DataFlavor.stringFlavor);
            IO.println("Available data flavors: " + avail);
            return (String) clipboard.getData(DataFlavor.stringFlavor);
        } catch (Exception e) {
            IO.println("Failed to read clipboard: " + e.getMessage());
            return "";
        }
    }
}
