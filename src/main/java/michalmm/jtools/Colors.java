package michalmm.jtools;

public class Colors {

    public enum ColorEnum {
        SOFT_GRAY("\033[38;5;246m"),      // Soft gray
        WARM_YELLOW("\033[38;5;220m"),        // Warm yellow
        BRIGHT_BLUE("\033[38;5;33m"),     // Bright blue
        BRIGHT_WHITE("\033[38;5;255m"),      // Bright white
        BRIGHT_RED("\033[38;5;196m"),         // Bright red
        BRIGHT_GREEN("\033[38;5;46m"),        // Bright green
        BRIGHT_YELLOW("\033[38;5;226m"),      // Bright yellow
        SKY_BLUE("\033[38;5;39m"),         // Sky blue
        SOFT_PURPLE("\033[38;5;129m"),      // Soft purple
        BRIGHT_CYAN("\033[38;5;51m"),         // Bright cyan
        BLACK_ON_WHITE("\033[38;5;232;48;5;255m");  // Black text on white background

        final String code;

        ColorEnum(String code) {
            this.code = code;
        }
    }

    private final static String RESET = "\u001B[0m";

    public static String format(ColorEnum colorEnum, String msg) {
        return String.format(colorEnum.code + "%s" + RESET, msg);
    }

    static void main() {
        IO.println(format(ColorEnum.BRIGHT_GREEN, "Hello wolrd!"));
    }
}
