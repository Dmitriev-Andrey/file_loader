package loaders;

import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;

import url.URL;

public class FtpLoader extends Loader {

    public FtpLoader(Path downloadPath) {
        super(downloadPath);
    }

    @Override
    void downloadFile(URL url) throws Exception {
        URLConnection urlConnection = new java.net.URL(url.getFullUrl()).openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        Files.copy(inputStream, pathToDownloadFile(url));
        inputStream.close();
    }
}
