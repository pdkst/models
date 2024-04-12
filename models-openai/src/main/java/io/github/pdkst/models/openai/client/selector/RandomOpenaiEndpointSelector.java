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
public class RandomOpenaiEndpointSelector implements OpenaiEndpointSelector {
    private final Random random;
    @Delegate
    private final List<OpenaiEndpointSelector> selectors;

    public RandomOpenaiEndpointSelector(String... keys) {
        this(Arrays.asList(keys));
    }

    public RandomOpenaiEndpointSelector(List<String> keys) {
        this(new Random(), OpenaiEndpointSelector.buildSelectors(keys));
    }

    public RandomOpenaiEndpointSelector(OpenaiEndpointSelector... selectors) {
        this(new Random(), selectors);
    }

    public RandomOpenaiEndpointSelector(Random random, OpenaiEndpointSelector... selectors) {
        this(random, Arrays.asList(selectors));
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
