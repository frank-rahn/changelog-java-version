package java21;

public class RecordPatterns {
  record X(Integer i) {}

  record Value<T1, T2>(T1 first, T2 second) {}

  private void guardedPatterns(Object obj) {
    if (obj instanceof X(Integer i)) {
      System.out.print("i=" + i + ", ");
    }

    switch (obj) {
      case null                   -> println("Null");
      case X(var x) when x > 4    -> println("x > 4");
      case X(var x)               -> println(x.intValue());
      case Value(String a, var b) -> println(a + ": " + b);
      default                     -> println("Hä?");
    }
  }

  public void process() {
    guardedPatterns(null);
    guardedPatterns(new X(0));
    guardedPatterns(new X(5));
    guardedPatterns(new Value<>("Test", 5));
    guardedPatterns(new Value<>(0, 5));
    guardedPatterns(new Object());
  }

  private void println(Object obj) {
    System.out.println(obj);
  }
}
