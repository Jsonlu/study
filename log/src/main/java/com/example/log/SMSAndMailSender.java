package com.example.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author:JsonLu
 * DateTime:2019/12/20 17:57
 * Email:jsonlu@qq.com
 * Desc:
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SMSAndMailSender {
    /*短信模板String格式化串*/
    String value() default "";

    String smsContent() default "";

    String mailContent() default "";

    /*是否激活发送功能*/
    boolean isActive() default true;

    /*主题*/
    String subject() default "";
}

