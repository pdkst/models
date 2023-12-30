package io.github.pdkst.models.openai.endpoint.image.request;

import io.github.pdkst.models.annotation.Required;
import lombok.Data;

/**
 * Creates an image given a prompt.
 *
 * @author pdkst.zhang
 * @since 2023/11/02
 */
@Data
public class ImagesRequest {
    /**
     * A text description of the desired image(s). The maximum length is 1000 characters.
     */
    @Required
    private String prompt;

    /**
     * The number of images to generate. Must be between 1 and 10.
     */
    private Integer n;

    /**
     * The format in which the generated images are returned.
     * Must be one of {@code url} or {@code b64_json}.
     */
    private String response_format;

    /**
     * The size of the generated images. Must be one of {@code 256x256}, {@code 512x512}, or {@code 1024x1024}.
     */
    private String size;

    /**
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     * <p>
     * <a href="https://platform.openai.com/docs/guides/safety-best-practices/end-user-ids">Learn more</a>.
     */
    private String user;
}
