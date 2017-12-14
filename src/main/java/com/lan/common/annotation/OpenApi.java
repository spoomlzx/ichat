package com.lan.common.annotation;

import java.lang.annotation.*;

/**
 * package com.lan.common.annotation
 * 注解公开api接口，跳过authentication
 *
 * @author lanzongxiao
 * @date 2017/10/30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OpenApi {

}
