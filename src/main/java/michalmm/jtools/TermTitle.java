package michalmm.jtools;

public class TermTitle {

    static void main(String[] args) {
        var title = String.join(" ", args);
        setTermTitle(title);
    }

    private static void setTermTitle(String title) {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            try {
                // Windows: use cmd's TITLE command
                new ProcessBuilder("cmd", "/c", "title", title)
                        .inheritIO()
                        .start()
                        .waitFor();
            } catch (Exception e) {
                // fallback to ANSI escape (works in Windows Terminal)
                System.out.print("\033]0;" + title + "\007");
                System.out.flush();
            }
        } else {
            // macOS/Linux: ANSI escape sequence
            System.out.print("\033]0;" + title + "\007");
            System.out.flush();
        }
    }
}
