package cn.az.java.metrics.aop;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author az
 * @since 03/28/21 11:25
 */
@Aspect
@Component
public class DemoAspect {

    ThreadLocal<Long> startTime = ThreadLocal.withInitial(System::currentTimeMillis);

    @Resource
    private MeterRegistry meterRegistry;

    private Counter counter;

    @PostConstruct
    private void init() {
        this.counter = meterRegistry.counter("requests_total", "status", "success");
    }

    @Pointcut("execution(public * cn.az.java.metrics.controller.*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint jp) throws Throwable {
        startTime.set(System.currentTimeMillis());
        counter.increment();
    }

    @AfterReturning(returning = "returnVal", pointcut = "pointCut()")
    public void doAfterReturning(Object returnVal) {
        System.out.println("方法执行时间: " + (System.currentTimeMillis() - startTime.get()));
    }
}
