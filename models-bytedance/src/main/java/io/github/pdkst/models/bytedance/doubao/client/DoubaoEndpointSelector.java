package io.github.pdkst.models.bytedance.doubao.client;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


/**
 * @author pdkst
 * @since 2024/06/19
 */
@Data
public class DoubaoEndpointSelector {
    private final DoubaoUrlBuilder urlBuilder;
    private final List<DoubaoEndpointNode> endpoints;

    public DoubaoEndpoint select(String model, String path) {
        final String url = urlBuilder.build(path);
        DoubaoEndpointNode node = selectNode(model);
        if (node == null) {
            throw new IllegalArgumentException("model not found: " + model);
        }
        return new DoubaoEndpoint(url, node.getEndpointId());
    }

    public DoubaoEndpointNode selectNode(String model) {
        for (DoubaoEndpointNode node : endpoints) {
            if (StringUtils.equals(node.getModel(), model)) {
                return node;
            }
        }
        return null;
    }
}
