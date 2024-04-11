package io.github.pdkst.models.openai.client.selector;

import io.github.pdkst.models.openai.client.OpenaiEndpoint;
import io.github.pdkst.models.openai.client.OpenaiEndpointSelector;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author pdkst.zhang
 * @since 2024/01/18
 */
@RequiredArgsConstructor
public class RandomOpenaiKeySelector implements OpenaiEndpointSelector {
    private final Random random;
    @Delegate
    private final List<OpenaiEndpointSelector> selectors;

    public RandomOpenaiKeySelector(String... keys) {
        this(Arrays.asList(keys));
    }

    public RandomOpenaiKeySelector(List<String> keys) {
        this(new Random(), OpenaiEndpointSelector.buildSelectors(keys));
    }

    public RandomOpenaiKeySelector(OpenaiEndpointSelector... selectors) {
        this(new Random(), selectors);
    }

    public RandomOpenaiKeySelector(Random random, OpenaiEndpointSelector... selectors) {
        this(new Random(), Arrays.asList(selectors));
    }

    @Override
    public OpenaiEndpoint select(String path) {
        final int nextInt = getNextInt();
        final OpenaiEndpointSelector selector = selectors.get(nextInt);
        return selector.select(path);
    }

    protected int getNextInt() {
        return random.nextInt(selectors.size());
    }
}
