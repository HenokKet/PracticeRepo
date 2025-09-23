import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Exercise01 {

    // LocalDate
    // ========================
    // Complete the numbered tasks below.
    // Open and execute the accompanying tests to confirm your solution is correct.

    // 1. return today's date
    LocalDate getToday() {
        return LocalDate.now();
    }

    // 2. return December 17, 1903 as a LocalDate
    LocalDate getFirstFlightDate() {
        return LocalDate.of(1903,12,17);
    }

    // 3. if parameter is in the future, return null.
    // Otherwise, add 5 days to the parameter and return the result.
    LocalDate makeFutureNullShiftThePast(LocalDate date) {
        if (date.isAfter(LocalDate.now())){
            return null;
        }else{
            date = date.plusDays(5);
        }
        return date;
    }

    // 4. return the fifth Friday from the parameter date.
    // if the date is Friday, don't count it.
    LocalDate fiveFridaysFromDate(LocalDate date) {
        LocalDate firstFriday = date.with(DayOfWeek.FRIDAY);
        LocalDate friday = firstFriday.plusWeeks(4);
        return friday;
    }

    // 5. given a date and a count,
    // return a list of the next `fridayCount` Fridays after the date.
    // if the date is Friday, don't include it.
    List<LocalDate> getNextFridays(LocalDate date, int fridayCount) {
        List<LocalDate> fridays = new ArrayList<>();
        if (fridayCount <= 0) return fridays;

        LocalDate firstFriday = date.with(DayOfWeek.FRIDAY);
        for (int i = 0; i < fridayCount; i++) {
            fridays.add(firstFriday.plusWeeks(i));
        }
        return fridays;
    }

    // 6. return the absolute value of the days between two dates.
    // one may be before two, two may be before one, but neither will be null
    int getDaysBetween(LocalDate one, LocalDate two) {
        long days = ChronoUnit.DAYS.between(one,two);
        return (int) Math.abs(days);
    }

}
