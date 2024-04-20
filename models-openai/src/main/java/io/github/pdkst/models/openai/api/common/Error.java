package io.github.pdkst.models.openai.api.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pdkst
 * @since 2024/04/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    /**
     * 错误代码
     */
    private String code;
    /**
     * 错误描述
     */
    private String message;
    /**
     * 错误类型
     */
    private String type;
    /**
     * param
     */
    private String param;
}
