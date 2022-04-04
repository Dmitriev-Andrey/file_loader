import args.Args;
import loaders.LoaderFactory;

public class Main {

    public static void main(String[] args) {
        Args arguments = Args.parsArgs(args);
        final LoaderFactory loaderFactory = new LoaderFactory(arguments.getLocation());
        ComplexLoader loader = new ComplexLoader(loaderFactory, arguments.getRetries(), arguments.getThreads());
        loader.download(arguments.getUrls());
        System.exit(0);
    }
}
