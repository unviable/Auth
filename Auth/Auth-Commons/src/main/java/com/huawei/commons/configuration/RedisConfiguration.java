package com.huawei.commons.configuration;

import java.lang.reflect.Method;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
/*
 * 开启redis缓存，设置key的生产策略。主要用于注解情况下，默认使用方法的全限定名作为key    自动生成key
 */
@EnableCaching
@Configuration
public class RedisConfiguration extends CachingConfigurerSupport{
     /*
	 * key的生成策略
	 */
	@Override
	public KeyGenerator keyGenerator() {
		// TODO Auto-generated method stub
		return new KeyGenerator() {
			/*
			 * 参数1：要操作的目标对象
			 * 参数2：要操作的方法
			 * 参数3：执行方法时的参数
			 */
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sBuilder = new StringBuilder();
				sBuilder.append(target.getClass().getName());
				sBuilder.append(".");
				sBuilder.append(method.getName());    //生成key
				for(Object object:params){
					sBuilder.append(object.toString());
				}
				return sBuilder.toString();
			}
		};
	}
	/**
     * retemplate相关配置
     * @param factory
     * @return
     */
	@Bean
	@SuppressWarnings("all")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template  = new RedisTemplate<>();
		//设置工厂
		template.setConnectionFactory(factory);
		//使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
		//（默认使用JDK的序列化方式）
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = 
			new Jackson2JsonRedisSerializer<>(Object.class);
		//创建对象映射
		ObjectMapper mapper = new ObjectMapper();
		//指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
		mapper.setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY);
		//指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer
		//等会抛出异常
		mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		//
		jackson2JsonRedisSerializer.setObjectMapper(mapper);
		//字符串序列化器
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		//key采用String的序列化方式
		template.setKeySerializer(stringRedisSerializer);
		//hash的key也采用String的方式
		template.setHashKeySerializer(stringRedisSerializer);
		//value采用jackson的方式
		template.setValueSerializer(jackson2JsonRedisSerializer);
		//hash的value也采用Jackson
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		
		template.afterPropertiesSet();
		return template;
	}
	/**
     * 对hash类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    /**
     * 对redis字符串类型数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * 对链表类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**
     * 对无序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /**
     * 对有序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }

}	