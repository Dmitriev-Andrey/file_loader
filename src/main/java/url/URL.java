package url;

import exception.UnsupportedProtocolException;

public class URL {

    private final String fullUrl;
    private final Protocol protocol;
    private final String filename;

    public URL(String fullUrl) {
        this.fullUrl = fullUrl;
        this.protocol = parseProtocol(fullUrl);
        this.filename = parseFileName(fullUrl);
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public String getFilename() {
        return fullUrl.hashCode() + "_" + filename;
    }

    private Protocol parseProtocol(String url) {
        String protocolStr = url.substring(0, url.indexOf("://"));
        try {
            return Protocol.valueOf(protocolStr.toUpperCase());
        } catch (Exception e) {
            throw new UnsupportedProtocolException(String.format("protocol.Protocol %s unsupported", protocolStr), e);
        }
    }

    private String parseFileName(String url) {
        int start = 0;
        int end = url.length();
        for (int i = 0; i < url.length(); i++) {
            if (url.charAt(i) == '/') {
                start = i;
            } else if (url.charAt(i) == '?') {
                end = i;
                break;
            }
        }
        return url.substring(start + 1, end);
    }
}
