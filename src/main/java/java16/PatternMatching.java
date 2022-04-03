package java16;

public class PatternMatching {

  public boolean process(Object obj) {
    if (obj instanceof String s) {
      System.out.println("obj is String:" + s);
    }

    return obj instanceof String s && s.isBlank();
  }
}
