package java21;

public sealed interface TypePatterns permits TypePatterns.Enum, TypePatterns.Clazz {
  enum Enum implements TypePatterns {A, B}

  final class Clazz implements TypePatterns {}

  private static void typePatterns(TypePatterns typePatterns) {
    switch (typePatterns) {
      case Enum.A  -> System.out.println("Enum.A");
      case Enum.B  -> System.out.println("Enum.B");
      case Clazz z -> System.out.println("Clazz");
    }
  }

  static void process() {
    typePatterns(Enum.A);
    typePatterns(Enum.B);
    typePatterns(new Clazz());
    typePatterns(null);
  }
}
