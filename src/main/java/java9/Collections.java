package java9;

import java.util.Map;

public class Collections {
  public Map<String, Object> process() {
    return Map.ofEntries(
        Map.entry("key1", new Object()),
        Map.entry("key2", new Object())
    );
  }
}
