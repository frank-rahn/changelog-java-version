package java8;

import java.util.List;

public class BulkOperation {

  public long process(List<String> strings) {
    return strings.stream().filter(s -> s.startsWith("test")).sorted().map(String::length).count();
  }
}
