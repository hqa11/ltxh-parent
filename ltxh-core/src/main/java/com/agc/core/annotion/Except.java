package com.agc.core.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在basedao的更新中,将不会再更新此字段
 * @author Administrator
 *
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Except {

}
