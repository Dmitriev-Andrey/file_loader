package loaders;

import java.nio.file.Path;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;
import url.URL;

public class SftpLoader extends Loader {

    public SftpLoader(Path downloadPath) {
        super(downloadPath);
    }

    @Override
    void downloadFile(URL url) throws Exception {
        FileSystemManager manager = VFS.getManager();
        try (FileObject local = manager.resolveFile(pathToDownloadFile(url).toString());
             FileObject remote = manager.resolveFile(url.getFullUrl())) {
            local.copyFrom(remote, Selectors.SELECT_SELF);
        }
    }
}
