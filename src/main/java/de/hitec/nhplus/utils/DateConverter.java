package de.hitec.nhplus.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for converting between <code>String</code> representations and
 * Java time objects like <code>LocalDate</code> and <code>LocalTime</code>.
 * <p>
 * This class standardizes date and time formatting throughout the application.
 */
public class DateConverter {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm";

    /**
     * Converts a <code>String</code> to a <code>LocalDate</code> using the format "yyyy-MM-dd".
     *
     * @param date The date string to convert.
     * @return A <code>LocalDate</code> representing the parsed date.
     */
    public static LocalDate convertStringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Converts a <code>String</code> to a <code>LocalTime</code> using the format "HH:mm".
     *
     * @param time The time string to convert.
     * @return A <code>LocalTime</code> representing the parsed time.
     */
    public static LocalTime convertStringToLocalTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    /**
     * Converts a <code>LocalDate</code> to a <code>String</code> using the format "yyyy-MM-dd".
     *
     * @param date The <code>LocalDate</code> to convert.
     * @return A string representation of the date.
     */
    public static String convertLocalDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Converts a <code>LocalTime</code> to a <code>String</code> using the format "HH:mm".
     *
     * @param time The <code>LocalTime</code> to convert.
     * @return A string representation of the time.
     */
    public static String convertLocalTimeToString(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }
}
