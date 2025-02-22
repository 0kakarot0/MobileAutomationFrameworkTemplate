package exception;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
