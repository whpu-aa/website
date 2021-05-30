package whpuaa.website.controller;

public class InvalidModelException extends RuntimeException{
    public InvalidModelException() {
    }

    public InvalidModelException(String message) {
        super(message);
    }

    public InvalidModelException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidModelException(Throwable cause) {
        super(cause);
    }

    public InvalidModelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
