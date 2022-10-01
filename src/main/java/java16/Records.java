package java16;

public class Records {

  public record Data(String a, Integer b) {}

  public record NamedTuple<T>(String name, T... values) {}

  public record Range(int low, int high) {
    public Range() {
      this(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public Range {
      if (low > high) {
        throw  new IllegalArgumentException("low greater high");
      }
    }

    public int length() {
      return high - low;
    }
  }

  public static void main(String[] args) {
    var data = new Data("Test", 5711);
    var data2 = new Data("Test", 5711);

    System.out.println(data.a());
    System.out.println(data.b());
    System.out.println(data.toString());
    System.out.println(data.hashCode());
    System.out.println(data.equals(data2));

    var tuple = new NamedTuple<>("Geld", 0.5, 3.14);
    System.out.println(tuple);

    var range = new Range();
    System.out.println(range);
    System.out.println(range.length());
    new Range(2, 1);
  }
}
