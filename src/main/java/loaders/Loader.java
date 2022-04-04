package loaders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import exception.DownloadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import url.URL;

public abstract class Loader {

    private static final Logger LOG = LoggerFactory.getLogger(Loader.class);

    private final Path downloadPath;

    public Loader(Path downloadPath) {
        this.downloadPath = downloadPath;
    }

    public void download(URL url) throws DownloadException {
        try {
            downloadFile(url);
        } catch (Exception e) {
            removeTrash(url);
            throw new DownloadException(e);
        }
    }

    abstract void downloadFile(URL url) throws Exception;

    protected Path pathToDownloadFile(URL url) {
        return Path.of(downloadPath.toString(), url.getFilename());
    }

    private void removeTrash(URL url) {
        try {
            Files.delete(Path.of(downloadPath.toString(), url.getFilename()));
        } catch (IOException e) {
            LOG.error("Can't remove trash for " + url.getFullUrl(), e);
        }
    }
}
