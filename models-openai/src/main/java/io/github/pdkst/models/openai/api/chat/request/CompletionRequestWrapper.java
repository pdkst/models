package io.github.pdkst.models.openai.api.chat.request;

import lombok.Data;

/**
 * CompletionRequest practical wrapper.
 *
 * @author pdkst.zhang
 * @since 2023/10/29
 */
@Data
public class CompletionRequestWrapper {
    private final CompletionRequest request;

    public CompletionRequestWrapper(CompletionRequest request) {
        this.request = request;
    }

    public CompletionRequestWrapper() {
        this(new CompletionRequest());
    }

    public CompletionRequest build() {
        return request;
    }
}
