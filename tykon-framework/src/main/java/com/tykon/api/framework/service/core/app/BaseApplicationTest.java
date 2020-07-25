package com.tykon.api.framework.service.core.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan(basePackages = { "com.hp" })
@EnableJpaRepositories(basePackages = { "com.hp" })
@EnableMongoRepositories(basePackages = { "com.hp" })
@ComponentScan(basePackages = {"com.hp"})
@EnableRedisRepositories(basePackages = "com.hp.api.framework.redis.repository")
@Configuration
@EnableTransactionManagement
@EnableAsync(proxyTargetClass=true)
@PropertySource("classpath:common.properties")
@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
public class BaseApplicationTest extends BaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseApplicationTest.class, args);
	}

}
