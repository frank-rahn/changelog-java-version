package java23;

/// Die Klasse Markdown zeigt die Nutzung von Markdown
/// in Javadoc.
///
/// CSS-Syntax:
/// ```css 
/// p {color: red}
/// ```
///
/// @author Frank Rahn
/// @since 25.09.2024
/// @version 1.0
public class Markdown {
  /// Diese Methode zeigt die Nutzung von Markdown an
  /// einer Methode.
  /// @param argument Ein `int`
  /// @return das übergebene Argument als [java.lang.Integer]
  ///   oder `null`, falls `argument` kleiner 0 ist
  public Integer process(int argument) {
    return argument < 0 ? null : argument;
  }
}
