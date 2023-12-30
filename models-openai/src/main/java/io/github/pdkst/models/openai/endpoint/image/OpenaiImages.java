package io.github.pdkst.models.openai.endpoint.image;

import io.github.pdkst.models.http.HttpExchanger;
import io.github.pdkst.models.http.HttpResponse;
import io.github.pdkst.models.http.request.HttpRequest;
import io.github.pdkst.models.openai.endpoint.image.request.ImagesRequest;
import io.github.pdkst.models.openai.endpoint.image.response.ImageResponse;
import lombok.RequiredArgsConstructor;

/**
 * Creates an image given a prompt.
 *
 * @author pdkst.zhang
 * @see <a href="https://platform.openai.com/docs/api-reference/images">Images</a>
 * @since 2023/11/02
 */
@RequiredArgsConstructor
public class OpenaiImages {
    private final HttpExchanger exchanger;

    public ImageResponse createImages(ImagesRequest request) throws Exception {
        HttpRequest post = HttpRequest.create()
                .url("/v1/images/generations")
                .json(request);
        final HttpResponse response = exchanger.exchange(post);
        return response.body(ImageResponse.class);
    }

}
