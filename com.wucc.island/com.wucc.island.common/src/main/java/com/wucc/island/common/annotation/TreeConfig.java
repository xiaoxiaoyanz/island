package com.wucc.island.common.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
@Documented
@Inherited
public @interface TreeConfig {
    String key() default "id";

    String code() default "code";

    String title() default "name";

    String parentKey() default "parentId";

    String levelNum() default "levelNum";

    String leaf() default "leaf";

    String[] sumProperties() default {};
}
