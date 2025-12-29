package java24;

import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class StreamGatherers {

  static void process() {
    // scan()
    System.out.println(
        Stream.of(100.0, 70.0, -50.0, 30.0, 75.0, 200.0, 15.0)
            .gather(Gatherers.scan(() -> 0.1, Double::sum))
            .toList());
    // [100.1, 170.1, 120.1, 150.1, 225.1, 425.1, 440.1]

    // fold()
    System.out.println(
        Stream.of(1, 2, 7).gather(Gatherers.fold(() -> 1, (x, y) -> x * y)).toList());
    // [14]

    // windowFixed()
    System.out.println(Stream.of(1, 2, 3, 4, 5, 6, 7).gather(Gatherers.windowFixed(3)).toList());
    // [[1, 2, 3], [4, 5, 6], [7]]

    // windowSliding()
    System.out.println(
        Stream.of("1", "2", "3", "4", "5", "6", "7").gather(Gatherers.windowSliding(3)).toList());
    // [[1, 2, 3], [2, 3, 4], [3, 4, 5], [4, 5, 6], [5, 6, 7]]

    // mapConcurrent()
    System.out.println(
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .gather(Gatherers.mapConcurrent(5, x -> x * x))
            .toList());
    // [1, 4, 9, 16, 25, 36, 49, 64, 81, 100]

    // Kombination von Operationen
    System.out.println(
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)
            .gather(Gatherers.windowFixed(3))
            .gather(Gatherers.windowFixed(2))
            .toList());
    // [[[1, 2, 3], [4, 5, 6]], [[7, 8, 9], [10, 11, 12]], [[13]]]

    System.out.println(
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)
            .gather(Gatherers.windowFixed(3).andThen(Gatherers.windowFixed(2)))
            .toList());
    // [[[1, 2, 3], [4, 5, 6]], [[7, 8, 9], [10, 11, 12]], [[13]]]
  }
}
