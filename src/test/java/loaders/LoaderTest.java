package loaders;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

import exception.DownloadException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import url.URL;

import static org.junit.jupiter.api.Assertions.*;

class LoaderTest {

    private final String tmpFolder = System.getProperty("user.dir") + "/tmp";

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectory(Path.of(tmpFolder));
    }

    @AfterEach
    void tearDown() throws IOException {
        try (Stream<Path> walk = Files.walk(Path.of(tmpFolder))) {
            walk.sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }

    @Test
    void download() throws IOException {
        Loader loader = new Loader(Path.of(tmpFolder)) {
            @Override
            void downloadFile(URL url) throws Exception {
                Files.createFile(Path.of(tmpFolder, url.getFilename()));
                assertEquals(1, Files.list(Path.of(tmpFolder)).count());
                throw new IllegalStateException("test exception");
            }
        };

        assertThrows(DownloadException.class, () -> loader.download(new URL("http://example.com/file.md")));

        assertEquals(0, Files.list(Path.of(tmpFolder)).count());
    }
}