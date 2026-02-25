package michalmm.jtools;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTime {

    private static final DateTimeFormatter simpleFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter oneWord = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");

    void main(String[] args) {
        if (args.length == 0) {
            currentDateTime(simpleFormatter);
        } else if (args.length == 1 && args[0].equals("--one-word")) {
            currentDateTime(oneWord);
        } else if (args.length == 2) {
            var d1 = LocalDateTime.parse(args[0], oneWord);
            var d2 = LocalDateTime.parse(args[1], oneWord);
            difference(d1, d2);
        } else {
            help();
        }
    }

    private static void difference(LocalDateTime date1, LocalDateTime date2) {
        var difference = Duration.between(date1, date2).abs();
        IO.println(String.format("%02d:%02d:%02d",
                difference.toHours(),
                difference.toMinutesPart(),
                difference.toSecondsPart()));
    }

    private static void help() {
        IO.println("Usage: java DateAndTime --one-word | date1 date2");
        IO.println("where date is in format yyyy-MM-dd_HH:mm:ss");
        IO.println("example: 2026-02-14_09:34:24");
    }

    private static void currentDateTime(DateTimeFormatter  formatter) {
        var now = LocalDateTime.now();
        IO.println(formatter.format(now));
    }
}
