package java9;

public class PrivatMethodImpl implements PrivatMethod {

  public void prozess() {
    defaultMethod();
    //Compile Errors
    //privateMethod();
    //privateStaticMethod();
  }

}
