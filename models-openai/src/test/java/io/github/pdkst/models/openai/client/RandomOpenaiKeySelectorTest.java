package io.github.pdkst.models.openai.client;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author pdkst.zhang
 * @since 2024/01/19
 */
public class RandomOpenaiKeySelectorTest {
    private RandomOpenaiKeySelector randomOpenaiKeySelector;
    private List<String> keys = Arrays.asList("a", "b", "c");

    @Before
    public void setUp() {
        final OpenaiUrlBuilder builder = new OpenaiUrlBuilder();
        randomOpenaiKeySelector = new RandomOpenaiKeySelector(builder, keys);
    }

    @Test
    public void select() {
        final OpenaiKey openaiKey = randomOpenaiKeySelector.select("/embeddings");
        assertTrue(keys.contains(openaiKey.getKey()));
    }
}