package io.github.pdkst.models.openai.client;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author pdkst.zhang
 * @since 2024/01/18
 */
public class OpenaiUrlBuilderTest {

    @Test
    public void build() {
        OpenaiUrlBuilder builder = new OpenaiUrlBuilder();
        final String url = builder.build("/chat/completions");
        assertEquals("https://api.openai.com/v1/chat/completions", url);
    }
}