import java.nio.file.Path;
import java.util.Arrays;

import args.Args;
import com.beust.jcommander.ParameterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ArgsTest {

    @Test
    public void parsArgs() {
        Args args = Args.parsArgs("-l", "/home/user/tmp", "-r", "5", "http://example.com", "sftp://example-file.com");
        assertEquals(Path.of("/home/user/tmp"), args.getLocation());
        assertEquals(5, args.getRetries());
        assertEquals(Arrays.asList("http://example.com", "sftp://example-file.com"), args.getUrls());
    }

    @Test
    public void notRequireArgs() {
        assertThrows(ParameterException.class, () -> {
            Args.parsArgs("-l", "/home/user/tmp", "-r", "5");
        });
    }
}