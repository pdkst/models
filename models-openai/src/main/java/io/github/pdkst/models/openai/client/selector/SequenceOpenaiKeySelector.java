package io.github.pdkst.models.openai.client.selector;

import io.github.pdkst.models.openai.client.OpenaiEndpoint;
import io.github.pdkst.models.openai.client.OpenaiEndpointSelector;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * 顺序选择器
 *
 * @author pdkst.zhang
 * @since 2024/01/19
 */
@Data
@RequiredArgsConstructor
public class SequenceOpenaiKeySelector implements OpenaiEndpointSelector {
    private final List<OpenaiEndpointSelector> selectors;
    private volatile int index = 0;

    public SequenceOpenaiKeySelector(String... keys) {
        this(OpenaiEndpointSelector.buildSelectors(Arrays.asList(keys)));
    }

    @Override
    public synchronized OpenaiEndpoint select(String path) {
        index++;
        index %= selectors.size();
        final OpenaiEndpointSelector selector = selectors.get(index);
        return selector.select(path);
    }
}
