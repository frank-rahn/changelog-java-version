package java25;

public class Employee extends Person {
  public Employee(String name, int age) {
    if (age < 0 || age > 100) {
      throw new IllegalArgumentException("age must be between 0 and 100");
    }

    super(name, age);
  }
}
