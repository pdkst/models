package io.github.pdkst.models.openai.client;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author pdkst.zhang
 * @since 2024/01/18
 */
@RequiredArgsConstructor
public class RandomOpenaiKeySelector {
    private final Random random;
    private final OpenaiUrlBuilder builder;
    private final List<String> keys;

    public RandomOpenaiKeySelector(OpenaiUrlBuilder builder, String... keys) {
        this(builder, Arrays.asList(keys));
    }

    public RandomOpenaiKeySelector(OpenaiUrlBuilder builder, List<String> keys) {
        this(new Random(), builder, Collections.unmodifiableList(keys));
    }

    public OpenaiKey select(String path) {
        final int nextInt = getNextInt();
        final String key = keys.get(nextInt);
        return new OpenaiKey(key, builder.build(path));
    }

    protected int getNextInt() {
        return random.nextInt(keys.size());
    }
}
