package io.github.pdkst.models.openai.client.selector;

import io.github.pdkst.models.openai.client.OpenaiKey;
import io.github.pdkst.models.openai.client.OpenaiKeySelector;
import io.github.pdkst.models.openai.client.OpenaiUrlBuilder;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author pdkst.zhang
 * @since 2024/01/18
 */
@RequiredArgsConstructor
public class RandomOpenaiKeySelector implements OpenaiKeySelector {
    private final Random random;
    private final OpenaiUrlBuilder builder;
    private final List<String> keys;

    public RandomOpenaiKeySelector(OpenaiUrlBuilder builder, String... keys) {
        this(builder, Arrays.asList(keys));
    }

    public RandomOpenaiKeySelector(OpenaiUrlBuilder builder, List<String> keys) {
        this(new Random(), builder, keys);
    }

    @Override
    public OpenaiKey select(String path) {
        final int nextInt = getNextInt();
        final String key = keys.get(nextInt);
        final String url = builder.build(path);
        return new OpenaiKey(url, key);
    }

    protected int getNextInt() {
        return random.nextInt(keys.size());
    }
}
