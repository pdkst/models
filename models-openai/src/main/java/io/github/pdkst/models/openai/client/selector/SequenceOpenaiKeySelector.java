package io.github.pdkst.models.openai.client.selector;

import io.github.pdkst.models.openai.client.OpenaiKey;
import io.github.pdkst.models.openai.client.OpenaiKeySelector;
import io.github.pdkst.models.openai.client.OpenaiUrlBuilder;
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
public class SequenceOpenaiKeySelector implements OpenaiKeySelector {
    private final OpenaiUrlBuilder builder;
    private final List<String> selectors;
    private volatile int index = 0;

    public SequenceOpenaiKeySelector(OpenaiUrlBuilder builder, String... keys) {
        this(builder, Arrays.asList(keys));
    }

    @Override
    public synchronized OpenaiKey select(String path) {
        index++;
        index %= selectors.size();
        final String key = selectors.get(index);
        final String url = builder.build(path);
        return new OpenaiKey(url, key);
    }
}
