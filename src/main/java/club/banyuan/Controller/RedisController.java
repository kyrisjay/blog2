package club.banyuan.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;



public class RedisController {
    @Autowired
    JedisConnectionFactory jedisConnectionFactory;

    void SetInRedis() {
        RedisConnection connection =  jedisConnectionFactory.getConnection();
    }
}
