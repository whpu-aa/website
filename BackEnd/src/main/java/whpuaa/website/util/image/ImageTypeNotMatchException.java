package whpuaa.website.util.image;

public class ImageTypeNotMatchException extends ImageException {
    public ImageTypeNotMatchException() {
    }

    public ImageTypeNotMatchException(String message) {
        super(message);
    }

    public ImageTypeNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageTypeNotMatchException(Throwable cause) {
        super(cause);
    }

    public ImageTypeNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
