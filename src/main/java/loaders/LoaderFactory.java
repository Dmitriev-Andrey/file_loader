package loaders;

import java.nio.file.Path;
import java.util.EnumMap;
import java.util.function.Supplier;

import url.Protocol;

public class LoaderFactory {

    private final EnumMap<Protocol, Supplier<Loader>> loaderMap = new EnumMap<>(Protocol.class);

    {
        loaderMap.put(Protocol.HTTP, this::buildHttpLoader);
        loaderMap.put(Protocol.HTTPS, this::buildHttpLoader);
        loaderMap.put(Protocol.FTP, this::buildFtpLoader);
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

    private HttpLoader buildHttpLoader() {
        return new HttpLoader(path);
    }

    private SftpLoader buildSftpLoader() {
        return new SftpLoader(path);
    }

    private FtpLoader buildFtpLoader() {
        return new FtpLoader(path);
    }
}
