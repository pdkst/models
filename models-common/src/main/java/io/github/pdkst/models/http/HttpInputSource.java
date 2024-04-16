package io.github.pdkst.models.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author pdkst
 * @since 2023/12/30
 */
public interface HttpInputSource {
    /**
     * 获取名称
     *
     * @return 参数名称
     */
    String name();

    default File file() {
        return null;
    }

    /**
     * 获取输入流
     *
     * @return 输入流
     * @throws IOException io异常
     */
    InputStream inputStream() throws IOException;
}
