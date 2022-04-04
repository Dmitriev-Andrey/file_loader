package args;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.PathConverter;

public class Args {

    @Parameter(description = "List of URLs to download",
            required = true)
    private List<String> urls = new ArrayList<>();

    @Parameter(names = {"--retries", "-r"},
            description = "Count of retries. Default = 1")
    private int retries = 1;

    @Parameter(names = {"--location", "-l"},
            description = "Download location. Default - current location",
            converter = PathConverter.class)
    private Path location = Path.of(System.getProperty("user.dir"));

    @Parameter(names = {"--threads", "-t"},
            description = "Count of thread for working. Default = 1")
    private int threads = 1;

    public List<String> getUrls() {
        return urls;
    }

    public int getRetries() {
        return retries;
    }

    public Path getLocation() {
        return location;
    }

    public int getThreads() {
        return threads;
    }

    public static Args parsArgs(String... args) {
        Args arguments = new Args();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);
        return arguments;
    }
}
