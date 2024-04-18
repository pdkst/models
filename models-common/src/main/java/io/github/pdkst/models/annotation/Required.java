package io.github.pdkst.models.annotation;

import java.lang.annotation.*;

/**
 * 标记参数为必填，not null
 *
 * @author pdkst
 * @since 2023/10/29
 */
@Inherited
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
public @interface Required {
}
