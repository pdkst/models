package io.github.pdkst.models.openai.endpoint.image.response;

import io.github.pdkst.models.openai.endpoint.image.request.ImagesRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pdkst
 * @since 2023/07/30
 */
@Data
@NoArgsConstructor
public class ImageObject {
    /**
     * The base64-encoded JSON of the generated image, if {@link ImagesRequest#response_format} is {@code b64_json}.
     */
    private String b64_json;
    /**
     * The URL of the generated image, if {@link ImagesRequest#response_format} is {@code url} (default).
     */
    private String url;
}