package java16;

public class Records {

  public record Data(String a, Integer b) {}

  public record NamedTuple<T>(String name, T... values) {}

  public static void main(String[] args) {
    Data data = new Data("Test", 5711);
    Data data2 = new Data("Test", 5711);

    System.out.println(data.a());
    System.out.println(data.b());
    System.out.println(data.toString());
    System.out.println(data.hashCode());
    System.out.println(data.equals(data2));

    var tuple = new NamedTuple<>("Geld", 0.5, 3.14);
    System.out.println(tuple);
  }
}
