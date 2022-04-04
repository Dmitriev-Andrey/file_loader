import java.util.List;

import exception.DownloadException;
import loaders.Loader;
import loaders.LoaderFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import url.Protocol;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ComplexLoaderTest {

    private ComplexLoader complexLoader;
    private LoaderFactory loaderFactory;
    private Loader loader;

    @BeforeEach
    void setUp() throws DownloadException {
        loader = mock(Loader.class);

        loaderFactory = mock(LoaderFactory.class);
        when(loaderFactory.getLoader(eq(Protocol.HTTP))).thenReturn(loader);

        complexLoader = new ComplexLoader(loaderFactory, 2, 5);
    }

    @Test
    void downloadSuccess() {
        complexLoader.download(List.of("http://example.com/file"));
    }
}