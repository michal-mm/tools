package michalmm.jtools;

import javax.swing.*;
import java.awt.*;

public class Warning {

    void main (String[] args) {
        String warningMessage = """
                This is ground control to major Tom
                Can you hear me, major Tom?!? 
                """;

        showWarnMessage(warningMessage);
    }

    private static void showWarnMessage (String message) {
        SwingUtilities.invokeLater(() -> {
            GraphicsConfiguration gc = getActiveScreenConfig();

            JFrame frame = new JFrame(gc);  // bind frame to that screen
            frame.setAlwaysOnTop(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setUndecorated(true);

            // Center on that specific screen
            Rectangle screenBounds = gc.getBounds();
            frame.setLocation(
                    screenBounds.x + screenBounds.width / 2,
                    screenBounds.y + screenBounds.height / 2
            );
            frame.setAlwaysOnTop(true);
            frame.setVisible(true);

            JOptionPane.showMessageDialog(
                    frame,
                    message,
                    "Tunnel Broken",
                    JOptionPane.ERROR_MESSAGE
            );

            frame.dispose();
        });
    }

    private static GraphicsConfiguration getActiveScreenConfig() {
        // Try to find the screen that has the currently focused window
        Window focusedWindow = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
        if (focusedWindow != null) {
            return focusedWindow.getGraphicsConfiguration();
        }

        // Fallback: find screen where the mouse cursor currently is
        Point mousePos = MouseInfo.getPointerInfo().getLocation();
        for (GraphicsDevice device : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
            Rectangle bounds = device.getDefaultConfiguration().getBounds();
            if (bounds.contains(mousePos)) {
                return device.getDefaultConfiguration();
            }
        }

        // Last resort: primary screen
        return GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();
    }
}
