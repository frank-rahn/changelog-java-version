package java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTime {

  public boolean process() {
    LocalDate birthday = LocalDate.of(1967, 5, 5);
    LocalTime now = LocalTime.now();

    LocalDateTime today = LocalDateTime.now();
    LocalDateTime tomorrow = today.plusDays(1);

    return tomorrow.isAfter(today);
  }
}
