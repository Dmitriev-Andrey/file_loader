package args;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import args.converters.UrlConverter;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.PathConverter;
import url.URL;

public class Args {

    @Parameter(description = "List of download URLs",
            converter = UrlConverter.class,
            required = true)
    private List<URL> urls = new ArrayList<>();

    @Parameter(names = {"--retries", "-r"},
            description = "Count of retries.")
    private int retries = 1;

    @Parameter(names = {"--location", "-l"},
            description = "Download location.",
            converter = PathConverter.class)
    private Path location = Path.of(System.getProperty("user.dir"));

    @Parameter(names = {"--threads", "-t"},
            description = "Count of thread for working")
    private int threads = 1;

    @Parameter(names = {"--help", "-h"}, help = true)
    private boolean help;

    public List<URL> getUrls() {
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

    public boolean isHelp() {
        return help;
    }
}
