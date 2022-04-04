package loaders;

import java.nio.file.Path;
import java.util.EnumMap;
import java.util.function.Supplier;

import url.Protocol;

public class LoaderFactory {

    private final EnumMap<Protocol, Supplier<Loader>> loaderMap = new EnumMap<>(Protocol.class);

    {
        loaderMap.put(Protocol.HTTP, this::buildStandardLoader);
        loaderMap.put(Protocol.HTTPS, this::buildStandardLoader);
        loaderMap.put(Protocol.FTP, this::buildStandardLoader);
        loaderMap.put(Protocol.SFTP, this::buildSftpLoader);
    }

    private final Path path;

    public LoaderFactory(Path path) {
        this.path = path;
    }

    public Loader getLoader(Protocol protocol) {
        Supplier<Loader> loaderBuilder = loaderMap.get(protocol);
        return loaderBuilder.get();
    }

    private StandardLoader buildStandardLoader() {
        return new StandardLoader(path);
    }

    private SftpLoader buildSftpLoader() {
        return new SftpLoader(path);
    }
}
