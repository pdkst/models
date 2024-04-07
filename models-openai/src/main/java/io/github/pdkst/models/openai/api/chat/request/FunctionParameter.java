package io.github.pdkst.models.openai.api.chat.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 方法参数
 *
 * @author pdkst
 * @since 2023-07-30
 */
@Data
@NoArgsConstructor
public class FunctionParameter {
    /**
     * 参数类型：
     */
    private String type;
    /**
     * 参数描述信息
     */
    private String description;
    /**
     * 参数描述信息
     */
    private List<String> enums;

    public List<String> getEnum() {
        return enums;
    }

    public void setEnum(List<String> enums) {
        this.enums = enums;
    }
}