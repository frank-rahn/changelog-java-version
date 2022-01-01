package java9;

import java.util.Optional;
import java.util.stream.Stream;

public class Optional9 {

  public <T> Stream<T> process(Optional<T> valueOpt) {
    T a = valueOpt.or(
        ()-> null
    ).get();

    valueOpt.ifPresentOrElse(t -> {
      System.out.println("not empty");
    }, ()->{
      System.out.println("empty");
    });

    return valueOpt.stream();
  }
}
