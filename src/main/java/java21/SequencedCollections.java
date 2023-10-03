package java21;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.SequencedCollection;

public class SequencedCollections {

  private final SequencedCollection<Integer> list = new LinkedList<>();
  private final SequencedCollection<Integer> set = new LinkedHashSet<>();

  public void process() {
    list.addFirst(4713);
    list.addFirst(4711);
    list.addLast(4715);
    set.addAll(list);

    var first = list.getFirst();
    var last = list.getLast();

    first = set.removeFirst();
  }
}
