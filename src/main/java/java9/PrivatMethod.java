package java9;

public interface PrivatMethod {

  default void defaultMethod() {
    System.out.println("Call defaultMethod");
    privateMethod();
  }

  private void privateMethod() {
    System.out.println("Call privateMethod");
    privateStaticMethod();
  }

  private static void privateStaticMethod() {
    System.out.println("Call privateStaticMethod");
  }
}
