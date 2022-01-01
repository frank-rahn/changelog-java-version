package java8;

import java.util.List;

public class Lambda {

  public void printList1(List<String> strings) {
    strings.forEach(
        s -> {
          System.out.println(s);
        });
  }

  public void printList2(List<String> strings) {
    strings.forEach(System.out::println);
  }
}
