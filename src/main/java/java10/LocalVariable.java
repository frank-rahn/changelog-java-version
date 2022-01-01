package java10;

import java.util.ArrayList;

public class LocalVariable {

  public long process() {
    var list = new ArrayList<String>();
    list.add("Frank");

    var stream = list.stream();
    return stream.count();
  }
}
