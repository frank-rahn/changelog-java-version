package java11;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WriteString {
  public String process(URI uri) throws IOException {
    Path filePath = Paths.get(uri);

    Files.writeString(filePath, "Hello World",
        StandardOpenOption.APPEND);

    return Files.readString(filePath);
  }
}
