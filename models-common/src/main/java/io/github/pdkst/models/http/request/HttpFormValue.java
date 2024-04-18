package io.github.pdkst.models.http.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author pdkst
 * @since 2023/11/03
 */
@Data
@AllArgsConstructor
public class HttpFormValue implements FormValue, Iterable<HttpFormValue.Entry> {
    private final String key;
    private final List<String> values;

    public HttpFormValue(String key, String values) {
        this(key, Collections.singletonList(values));
    }

    public HttpFormValue(String key, List<String> exists, String values) {
        this(key, merge(exists, values));
    }

    public static List<String> merge(List<String> values, String value) {
        if (values.contains(value)) {
            return values;
        }
        final List<String> strings = new ArrayList<>(values);
        strings.add(value);
        return Collections.unmodifiableList(strings);
    }

    @Override
    public HttpFormValue append(String value) {
        return new HttpFormValue(key, values, value);
    }

    @Override
    public String getValue() {
        return values.get(0);
    }

    @Override
    @NotNull
    public Iterator<Entry> iterator() {
        return new Iter(key, values.iterator());
    }

    @Data
    public static class Entry {
        final String key;
        final Object value;
    }

    @Data
    public static class Iter implements Iterator<Entry> {
        private final String key;
        private final Iterator<String> valueIterator;

        @Override
        public boolean hasNext() {
            return valueIterator.hasNext();
        }

        @Override
        public Entry next() {
            return new Entry(key, valueIterator.next());
        }
    }
}
