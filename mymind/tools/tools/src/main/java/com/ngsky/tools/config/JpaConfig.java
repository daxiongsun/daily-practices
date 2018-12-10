package com.ngsky.tools.config;

import com.ngsky.tools.expandjpa.ExpandJpaRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <dl>
 * <dt>JpaConfig</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 10/30/2018 11:11 PM</dd>
 * </dl>
 *
 * @author daxiong
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = "com.ngsky.tools.repository", repositoryFactoryBeanClass = ExpandJpaRepositoryFactoryBean.class)
@EntityScan(basePackages = "com.ngsky.tools.domain")
@Configuration
public class JpaConfig {
    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}