package java21;

import java.time.Duration;
import java.util.concurrent.Executors;

public class VirtualThreads {

  private static void println(String prefix) {
    System.out.printf(
        "Thread %s: %d %s %n",
        prefix,
        System.currentTimeMillis(),
        Thread.currentThread());
  }

  private void sleep() {
    println("Started");
    try {
      Thread.sleep(Duration.ofSeconds(2));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    println("Ended  ");
  }

  public void process() {
    var t1 = Thread.ofVirtual().unstarted(this::sleep);
    t1.start();

    var t2 = Thread.ofPlatform().start(this::sleep);

    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      var future = executor.submit(this::sleep);

      t1.join();
      t2.join();
      future.resultNow();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
