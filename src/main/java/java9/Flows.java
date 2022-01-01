package java9;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

public class Flows implements Flow.Subscriber<String> {

  private Subscription subscription;

  @Override
  public void onSubscribe(Subscription subscription) {
    this.subscription = subscription;
    System.out.println("Starting ...");
    this.subscription.request(1);
  }

  @Override
  public void onNext(String item) {
    System.out.println(item);
    this.subscription.request(1);
  }

  @Override
  public void onError(Throwable throwable) {
    throwable.printStackTrace();
  }

  static CountDownLatch countDown = new CountDownLatch(1);

  @Override
  public void onComplete() {
    System.out.println("Finished");
    countDown.countDown();
  }

  public static void main(String[] args) throws InterruptedException {
    SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
    publisher.subscribe(new Flows());

    List.of("a", "b", "c", "d").forEach(publisher::submit);
    publisher.close();

    countDown.await();
  }
}
