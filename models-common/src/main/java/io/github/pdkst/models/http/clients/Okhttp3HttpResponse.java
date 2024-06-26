package io.github.pdkst.models.http.clients;

import io.github.pdkst.models.http.HttpResponse;
import io.github.pdkst.models.json.JsonMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author pdkst
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

    @Override
    public String string() {
        final ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        try {
            return body.string();
        } catch (IOException e) {
            return null;
        }
    }

    public void save(String filePath) throws IOException {
        final ResponseBody body = response.body();
        if (body == null) {
            return;
        }
        try (InputStream inputStream = body.byteStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            try (FileOutputStream outputStream = new java.io.FileOutputStream(filePath)) {
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        }
    }
}
