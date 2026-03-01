package java25;

import module java.base;

public class ModuleImportNeu {
  private void process() {
    String[] fruits = new String[] {"apple", "berry", "citrus"};
    Map<String, String> m =
        Stream.of(fruits)
            .collect(Collectors.toMap(s -> s.toUpperCase().substring(0, 1), Function.identity()));
    IO.println(m);
  }
}
