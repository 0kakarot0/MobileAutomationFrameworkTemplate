package exception;

public class MobileElementAttributeException extends RuntimeException {
    public MobileElementAttributeException(String message) {
        super(message);
    }

    public MobileElementAttributeException(String message, Throwable cause) {
        super(message, cause);
    }
}
