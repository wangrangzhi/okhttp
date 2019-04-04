package redisandkfka.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@EnableScheduling
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@EnableFeignClients
public class RedisandkfkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisandkfkaApplication.class, args);
    }

}
