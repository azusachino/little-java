package cn.az.boot.aop.aspect;

import cn.az.boot.aop.annotation.DoSomething;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author az
 * @since 2020-04-20
 */
@Slf4j
@Aspect
public class DoSomethingAspect {

    @Pointcut(value = "@annotation(cn.az.boot.aop.annotation.DoSomething)")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before(JoinPoint jp) throws ClassNotFoundException {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        DoSomething doSomething = signature.getMethod().getAnnotation(DoSomething.class);
        String before = doSomething.before();
        Class<?> clazz = ClassUtils.forName(before, ClassUtils.getDefaultClassLoader());
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        doLog(point, System.currentTimeMillis());
        Object result = point.proceed();
        doLog(point, System.currentTimeMillis());
        return result;
    }

    private void doLog(ProceedingJoinPoint point, Long time) {
        Signature signature = point.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            DoSomething doSomething = method.getAnnotation(DoSomething.class);
            if (Objects.nonNull(doSomething)) {
                log.info("before: " + doSomething.before() + " after: " + doSomething.after());
            }
            // modified class name
            String className = point.getTarget().getClass().getName();
            // modified method name
            String methodName = signature.getName();
            log.info(className + "." + methodName + "()");
            // 请求的方法参数值
            Object[] args = point.getArgs();
            // 请求的方法参数名称
            LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
            String[] paramNames = u.getParameterNames(method);
            if (args != null && paramNames != null) {
                StringBuilder params = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    params.append("  ").append(paramNames[i]).append(": ").append(args[i]);
                }
                log.info(params.toString());
            }
        }
    }
}
