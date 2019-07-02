package spittr.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

@EnableCaching
@Configuration
public class CachingConfig {
	
	
/* 高版本的用法
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		
		 //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        //设置CacheManager的值序列化方式为json序列化
        RedisSerializer<Object> jsonSerializer = new GenericJackson2JsonRedisSerializer();
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
                                                    .fromSerializer(jsonSerializer);
        RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig()
                                                    .serializeValuesWith(pair);
        //设置默认超过期时间是30秒
        defaultCacheConfig.entryTtl(Duration.ofSeconds(30));
        //初始化RedisCacheManager
        return new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
	}*/
	
	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		return new RedisCacheManager(redisTemplate);
	}
	
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
		
		RedisTemplate<String, String> template = new RedisTemplate<String, String>();
		
		template.setConnectionFactory(factory);
		template.afterPropertiesSet();
		return template;
	}
	
	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		
		JedisConnectionFactory factory = new JedisConnectionFactory();
		
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxWaitMillis(5000);
		poolConfig.setMaxTotal(5);//最大连接数, 默认8个
		poolConfig.setMaxIdle(5);//最大空闲连接数, 默认8个
		
		
		factory.setPort(6379);
		factory.setHostName("127.0.0.1");
		factory.setPoolConfig(poolConfig);
		
		factory.afterPropertiesSet();//这句要放在设置参数的后面执行
		return factory;
	}
}
