package date;


import org.joda.time.DateTime;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class DateUtil {

    /**
     * 获取两个日期间隔的所有日期
     * @param start 格式必须为'2018-01-25'
     * @param end 格式必须为'2018-01-25'
     * @return
     */

    public static List<String> getBetweenDate(String start, String end) {
        List<String> list = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(new DateTime(start).toString("yyyy-MM-dd"));
        LocalDate endDate = LocalDate.parse(new DateTime(end).toString("yyyy-MM-dd"));

        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }
        Stream.iterate(startDate, d -> d.plusDays(1))
                .limit(distance + 1)
                .forEach(d -> list.add(d.toString()));
        return list;
    }


    public static String FormatDate(String date) {
        return new DateTime(date).toString("yyyy-MM-dd");
    }

    public static String convertWeektoDate(String dateStr){
        String[] dates = dateStr.split("\\.");
        int year = Integer.parseInt(dates[0]);
        int week = Integer.parseInt(dates[1]);

        Calendar cld = Calendar.getInstance();
        cld.set(Calendar.YEAR, year);
        cld.set(Calendar.WEEK_OF_YEAR, week);

        Date date = cld.getTime();
        DateTime formattedDate  = new DateTime(date).minusDays(2);
        String date1 = formattedDate.toString("yyyy-MM-dd");
        String date2 = formattedDate.plusDays(6).toString("yyyy-MM-dd");

        return date1 + "~" + date2;
    }
    public static void main(String[] args) {

//        getBetweenDate("2018-01-25", "2018-01-29").forEach(System.out::println);
        String res =  convertWeektoDate("2019.48");
        System.out.println(res);
    }

}
