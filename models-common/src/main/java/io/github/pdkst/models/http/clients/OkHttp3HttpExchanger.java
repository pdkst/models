package io.github.pdkst.models.http.clients;

import io.github.pdkst.models.http.AbstractHttpExchanger;
import io.github.pdkst.models.http.HttpInputSource;
import io.github.pdkst.models.http.HttpResponse;
import io.github.pdkst.models.http.listener.ServerSideEventListener;
import io.github.pdkst.models.http.request.FormValue;
import io.github.pdkst.models.http.request.HttpFormValues;
import io.github.pdkst.models.http.request.HttpRequest;
import io.github.pdkst.models.json.JacksonMapper;
import io.github.pdkst.models.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/**
 * @author pdkst.zhang
 * @since 2023/10/29
 */
@RequiredArgsConstructor
public class OkHttp3HttpExchanger extends AbstractHttpExchanger {
    private final OkHttpClient client;
    private final JsonMapper jsonMapper;

    public OkHttp3HttpExchanger() {
        this(new OkHttpClient.Builder()
                .build(), new JacksonMapper());
    }

    @Override
    protected HttpResponse doExchange(HttpRequest request) throws Exception {
        final Request okhttp3Request = buildRequest(request);
        final Response response = client.newCall(okhttp3Request).execute();
        return new Okhttp3HttpResponse(response, jsonMapper);
    }

    @Override
    public void doServerSideEvent(HttpRequest request, ServerSideEventListener listener) throws Exception {
        final Request rawRequest = buildRequest(request);
        EventSource.Factory factory = EventSources.createFactory(client);
        final Okhttp3EventListenerAdapter listenerAdapter
                = new Okhttp3EventListenerAdapter(listener, jsonMapper);
        factory.newEventSource(rawRequest, listenerAdapter);
    }

    @NotNull
    private Request buildRequest(HttpRequest request) throws Exception {
        final String url = buildUrl(request);
        final RequestBody body = buildBody(request);
        final Request.Builder builder = new Request.Builder()
                .url(url)
                .header("Accept", "*/*")
                .method(request.method().name(), body);
        appendHeader(builder, request);
        return builder.build();
    }

    private void appendHeader(Request.Builder builder, HttpRequest request) {
        if (MapUtils.isEmpty(request.header())) {
            return;
        }
        for (Map.Entry<String, String> entry : request.header().entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();
            if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
                continue;
            }
            builder.addHeader(key, entry.getValue());
        }
    }

    @NotNull
    private RequestBody buildBody(HttpRequest request) throws Exception {
        final io.github.pdkst.models.http.MediaType mediaType = request.contentType();
        switch (mediaType) {
            case FORM_DATA:
                return buildFormBody(request);
            case PARTY_FORM_DATA:
                return buildMultiPartBody(request);
            default:
                return buildJsonBody(request, mediaType);
        }
    }

    @NotNull
    private RequestBody buildJsonBody(HttpRequest request,
                                      io.github.pdkst.models.http.MediaType mediaType) throws Exception {
        final String json = jsonMapper.value(request.json());
        return RequestBody.create(json, MediaType.parse(mediaType.getValue()));
    }

    @NotNull
    private FormBody buildFormBody(HttpRequest request) {
        final FormBody.Builder builder = new FormBody.Builder();
        final HttpFormValues form = request.form();
        for (FormValue formValue : form) {
            final List<String> values = formValue.getValues();
            if (values == null) {
                continue;
            }
            for (String value : values) {
                if (value == null) {
                    continue;
                }
                builder.add(formValue.getKey(), value);
            }
        }
        return builder.build();
    }

    @NotNull
    private RequestBody buildMultiPartBody(HttpRequest request) {
        final MultipartBody.Builder builder = new MultipartBody.Builder();
        final HttpFormValues form = request.form();
        for (FormValue formValue : form) {
            final List<String> values = formValue.getValues();
            if (values == null) {
                continue;
            }
            for (String value : values) {
                if (value == null) {
                    continue;
                }
                builder.addFormDataPart(formValue.getKey(), value);
            }
        }
        final HttpInputSource source = request.formInputSource();
        if (source != null) {
            final RequestBody body = RequestBody.create(null, source.file());
            builder.addFormDataPart(source.name(), "file", body);
        }
        return builder.build();
    }

    private String buildUrl(HttpRequest request) {
        String url = request.url();
        if (StringUtils.isBlank(url)) {
            throw new IllegalArgumentException("URL is required");
        }
        final Map<String, String> pathVariables = request.pathVariables();
        if (MapUtils.isEmpty(pathVariables)) {
            return url;
        }
        for (Map.Entry<String, String> entry : pathVariables.entrySet()) {
            final String placeholder = "{" + entry.getKey() + "}";
            final String value = StringUtils.trimToEmpty(entry.getValue());
            url = url.replace(placeholder, value);
        }
        return url;
    }
}
