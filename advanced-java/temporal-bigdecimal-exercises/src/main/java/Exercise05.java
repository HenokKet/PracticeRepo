import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class Exercise05 {

    // THE GODMOTHER
    // ========================
    // Complete the numbered tasks below.
    // Open and execute the accompanying tests to confirm your solution is correct.

    // 1. Your Godmother gives you $10 every other Friday throughout the year.
    // Payments start on the first Friday of the year.
    // Given a date, calculate payments expected from that date until the end of the year.
    BigDecimal calculateGiftsTilEndOfYear(LocalDate date) {
        LocalDate fFriday = LocalDate.of(date.getYear(), 1,1).with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        LocalDate lDay = LocalDate.of(date.getYear(), 12,31);

        LocalDate current = fFriday;
        while (!current.isAfter(date)) {
            current = current.plusWeeks(2);
        }

        int count = 0;
        while (!current.isAfter(lDay)) {
            count++;
            current = current.plusWeeks(2);
        }

        // $10 per payment
        return BigDecimal.TEN.multiply(BigDecimal.valueOf(count));
    }

    // 2. Your Godmother is getting quirky. She adjusted her payment schedule.
    // She still pays every other Friday throughout the year, starting on the first Friday of the year.
    // But now, she pays a number of dollars equal to the day of the month.
    // Examples
    // Jan 31 == $31
    // Mar 1 == $1
    // July 12 == $12
    // Given a date, calculate payments expected from that date until the end of the year.
    BigDecimal calculateQuirkyGiftsTilEndOfYear(LocalDate date) {
        LocalDate fFriday = LocalDate.of(date.getYear(), 1,1).with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        LocalDate lDay = LocalDate.of(date.getYear(), 12,31);

        LocalDate current = fFriday;
        while (!current.isAfter(date)) {
            current = current.plusWeeks(2);
        }

        int count = 0;
        BigDecimal total = BigDecimal.ZERO;
        while (!current.isAfter(lDay)) {
            total = total.add(BigDecimal.valueOf(current.getDayOfMonth()));
            current = current.plusWeeks(2);
        }

        return total;
    }

}
