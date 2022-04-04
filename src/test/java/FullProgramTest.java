import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import args.Args;
import loaders.LoaderFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FullProgramTest {

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
        Args arguments = Args.parsArgs("-l", tmpFolder, "-t", "2", "http://ftp.gnu.org/gnu/wget/wget-1.5.3.tar.gz",
                "https://ftp.gnu.org/gnu/=README");
        LoaderFactory loaderFactory = new LoaderFactory(arguments.getLocation());
        ComplexLoader loader = new ComplexLoader(loaderFactory, arguments.getRetries(), arguments.getThreads());
        loader.download(arguments.getUrls());

        List<Path> files = Files.list(Path.of(tmpFolder))
                .collect(Collectors.toList());

        assertEquals(2, files.size());

        assertTrue(files.stream().anyMatch(path -> path.getFileName().toString().equals("http://ftp.gnu.org/gnu/wget/wget-1.5.3.tar.gz".hashCode() + "_wget-1.5.3.tar.gz")));
        assertTrue(files.stream().anyMatch(path -> path.getFileName().toString().equals("https://ftp.gnu.org/gnu/=README".hashCode() + "_=README")));
    }
}