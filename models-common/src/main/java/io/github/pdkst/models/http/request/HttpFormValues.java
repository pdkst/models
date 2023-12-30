package io.github.pdkst.models.http.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pdkst.zhang
 * @since 2023/11/03
 */
@Data
public class HttpFormValues implements Iterable<FormValue> {
    private final Map<String, FormValue> values = new LinkedHashMap<>();

    public void add(String key, String... values) {
        if (values == null) {
            return;
        }
        for (String value : values) {
            add(key, value);
        }
    }

    public void add(String key, List<String> values) {
        if (values == null) {
            return;
        }
        for (String value : values) {
            add(key, value);
        }
    }

    public void add(String name, String value) {
        final FormValue formValue = values.get(name);
        if (formValue == null) {
            values.put(name, new HttpFormValue(name, value));
        } else {
            values.put(name, formValue.append(value));
        }
    }

    @NotNull
    @Override
    public Iterator<FormValue> iterator() {
        return new Iter();
    }

    @RequiredArgsConstructor
    private class Iter implements Iterator<FormValue> {
        private final Iterator<FormValue> iterator;

        public Iter() {
            this(values.values().iterator());
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public FormValue next() {
            return iterator.next();
        }
    }
}
