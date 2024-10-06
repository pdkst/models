package io.github.pdkst.models.bytedance.doubao.client;

import lombok.Data;

/**
 * @author pdkst
 * @since 2024/06/19
 */
@Data
public class DoubaoUrlBuilder {
    private String schema = "https";
    private String service = "ark";
    private String regin = "cn-beijing";
    private String domain = "volces.com";
    private String path = "/api";
    private String version = "v3";

    public String build(String api) {
        return String.format("%s://%s.%s.%s/%s/%s/%s", schema, service, regin, domain, path, version, api);
    }
}
