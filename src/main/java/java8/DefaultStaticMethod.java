package java8;

public interface DefaultStaticMethod {

  void abstractMethod();

  default void defaultMethod() {
    System.out.println("Call defaultMethod");
    abstractMethod();
  }

  static void staticMethod() {
    System.out.println("Call staticMethod");
  }
}
