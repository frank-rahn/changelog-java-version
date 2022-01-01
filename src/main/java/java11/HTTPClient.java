package java11;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

public class HTTPClient {

  public CompletableFuture<String> process(String uri) {
    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();

    return client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body);
  }
}
