package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.List;

public class RedisDemo {

    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6479, 3000, null);

        try(Jedis resource = jedisPool.getResource()) {
            System.out.println(resource.set("a", "hello"));
            System.out.println(resource.get("a"));

            final Pipeline pipelined = resource.pipelined();
            for (int i = 0; i < 10; i++) {
                pipelined.incr("pipelinekey");
                pipelined.set("sgw" + i, "sgw");
            }
            final List<Object> objects = pipelined.syncAndReturnAll();
            objects.forEach(System.out::println);
        }
    }

}
