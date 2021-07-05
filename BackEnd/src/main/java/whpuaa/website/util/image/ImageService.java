package whpuaa.website.util.image;

import whpuaa.website.util.ByteData;

public interface ImageService {
    /**
     * Validate a image.
     *
     * @param data The image data.
     * @throws ImageDecodeException       The byte stream can't be decoded.
     * @throws ImageTypeNotMatchException Real type of the image is not the given one.
     * @throws ImageException             Other validate error.
     */
    void validate(ByteData data) throws ImageException;
}
