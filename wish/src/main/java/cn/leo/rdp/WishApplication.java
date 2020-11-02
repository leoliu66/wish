package cn.leo.rdp;

import org.mybatis.spring.annotation.MapperScan;
import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.redisson.spring.session.config.EnableRedissonHttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * 程序入口
 *
 * @author 刘露leo
 * <p>
 * 2020年10月18日下午2:00:25
 */

@SpringBootApplication
@MapperScan({"cn.leo.**.dao","cn.leo.rdp.wish.dao"})
@ComponentScan(basePackages = {"cn.leo"})
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySource(value = {"classpath:/wish.properties"})
@EnableRedissonHttpSession(maxInactiveIntervalInSeconds = 2 * 60 * 60, keyPrefix = "rdp")
@RestController
@EnableCaching
public class WishApplication extends SpringBootServletInitializer {

    /**
     * 启动服务main
     */
    public static void main(String[] args) {
        SpringApplication.run(WishApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WishApplication.class);
    }

    /**
     * 适配Spring Cache
     */
    @Autowired
    private RedissonClient redissonClient;

    @Bean
    protected CacheManager cacheManager() {
        return new RedissonSpringCacheManager(redissonClient);
    }

}
