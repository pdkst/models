package io.github.pdkst.models.http.request;

import java.util.List;

public interface FormValue {
    String getKey();

    String getValue();

    List<String> getValues();

    FormValue append(String value);
}