package url;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class URLTest {


    @Test
    public void buildTest() {
        URL url = new URL("http://example.com/file");
        assertEquals(Protocol.HTTP, url.getProtocol());
        assertEquals("http://example.com/file".hashCode() + "_file", url.getFilename());
    }
}