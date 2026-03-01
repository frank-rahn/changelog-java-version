package java25;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModuleImportAlt {
  private void process() {
    String[] fruits = new String[] {"apple", "berry", "citrus"};
    Map<String, String> m =
        Stream.of(fruits)
            .collect(Collectors.toMap(s -> s.toUpperCase().substring(0, 1), Function.identity()));
    System.out.println(m);
  }
}
