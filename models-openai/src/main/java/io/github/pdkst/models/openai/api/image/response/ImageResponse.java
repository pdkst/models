package io.github.pdkst.models.openai.api.image.response;

import io.github.pdkst.models.common.Response;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author pdkst
 * @since 2023/07/30
 */
@Data
@NoArgsConstructor
public class ImageResponse extends Response {
    /**
     * created at
     */
    private Long created;
    /**
     * images
     */
    private List<ImageObject> data;
}
