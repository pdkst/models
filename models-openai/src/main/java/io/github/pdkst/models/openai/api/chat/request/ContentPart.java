package io.github.pdkst.models.openai.api.chat.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * @author pdkst
 * @see
 * <a href="https://platform.openai.com/docs/guides/vision/low-or-high-fidelity-image-understanding">Vision guide</a>.
 * @since 2024/04/14
 */
@Data
@NoArgsConstructor
public class ContentPart {
    /**
     * The type of the content part. "text" or "image_url"
     */
    private String type;
    /**
     * The text content of the part.
     */
    private String text;
    /**
     * The image content of the part.
     */
    private ContentPartImageObject image_url;

    public ContentPart(String text) {
        this.type = "text";
        this.text = text;
    }

    public ContentPart(ContentPartImageObject imageObject) {
        this.type = "image_url";
        this.image_url = imageObject;
    }

    public static ContentPart text(String text) {
        return new ContentPart(text);
    }

    public static ContentPart image(String url) {
        return image(new ContentPartImageObject(url));
    }

    public static ContentPart lowImage(String url) {
        return image(ContentPartImageObject.low(url));
    }

    public static ContentPart highImage(String url) {
        return image(ContentPartImageObject.high(url));
    }

    public static ContentPart image(String url, String detail) {
        return image(new ContentPartImageObject(url, detail));
    }

    @NotNull
    public static ContentPart image(ContentPartImageObject imageObject) {
        ContentPart contentPart = new ContentPart();
        contentPart.setType("image_url");
        contentPart.setImage_url(imageObject);
        return contentPart;
    }
}
