import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import args.Args;
import com.beust.jcommander.JCommander;
import loaders.LoaderFactory;

public class Main {

    public static void main(String[] args) {
        Args arguments = new Args();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(arguments)
                .build();
        jCommander.parse(args);
        jCommander.setProgramName("file_loader");
        if (arguments.isHelp()) {
            jCommander.usage();
            return;
        }

        LoaderFactory loaderFactory = new LoaderFactory(arguments.getLocation());
        ExecutorService executorService = Executors.newFixedThreadPool(arguments.getThreads());
        ComplexLoader loader = new ComplexLoader(loaderFactory, executorService, arguments.getRetries());
        try {
            loader.download(arguments.getUrls());
        } finally {
            executorService.shutdown();
        }
    }
}
