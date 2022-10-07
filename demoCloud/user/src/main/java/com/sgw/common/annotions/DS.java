package com.sgw.common.annotions;

import com.sgw.common.constants.DatasourceConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sunny
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DS {

    String source() default DatasourceConstants.WRITE_SOURCE;

}
