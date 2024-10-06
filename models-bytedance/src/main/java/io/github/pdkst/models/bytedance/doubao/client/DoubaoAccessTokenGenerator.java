package io.github.pdkst.models.bytedance.doubao.client;

import com.volcengine.ApiClient;
import com.volcengine.ApiException;
import com.volcengine.ark.ArkApi;
import com.volcengine.ark.model.GetApiKeyRequest;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.util.Arrays;

/**
 * @author pdkst
 * @since 2024/10/06
 */
@RequiredArgsConstructor
public class DoubaoAccessTokenGenerator {
    private final ArkApi arkApi;

    public DoubaoAccessTokenGenerator(ApiClient apiClient) {
        this.arkApi = new ArkApi(apiClient);
    }

    public String generate(String resourceType, String... resourceIds) throws ApiException {
        return this.generate(Duration.ofSeconds(30), resourceType, resourceIds);
    }

    public String generate(Duration duration, String resourceType, String... resourceIds) throws ApiException {
        final GetApiKeyRequest request = new GetApiKeyRequest();
        request.setDurationSeconds((int) duration.getSeconds());
        request.setResourceType(resourceType);
        request.setResourceIds(Arrays.asList(resourceIds));
        return this.arkApi.getApiKey(request).getApiKey();
    }
}
