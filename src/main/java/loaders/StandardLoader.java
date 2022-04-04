package loaders;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;

public class StandardLoader extends Loader {

    public StandardLoader(Path downloadPath) {
        super(downloadPath);
    }

    @Override
    public void downloadFile(url.URL url) throws IOException {
        try (ReadableByteChannel readChannel = Channels.newChannel(new java.net.URL(url.getFullUrl()).openStream());
             FileOutputStream fileOS = new FileOutputStream(pathToDownloadFile(url).toString());
             FileChannel writeChannel = fileOS.getChannel()) {
            writeChannel.transferFrom(readChannel, 0, Long.MAX_VALUE);
        }
    }
}
