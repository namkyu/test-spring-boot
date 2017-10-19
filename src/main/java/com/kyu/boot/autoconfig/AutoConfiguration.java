package com.kyu.boot.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

// 자동 설정 기능 모듈
@Configuration
public class AutoConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    @Conditional(MySQLDatabaseTypeCondition.class)
    public UserDAO jdbcUserDAO() {
        return new JdbcUserDAO();
    }

    @Bean
    @Conditional(MongoDatabaseTypeCondition.class)
    public UserDAO mongoUserDAO() {
        return new MongoUserDAO();
    }

    @Bean
    @ConditionalOnMissingBean(name = "keyboard")
    public String keyboard() {
        return "LG";
    }

    @Bean(name = "myDataSource")
    @ConditionalOnProperty(name = "usemysql", havingValue = "local")
    public String dataSource() {
        return environment.getProperty("usemysql");
    }
}
