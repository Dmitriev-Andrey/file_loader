package exception;

public class UnsupportedProtocolException extends RuntimeException {
    public UnsupportedProtocolException(String message, Throwable cause) {
        super(message, cause);
    }
}
