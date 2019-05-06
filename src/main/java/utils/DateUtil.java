package utils;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date pase(String str) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        try {
            return dateFormat.parse(str);
        }catch (ParseException e){
            e.printStackTrace();
        }

        return new Date();

    }

    public static void main(String[] args){
        Date d=pase("20190101");
        System.out.println(d);

        String dateTime = new DateTime(d).toString("yyyy-MM-dd");
        System.out.println(dateTime);

    }
}
