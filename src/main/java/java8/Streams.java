package java8;

import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Streams {

  public void process() {
    Stream<String> streamEmpty = Stream.empty();
    Stream<String> streamOfArray = Stream.of("a", "b", "c");
    Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);
    IntStream intStream = IntStream.range(1, 3);
    LongStream longStream = LongStream.rangeClosed(1, 3);
  }
}
