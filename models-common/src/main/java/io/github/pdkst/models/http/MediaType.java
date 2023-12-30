package io.github.pdkst.models.http;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author pdkst.zhang
 * @since 2023/10/29
 */
@Getter
@RequiredArgsConstructor
public enum MediaType {
    JSON("application/json"),
    FORM_DATA("application/x-www-form-urlencoded"),
    PARTY_FORM_DATA("multipart/form-data"),
    TEXT_PLAIN("text/plain"),
    ;
    private final String value;
}
