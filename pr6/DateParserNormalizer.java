import java.util.*;
import java.util.regex.*;

public class DateParserNormalizer {
    public static void main(String[] args) {
        String text = "Пример текста: Event on 12th Oct 2024, 02.03.2022, 2022/03/02, 12/10/24, 2024-10-12, 30 февраля 2024, 31 Dec 2022, 05.05.2025, 15 August 2023.";

        String[] patterns = {
                "(\\d{1,2})\\.(\\d{1,2})\\.(\\d{4})", // dd.MM.yyyy
                "(\\d{1,2})(?:st|nd|rd|th)?\\s+(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\s+(\\d{4})", // d MMM yyyy
                "(\\d{4})-(\\d{2})-(\\d{2})", // yyyy-MM-dd
                "(\\d{1,2})/(\\d{1,2})/(\\d{2,4})", // dd/MM/yy или dd/MM/yyyy
                "(\\d{4})/(\\d{2})/(\\d{2})", // yyyy/MM/dd
                "(\\d{1,2})\\s+(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\s+(\\d{4})", // d MMM yyyy (без ordinal)
                "(\\d{1,2})\\s+(January|February|March|April|May|June|July|August|September|October|November|December)\\s+(\\d{4})" // d Month yyyy
        };

        Pattern[] regexPatterns = new Pattern[patterns.length];
        for (int i = 0; i < patterns.length; i++) {
            regexPatterns[i] = Pattern.compile(patterns[i], Pattern.CASE_INSENSITIVE);
        }

        Set<String> uniqueDates = new TreeSet<>();

        for (Pattern pattern : regexPatterns) {
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                String isoDate = null;
                if (pattern.pattern().equals(patterns[0])) {
                    // dd.MM.yyyy
                    int day = Integer.parseInt(matcher.group(1));
                    int month = Integer.parseInt(matcher.group(2));
                    int year = Integer.parseInt(matcher.group(3));
                    if (isValidDate(day, month, year)) {
                        isoDate = String.format("%04d-%02d-%02d", year, month, day);
                    }
                } else if (pattern.pattern().equals(patterns[1]) || pattern.pattern().equals(patterns[6])) {
                    // d MMM yyyy или d Month yyyy
                    int day = Integer.parseInt(matcher.group(1));
                    String monthStr = matcher.group(2).toLowerCase();
                    int year = Integer.parseInt(matcher.group(3));
                    int month = monthStringToNumber(monthStr);
                    if (month != -1 && isValidDate(day, month, year)) {
                        isoDate = String.format("%04d-%02d-%02d", year, month, day);
                    }
                } else if (pattern.pattern().equals(patterns[2])) {
                    // yyyy-MM-dd
                    int year = Integer.parseInt(matcher.group(1));
                    int month = Integer.parseInt(matcher.group(2));
                    int day = Integer.parseInt(matcher.group(3));
                    if (isValidDate(day, month, year)) {
                        isoDate = String.format("%04d-%02d-%02d", year, month, day);
                    }
                } else if (pattern.pattern().equals(patterns[3])) {
                    // dd/MM/yy или dd/MM/yyyy
                    int day = Integer.parseInt(matcher.group(1));
                    int month = Integer.parseInt(matcher.group(2));
                    int yearPart = Integer.parseInt(matcher.group(3));
                    int year;
                    if (yearPart < 100) {
                        year = (yearPart >= 50) ? 1900 + yearPart : 2000 + yearPart;
                    } else {
                        year = yearPart;
                    }
                    if (isValidDate(day, month, year)) {
                        isoDate = String.format("%04d-%02d-%02d", year, month, day);
                    }
                } else if (pattern.pattern().equals(patterns[4])) {
                    // yyyy/MM/dd
                    int year = Integer.parseInt(matcher.group(1));
                    int month = Integer.parseInt(matcher.group(2));
                    int day = Integer.parseInt(matcher.group(3));
                    if (isValidDate(day, month, year)) {
                        isoDate = String.format("%04d-%02d-%02d", year, month, day);
                    }
                }
                if (isoDate != null) {
                    uniqueDates.add(isoDate);
                }
            }
        }

        // Вывод отсортированных уникальных дат
        for (String date : uniqueDates) {
            System.out.println(date);
        }
    }

    static boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12 || day < 1) return false;
        int[] daysInMonth = {31, (isLeapYear(year) ? 29 : 28), 31,30,31,30,31,31,30,31,30,31};
        return day <= daysInMonth[month -1];
    }

    static boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

    static int monthStringToNumber(String m) {
        switch (m.toLowerCase()) {
            case "jan": case "january": return 1;
            case "feb": case "february": return 2;
            case "mar": case "march": return 3;
            case "apr": case "april": return 4;
            case "may": return 5;
            case "jun": case "june": return 6;
            case "jul": case "july": return 7;
            case "aug": case "august": return 8;
            case "sep": case "september": return 9;
            case "oct": case "october": return 10;
            case "nov": case "november": return 11;
            case "dec": case "december": return 12;
            default: return -1;
        }
    }
}