/**
 *
 */
package com.tykon.api.framework.service.core.app;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author sachin
 *
 */
@EntityScan(basePackages = { "com.hp" })
@EnableJpaRepositories(basePackages = { "com.hp" })
@EnableMongoRepositories(basePackages = { "com.hp" })
@ComponentScan(basePackages = { "com.hp" })
@EnableRedisRepositories(basePackages = "com.hp.api.framework.redis.repository")
@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableCaching
@EnableAsync(proxyTargetClass = true)
@PropertySource("classpath:common.properties")
@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
@PropertySource(value = "file:/opt/context/external.properties", ignoreResourceNotFound = true)

@EnableSwagger2
public class BaseApplication {
	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("hp-user").apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("HELLO PARENT - 1.2 REST API").description("HELLO PARENT - 1.2 REST API")
				// .termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
				.contact("Amlesh Sinha")
				// .license("Apache License Version 2.0")
				// .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
				.version("1.2").build();
	}

	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(8);
		pool.setMaxPoolSize(100);
		pool.setWaitForTasksToCompleteOnShutdown(true);
		return pool;
	}

	@Bean(destroyMethod = "shutdown")
	public RedissonClient redisson(@Value("classpath:/redisson.yaml") InputStream configFile) throws IOException {
		System.out.println("initiating redisson");
		Config config = Config.fromYAML(configFile);
		RedissonClient redissonClient = Redisson.create(config);
		System.out.println("completed initiating redisson");
		return redissonClient;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new CommonsMultipartResolverMine();
	}

	public static class CommonsMultipartResolverMine extends CommonsMultipartResolver {

		@Override
		public boolean isMultipart(HttpServletRequest request) {
			final String header = request.getHeader("Content-Type");
			if (header == null) {
				return false;
			}
			return header.contains("multipart/form-data");
		}

	}

	/**
	 * creating bean of rest template to autowire
	 * 
	 * @return
	 */
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public MappingMongoConverter mappingMongoConverter(MongoDbFactory factory, MongoMappingContext context) {
		DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
		HPMappingMongoConverter mappingConverter = new HPMappingMongoConverter(dbRefResolver,
				new MongoMappingContext());
		mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
		return mappingConverter;
	}
}
