import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import exception.DownloadException;
import loaders.Loader;
import loaders.LoaderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import url.URL;

public class ComplexLoader {

    private static final Logger LOG = LoggerFactory.getLogger(ComplexLoader.class);

    private final int retries;
    private final LoaderFactory loaderFactory;
    private final ExecutorService executor;

    public ComplexLoader(LoaderFactory loaderFactory, ExecutorService executor, int retries) {
        this.retries = retries;
        this.loaderFactory = loaderFactory;
        this.executor = executor;
    }

    public void download(List<URL> urls) {
        List<? extends Future<?>> futures = urls.stream()
                .distinct()
                .map(url -> new Thread(() -> download(url)))
                .map(executor::submit)
                .collect(Collectors.toList());

        for (var future : futures) {
            waitFuture(future);
        }
        LOG.info("Done!");
    }

    private void waitFuture(Future<?> future) {
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            LOG.error("Problem with downloading", e);
        }
    }

    private void download(URL url) {
        Loader loader = loaderFactory.getLoader(url.getProtocol());
        int attempt = 1;
        boolean download = false;
        while (attempt <= retries && !download) {
            LOG.info("Download from {}. Attempt: {}", url.getFullUrl(), attempt);
            try {
                loader.download(url);
                LOG.info("File from {} is loaded", url.getFullUrl());
                download = true;
            } catch (DownloadException e) {
                attempt++;
                LOG.warn("Problem with downloading a file from " + url.getFullUrl(), e);
            }
        }
        if (!download) {
            LOG.error("Can't download file from {}", url.getFullUrl());
        }
    }
}
