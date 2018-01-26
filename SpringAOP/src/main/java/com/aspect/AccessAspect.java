package com.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect  
@Component 
public class AccessAspect {
    /**@前置通知
     * 方法开始之前执行一段代码
     * @param joinPoint
     */
    @Before("execution(* com.interfaces.HelloWorldImpl1.*(..))")
    public void before(JoinPoint point) {
    	System.out.println("BEFORE-->");
        String methodName = point.getSignature().getName();
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        System.out.println(method.getName());
        Object[] args = point.getArgs();
        System.out.println("The method 【" + methodName + "】 begins with " + Arrays.asList(args));
        System.out.println();
    }

    /**@后置最终通知
     * 方法执行之后执行一段代码
     * 无论该方法是否出现异常
     * @param joinPoint
     */
    @After("execution(* com.interfaces.HelloWorldImpl1.*(..))")
    public void after(JoinPoint point) {
    	System.out.println("AFTER-->");
        String methodName = point.getSignature().getName();
        Object[] args = point.getArgs();
        System.out.println("The method 【" + methodName + "】 ends with " + Arrays.asList(args));
        System.out.println();
    }

    /**@后置返回通知
     * 方法正常结束后执行的代码，不包括抛出异常的情况
     * 返回通知是可以访问到方法的返回值的
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value="execution(* com.interfaces.HelloWorldImpl1.*(..))",returning="result")
    public void afterReturning(JoinPoint point,Object result) {
    	System.out.println("AFTERRETURNING-->");
        String methodName = point.getSignature().getName();
        System.out.println("The method 【" + methodName + "】 return with " + result);
        System.out.println();
    }

    /**@后置异常通知
     * 在方法出现异常时会执行的代码
     * 可以访问到异常对象，可以指定在出现特定异常时在执行通知代码
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value="execution(* com.interfaces.HelloWorldImpl1.*(..))", throwing="ex")
    public void afterThrowing(JoinPoint point, Exception ex) {
    	System.out.println("AFTERTHROWING-->");
        String methodName = point.getSignature().getName();
        System.out.println("The method " + methodName + " occurs exception: " + ex);
        System.out.println();
    }

    /**@环绕通知
     * 环绕通知需要携带ProceedingJoinPoint类型的参数
     * 环绕通知类似于动态代理的全过程：ProceedingJoinPoint类型的参数可以决定是否执行目标方法。
     * 而且环绕通知必须有返回值，返回值即为目标方法的返回值
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.interfaces.HelloWorldImpl1.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
    	System.out.println("ARROUND-->");
        Object result = null;
        System.out.println("target:" + point.getTarget());
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        System.out.println(method.getName());
        String methodName = point.getSignature().getName();
        //执行目标方法
        try {
            //前置通知
            System.out.println("ARROUND-->The method 【" + methodName + "】 begins with 【" + Arrays.asList(point.getArgs()) +"】");
            result = point.proceed();
        } catch (Throwable e) {
            //后置异常通知【在方法出现异常时会执行的代码】
            System.out.println("ARROUND-->The method 【" + methodName + "】 occurs expection : 【" + e +"】");
            throw new RuntimeException(e);
        }
        //后置返回通知【方法正常结束后执行的代码，不包括抛出异常的情况】
        System.out.println("ARROUND-->The method 【" + methodName + "】 return with 【" + result +"】");
        System.out.println();
        return result;
    }

}