package java16;

public class Records {

  public record Data(String a, Integer b) {}

  public static void main(String[] args) {
    Data data = new Data("Test", 5711);

    System.out.println(data.a());
    System.out.println(data.b());
    System.out.println(data.hashCode());
    System.out.println(data.toString());
  }
}
