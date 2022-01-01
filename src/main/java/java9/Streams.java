package java9;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {

  public void process() {
    Stream.of(10, 42, 4711).dropWhile(i -> i < 50).forEach(System.out::println);
    Stream.of(10, 42, 4711).takeWhile(i -> i < 50).forEach(System.out::println);
    IntStream.iterate(1, i -> i < 4711, i -> i * 42).forEach(System.out::println);
    Stream.ofNullable(null).forEach(System.out::println);
    Stream.ofNullable("Frank").forEach(System.out::println);
  }
}
