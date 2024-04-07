package io.github.pdkst.models.openai.api.chat.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 方法参数列表
 *
 * @author pdkst
 * @since 2023-07-30
 */
@Data
@NoArgsConstructor
public class FunctionParameters {
    /**
     * 参数类型
     */
    private String type;
    /**
     * 参数列表
     */
    private Map<String, FunctionParameter> properties;
    /**
     * 必填参数
     */
    private List<String> required;
}