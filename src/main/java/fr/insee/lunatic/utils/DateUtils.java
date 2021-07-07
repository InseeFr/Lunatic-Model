package fr.insee.lunatic.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static String getCurrentDate(){
        LocalDateTime now = LocalDateTime.now();
        return dateTimeFormatter.format(now);
    }
}
