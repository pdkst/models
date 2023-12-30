package io.github.pdkst.models.http.clients;

import io.github.pdkst.models.http.HttpResponse;
import io.github.pdkst.models.json.JsonMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.InputStream;

/**
 * @author pdkst.zhang
 * @since 2023/10/29
 */
@Data
@RequiredArgsConstructor
public class Okhttp3HttpResponse implements HttpResponse {
    private final Response response;
    private final JsonMapper jsonMapper;

    @Override
    public <T> T body(Class<T> resultType) throws Exception {
        final ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        return jsonMapper.parse(body.string(), resultType);
    }

    @Override
    public InputStream byteStream() {
        return response.body() != null ? response.body().byteStream() : null;
    }
}
