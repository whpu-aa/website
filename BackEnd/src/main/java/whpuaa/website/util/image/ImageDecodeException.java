package whpuaa.website.util.image;

public class ImageDecodeException extends ImageException {
    public ImageDecodeException() {
    }

    public ImageDecodeException(String message) {
        super(message);
    }

    public ImageDecodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageDecodeException(Throwable cause) {
        super(cause);
    }

    public ImageDecodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
