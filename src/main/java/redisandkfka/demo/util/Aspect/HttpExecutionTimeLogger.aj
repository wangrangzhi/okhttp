package redisandkfka.demo.util.Aspect;


import org.aspectj.lang.reflect.SourceLocation;

public aspect HttpExecutionTimeLogger {

    pointcut fooPC(): execution(void redisandkfka.demo.controller.allkindsofhttp
            .trafficTotalrefashEverySenondsRedisRewrite());

    before(): cflow(fooPC())  {
        SourceLocation sl = thisJoinPoint.getSourceLocation();//切面对应的代码位置
        System.out.println(sl);
    }
}
