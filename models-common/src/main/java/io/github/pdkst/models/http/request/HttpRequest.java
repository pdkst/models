package io.github.pdkst.models.http.request;

import io.github.pdkst.models.http.HttpInputSource;
import io.github.pdkst.models.http.MediaType;
import io.github.pdkst.models.http.input.FileInputSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.ObjectUtils;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * http request object
 *
 * @author pdkst
 * @since 2023/10/29
 */
@Data
@Accessors(fluent = true, chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class HttpRequest {
    /**
     * http method
     */
    private HttpMethod method;
    /**
     * urls path
     */
    private String url;
    /**
     * content type: eg: application/json
     */
    private MediaType contentType = MediaType.JSON;
    /**
     * header values
     */
    private Map<String, String> header = new LinkedHashMap<>();
    /**
     * query values
     */
    private Map<String, String> query = new LinkedHashMap<>();
    /**
     * path variables
     */
    private Map<String, String> pathVariables = new LinkedHashMap<>();
    /**
     * form values
     */
    private HttpFormValues form = new HttpFormValues();
    /**
     * json Object, mapping by json mapper.
     */
    private Object json;
    /**
     * file upload
     */
    private HttpInputSource formInputSource;

    public static HttpRequest create() {
        return new HttpRequest();
    }

    public static HttpRequest get(String url) {
        return new HttpRequest().method(HttpMethod.GET).url(url);
    }

    public static HttpRequest post(String url) {
        return new HttpRequest().method(HttpMethod.POST).url(url);
    }

    public static HttpRequest post(String url, Object json) {
        return new HttpRequest().method(HttpMethod.POST).json(json).url(url);
    }

    public static HttpRequest post(String url, File file) {
        final FileInputSource formInputSource = new FileInputSource(file);
        return post(url, formInputSource);
    }

    private static HttpRequest post(String url, HttpInputSource formInputSource) {
        return new HttpRequest().method(HttpMethod.POST).formInputSource(formInputSource).url(url);
    }

    public static HttpRequest put(String url) {
        return new HttpRequest().method(HttpMethod.PUT).url(url);
    }

    public static HttpRequest put(String url, Object json) {
        return new HttpRequest().method(HttpMethod.PUT).json(json).url(url);
    }

    public static HttpRequest delete(String url) {
        return new HttpRequest().method(HttpMethod.DELETE).url(url);
    }


    public HttpRequest form(String key, String... values) {
        form.add(key, values);
        return this;
    }

    public HttpRequest form(String key, List<String> values) {
        form.add(key, values);
        return this;
    }

    public HttpRequest query(String key, Object value) {
        query.put(key, ObjectUtils.defaultIfNull(value, "").toString());
        return this;
    }

    public HttpRequest header(String key, String value) {
        header.put(key, value);
        return this;
    }

    public HttpRequest file(File file) {
        this.formInputSource = new FileInputSource(file);
        return this;
    }
}
