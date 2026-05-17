package java26;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class HTTPClient {
  public CompletableFuture<String> process(String uri) {
    var client = HttpClient.newHttpClient();

    var request =
        HttpRequest.newBuilder()
            .GET()
            .version(HttpClient.Version.HTTP_3)
            .uri(URI.create(uri))
            .build();

    return client
        .sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenApply(HttpResponse::body);
  }
}
