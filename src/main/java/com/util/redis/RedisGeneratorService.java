package com.util.redis;


import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public abstract class RedisGeneratorService<K extends Serializable, V extends Serializable>
{
  @Autowired
  protected RedisTemplate<K, V> txRedisTemplate;
  
  public void setTxRedisTemplate(RedisTemplate<K, V> txRedisTemplate)
  {
    this.txRedisTemplate = txRedisTemplate;
  }
  
  protected RedisSerializer<String> getRedisSerializer()
  {
    return this.txRedisTemplate.getStringSerializer();
  }
}
