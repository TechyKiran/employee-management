package com.example.employee.config;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.*;
import java.time.Duration;
@Configuration
public class RedisConfig {
 @Bean RedisCacheConfiguration cacheConfiguration(){
  return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10))
   .serializeValuesWith(org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
 }
}