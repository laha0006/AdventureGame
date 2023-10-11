package ui;

public class Color {
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;105m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;255m";   // WHITE
    public static final String ORANGE = "\033[38;5;208m"; // ORANGE
    public static final String PURPLE_EPIC = "\033[38;5;163m"; // WOW PURPLE
    public static final String GREEN_UNCOMMON = "\033[38;5;30m"; // WOW GREEN
    public static final String BLUE_RARE = "\033[38;5;63m"; // WoW Rare

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN


    public static String red(String string) {
        return RED + string + RESET;
    }
    public static String boldRed(String string) {
        return RED_BOLD + string + RESET;
    }


    public static String brightRed(String string) {
        return RED_BRIGHT + string + RESET;
    }

    public static String bgBrightGreen(String string) {
        return GREEN_BACKGROUND_BRIGHT + string + RESET;
    }


}
