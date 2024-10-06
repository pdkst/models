package io.github.pdkst.models.bytedance.doubao.client;

import io.github.pdkst.models.http.HttpExchanger;
import io.github.pdkst.models.http.clients.OkHttp3HttpExchanger;
import io.github.pdkst.models.json.JacksonMapper;
import io.github.pdkst.models.json.JsonMapper;
import lombok.Data;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.ObjectUtils;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @author pdkst
 * @since 2024/06/19
 */
@Data
public class DoubaoOptions {
    private String schema = "https";
    private String service = "ark";
    private String regin = "cn-beijing";
    private String domain = "volces.com";
    private String path = "/api";
    private String version = "v3";
    private String model = "";
    private Proxy.Type proxyType = Proxy.Type.HTTP;
    private String proxyHost;
    private Integer proxyPort;
    private String accessKey;
    private String accessSecret;
    private OkHttpClient okHttpClient;
    private JsonMapper jsonMapper;
    private HttpExchanger httpExchanger;

    public HttpExchanger buildHttpExchanger() {
        if (httpExchanger != null) {
            return httpExchanger;
        }
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        if (proxyHost != null && proxyPort != null) {
            final InetSocketAddress proxyAddress = new InetSocketAddress(proxyHost, proxyPort);
            final Proxy.Type proxyType = ObjectUtils.defaultIfNull(this.proxyType, Proxy.Type.HTTP);
            Proxy proxy = new Proxy(proxyType, proxyAddress);
            okHttpClient = okHttpClient.newBuilder().proxy(proxy).build();
        }
        if (jsonMapper == null) {
            jsonMapper = new JacksonMapper();
        }
        return new OkHttp3HttpExchanger(okHttpClient, jsonMapper);
    }

    public DoubaoEndpointSelector buildSelector() {
        return null;
    }
}
