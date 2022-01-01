package java8;

public class DefaultStaticMethodImpl implements DefaultStaticMethod {

  @Override
  public void abstractMethod() {
    System.out.println("Call abstractMethod");
    DefaultStaticMethod.staticMethod();
  }
}
