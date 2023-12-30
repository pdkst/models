package io.github.pdkst.models.openai.endpoint.moderation;

import io.github.pdkst.models.annotation.Required;
import io.github.pdkst.models.http.AbstractHttpExchanger;
import io.github.pdkst.models.http.HttpResponse;
import io.github.pdkst.models.http.request.HttpRequest;
import io.github.pdkst.models.openai.endpoint.moderation.response.ModerationResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Given a input text, outputs if the model classifies it as violating OpenAI's content policy.
 * <p>
 * Related guide: <a href="https://platform.openai.com/docs/guides/moderation">Moderations</a>
 *
 * @author pdkst.zhang
 * @since 2023/11/03
 */
@RequiredArgsConstructor
public class OpenaiModeration {
    private final AbstractHttpExchanger exchanger;

    /**
     * Classifies if text violates OpenAI's Content Policy
     *
     * @param input The input text to classify
     * @param model Two content moderations models are available: text-moderation-stable and text-moderation-latest.
     *              <p>
     *              The default is text-moderation-latest which will be automatically upgraded over time.
     *              This ensures you are always using our most accurate model.
     *              If you use text-moderation-stable, we will provide advanced notice before updating the model.
     *              Accuracy of text-moderation-stable may be slightly lower than for text-moderation-latest.
     * @return A <a href="https://platform.openai.com/docs/api-reference/moderations/object">moderation</a> object.
     * @throws Exception errors
     */
    public ModerationResponse createModeration(@Required List<String> input, String model) throws Exception {
        final HttpRequest post = HttpRequest.post("/v1/moderations")
                .form("input", input)
                .form("model", model);
        final HttpResponse response = exchanger.exchange(post);
        return response.body(ModerationResponse.class);
    }
}
