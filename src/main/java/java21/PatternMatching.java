package java21;

import static java.math.MathContext.UNLIMITED;

import java.math.BigDecimal;

public class PatternMatching {
  private final String pi = String.valueOf(Math.PI);

  private String formatterPatternSwitch(Object obj) {
    return switch (obj) {
      case null      -> "Object is null";
      case Integer i -> String.format("int    %d", i);
      case Long l    -> String.format("long   %d", l);
      case Double d  -> String.format("double %f", d);
      case String s  -> String.format("String %s", s);
      default        -> String.format("Object %s", obj);
    };
  }

  private void guardedCaseLabel(String text) {
    switch (text) {
      case null                          -> println("Null");
      case "Foo", "Bar"                  -> println("Ok");
      case String s when s.equals("yes") -> println("True");
      case String s when s.equals("no")  -> println("False");
      case String s                      -> println(s);
    }
  }

  private void nullAndDefault(String s) {
    switch (s) {
      case "Text"        -> println("Text");
      case null, default -> println(s);
    }
  }

  public void process() {
    println(formatterPatternSwitch(null));
    println(formatterPatternSwitch(Integer.valueOf("3")));
    println(formatterPatternSwitch(Long.valueOf("3")));
    println(formatterPatternSwitch(Double.valueOf(pi)));
    println(formatterPatternSwitch(pi));
    println(formatterPatternSwitch(new BigDecimal(pi, UNLIMITED)));

    System.out.println();

    guardedCaseLabel(null);
    guardedCaseLabel("Foo");
    guardedCaseLabel("Bar");
    guardedCaseLabel("yes");
    guardedCaseLabel("no");
    guardedCaseLabel("Hä?");

    System.out.println();

    nullAndDefault(null);
    nullAndDefault("Text");
    nullAndDefault("Blubber");
  }

  private void println(Object obj) {
    System.out.println(obj);
  }
}
