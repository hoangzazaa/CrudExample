package vn.vnpt.stc.ol.apigw.configuration;

import com.google.gson.Gson;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import vn.vnpt.stc.ol.base.configuration.SpringContext;
import vn.vnpt.stc.ol.base.constants.Constants;
import vn.vnpt.stc.ol.base.event.AMQPService;
import vn.vnpt.stc.ol.base.event.EventBus;
import vn.vnpt.stc.ol.base.event.RabbitConnectionListener;
import vn.vnpt.stc.ol.base.event.amqp.AMQPEventBus;
import vn.vnpt.stc.ol.apigw.core.filter.JwtFilter;

import java.util.concurrent.Executor;

@Configuration
public class OneLinkConfiguration {

    /* ========================= RABBIT MQ ============================ */
    @Bean
    @ConfigurationProperties(prefix = "spring.rabbitmq", ignoreUnknownFields = true)
    public CachingConnectionFactory connectionFactory() {
        SpringContext.setServiceName(Constants.OLServices.NORTH_REST);
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.addConnectionListener(new RabbitConnectionListener() {});
        return cachingConnectionFactory;
    }

    @Bean
    public EventBus eventBus(CachingConnectionFactory connectionFactory, ApplicationContext ctx) {
        return new AMQPEventBus(connectionFactory, ctx);
    }

    @Bean
    public AMQPService amqpService(ApplicationContext ctx) {
        return new AMQPService(ctx);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public RedisConnectionFactory redisConnectionFactory(RedisSentinelConfiguration redisSentinelConfiguration) {
        return new JedisConnectionFactory();
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

    @Bean("taskExecutor")
    public Executor taskExecutor(ThreadPoolConfiguration threadPoolConfiguration) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadPoolConfiguration.getCoreSize());
        executor.setMaxPoolSize(threadPoolConfiguration.getMaxSize());
        executor.setQueueCapacity(threadPoolConfiguration.getQueueCapacity());
        executor.setThreadNamePrefix("apigw-");
        executor.initialize();
        return executor;
    }

}
