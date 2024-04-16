package io.github.pdkst.models.openai.api.chat.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pdkst
 * @see
 * <a href="https://platform.openai.com/docs/guides/vision/low-or-high-fidelity-image-understanding">Vision guide</a>.
 * @since 2024/04/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentPartImageObject {
    /**
     * Either a URL of the image or the base64 encoded image data.
     */
    private String url;
    /**
     * Specifies the detail level of the image. Learn more in the
     * <a href="https://platform.openai.com/docs/guides/vision/low-or-high-fidelity-image-understanding">Vision guide</a>.
     * <p>
     * Defaults to "auto"
     */
    private String detail;

    public ContentPartImageObject(String url) {
        this.url = url;
        this.detail = "auto";
    }

    public static ContentPartImageObject auto(String url) {
        return new ContentPartImageObject(url);
    }

    public static ContentPartImageObject low(String url) {
        return new ContentPartImageObject(url, "low");
    }

    public static ContentPartImageObject high(String url) {
        return new ContentPartImageObject(url, "high");
    }
}
