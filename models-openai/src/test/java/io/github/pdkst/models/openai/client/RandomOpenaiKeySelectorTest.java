package io.github.pdkst.models.openai.client;

import io.github.pdkst.models.openai.client.selector.RandomOpenaiKeySelector;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

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
        randomOpenaiKeySelector = new RandomOpenaiKeySelector(keys);
    }

    @Test
    public void select() {
        final OpenaiEndpoint endpoint = randomOpenaiKeySelector.select("/embeddings");
        assertNotNull(endpoint);
    }
}