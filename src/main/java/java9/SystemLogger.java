package java9;

import static java.lang.System.Logger.Level.INFO;

import java.lang.System.Logger;

public class SystemLogger {
  private static final Logger LOGGER =
      System.getLogger(SystemLogger.class.getName());

  public static void main(String[] args) {
    if (LOGGER.isLoggable(INFO)) {
      LOGGER.log(INFO, "Info in {0}", "If-Abfrage");
    }

    LOGGER.log(INFO, () -> "Info in %s".formatted("Lamba"));
  }
}
