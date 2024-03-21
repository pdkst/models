package io.github.pdkst.models.tool.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author pdkst.zhang
 * @since 2024/03/21
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Tool {
    /**
     * 方法名字
     *
     * @return 方法名
     */
    String name() default "";

    /**
     * 方法描述
     *
     * @return 方法描述
     */
    String description() default "";

}
