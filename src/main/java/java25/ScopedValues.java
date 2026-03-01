package java25;

import java.util.UUID;

public class ScopedValues {
  private static final ScopedValue<String> ID = ScopedValue.newInstance();

  private static void process() {
    ScopedValue.where(ID, UUID.randomUUID().toString())
        .run(
            () -> IO.println("Die ID ist " + ID.get())
        );
  }
}
