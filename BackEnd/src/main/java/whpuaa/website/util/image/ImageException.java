package whpuaa.website.util.image;

public class ImageException extends Exception {
    public ImageException() {
    }

    public ImageException(String message) {
        super(message);
    }

    public ImageException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageException(Throwable cause) {
        super(cause);
    }

    public ImageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
