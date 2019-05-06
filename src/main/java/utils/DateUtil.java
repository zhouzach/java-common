package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static LocalDate parse(String str) {
        return LocalDate.parse(str, formatter);
    }

    public static LocalDateTime parse4DateTime(String str) {
        return LocalDate.parse(str, formatter).atStartOfDay();
    }

    public static void main(String[] args){
        LocalDate date = parse("20190101");
        LocalDateTime dateTime = parse4DateTime("20190101");
        System.out.println(date);
        System.out.println(dateTime);
    }
}
