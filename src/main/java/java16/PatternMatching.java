package java16;

public class PatternMatching {

  public void process(Object obj) {
    if(obj instanceof String s && s.isBlank()) {
      System.out.println("obj is Blank String:" + s);
    }
  }

}
