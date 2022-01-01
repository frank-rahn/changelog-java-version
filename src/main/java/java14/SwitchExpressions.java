package java14;

import java.time.DayOfWeek;

public class SwitchExpressions {

  public int process(DayOfWeek day) {
    return switch (day) {
      case MONDAY, FRIDAY, SUNDAY -> 6;
      case TUESDAY -> 7;
      case THURSDAY, SATURDAY -> 8;
      default -> {
        yield f();
      }
    };
  }

  private int f() {
    return 4711;
  }
}
