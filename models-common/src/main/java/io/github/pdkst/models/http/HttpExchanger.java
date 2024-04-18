package io.github.pdkst.models.http;

import io.github.pdkst.models.http.listener.ServerSideEventListener;
import io.github.pdkst.models.http.request.HttpRequest;

/**
 * @author pdkst
 * @since 2023/11/18
 */
public interface HttpExchanger {
    /**
     * 处理请求
     *
     * @param request 请求
     * @return 响应
     * @throws Exception 请求引发异常
     */
    HttpResponse exchange(HttpRequest request) throws Exception;

    /**
     * 监听服务器端事件
     *
     * @param request  请求
     * @param listener 监听器
     * @throws Exception 请求引发异常
     */
    void serverSideEvent(HttpRequest request, ServerSideEventListener listener) throws Exception;

    /**
     * 添加拦截器
     *
     * @param interceptor 拦截器
     */
    void addInterceptor(Interceptor interceptor);
}
