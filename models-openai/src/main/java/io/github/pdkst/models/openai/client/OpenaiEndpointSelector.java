package io.github.pdkst.models.openai.client;

import io.github.pdkst.models.openai.client.selector.SingletonOpenaiEndpointSelector;

import java.util.ArrayList;
import java.util.List;

/**
 * select openai keys
 *
 * @author pdkst
 * @since 2023/12/30
 */
public interface OpenaiEndpointSelector {
    /**
     * select openai key
     *
     * @return openai key
     */
    OpenaiEndpoint select(String path);

    static OpenaiEndpointSelector singleton(String key) {
        return new SingletonOpenaiEndpointSelector(key);
    }

    static List<OpenaiEndpointSelector> buildSelectors(List<String> keys) {
        final List<OpenaiEndpointSelector> selectors = new ArrayList<>();
        for (String key : keys) {
            selectors.add(new SingletonOpenaiEndpointSelector(key));
        }
        return selectors;
    }
}
