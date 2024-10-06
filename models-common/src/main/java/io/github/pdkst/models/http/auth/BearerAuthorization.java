package io.github.pdkst.models.http.auth;

import io.github.pdkst.models.http.Authorization;
import io.github.pdkst.models.http.request.HttpRequest;
import lombok.RequiredArgsConstructor;

/**
 * @author pdkst
 * @since 2024/10/06
 */
@RequiredArgsConstructor
public class BearerAuthorization implements Authorization {
    private final String token;

    @Override
    public void authorize(HttpRequest request) {
        request.header("Authorization", "Bearer " + token);
    }

    public static BearerAuthorization of(String token) {
        return new BearerAuthorization(token);
    }
}
