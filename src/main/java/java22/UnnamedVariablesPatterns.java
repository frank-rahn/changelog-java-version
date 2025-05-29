package java22;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("ConstantValue")
public class UnnamedVariablesPatterns {
  record Point(int x, int y) {}
  record Square(Point p1, int s) {}

  static void unnamedVariables() {
    try {
      System.out.print("do sleeping");
      Thread.sleep(1000);
      System.out.println("... done");
    } catch (InterruptedException _) {}

    var optional = Optional.of("Yes");
    System.out.println(
        optional.map(_ -> "Optional: No")
    );

    var names = List.of("Frank", "Walter", "Martin", "Gerd");
    System.out.print("Processing for: ");
    for (var _ : names) {
      System.out.print('.');
    }
    System.out.println(" done");

    System.out.print("Processing forEach: ");
    names.forEach(_ -> System.out.print('.'));
    System.out.println(" done");
  }

  static void unnamedPattern() {
    Object p = new Point(1, 2);
    if (p instanceof Point(int x, _)) {
      System.out.println("x=" + x);
    }

    p = new Square(new Point(1, 2), 3);
    if (p instanceof Square(_, int s)) {
      System.out.println("s=" + s);
    }
  }

  static void process() {
    unnamedVariables();
    unnamedPattern();
  }
}
