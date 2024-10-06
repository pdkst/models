package io.github.pdkst.models.bytedance.doubao.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pdkst
 * @since 2024/06/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoubaoEndpoint {
    private String url;
    private String endpointId;
}
