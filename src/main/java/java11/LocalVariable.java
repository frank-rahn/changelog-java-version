package java11;

import java.util.function.BiConsumer;

public class LocalVariable {

  public BiConsumer<String, String> process() {
    return (var x, var y) -> x.startsWith(y);
  }

}
