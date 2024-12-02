package adventofcode;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class InputReader {

    public static Stream<String> readLines(String fileSource, Class<?> callingClass) {
        URL resource = callingClass.getClassLoader().getResource(fileSource);

        URI uri = null;
        try {
            assert resource != null;
            uri = resource.toURI();

            Path path = Path.of(uri);
            return Files.lines(path);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readString(String fileSource, Class<?> callingClass) {
        URL resource = callingClass.getClassLoader().getResource(fileSource);

        URI uri = null;
        try {
            assert resource != null;
            uri = resource.toURI();

            Path path = Path.of(uri);
            return Files.readString(path);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
