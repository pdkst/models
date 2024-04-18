package io.github.pdkst.models.tool;

import lombok.Data;

import java.util.List;

/**
 * @author pdkst
 * @since 2024/03/20
 */
@Data
public class ToolParameter {
    private Integer index;
    private String name;
    private String description;
    private String type;
    private List<String> enums;
    private boolean required;
}
