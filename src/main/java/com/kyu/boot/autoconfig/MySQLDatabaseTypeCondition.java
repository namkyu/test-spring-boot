package com.kyu.boot.autoconfig;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Project : test-spring-boot
 * @Date : 2017-10-18
 * @Author : nklee
 * @Description :
 */
public class MySQLDatabaseTypeCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String enabledDBType = conditionContext.getEnvironment().getProperty("dbType");
        if (enabledDBType != null && enabledDBType.equalsIgnoreCase("mysql")) {
            return true;
        }

        return false;
    }
}
