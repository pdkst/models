package io.github.pdkst.models.openai.sample.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author pdkst.zhang
 * @since 2024/01/01
 */
@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, HttpRequest request) {
        log.info("exceptionHandler => handle exception;url={}", request.getURI(), e);
        return e.getMessage();
    }
}
