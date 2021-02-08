package com.wucc.island.base.spring.aop;

import com.wucc.island.base.spring.model.UserService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.*;
import org.springframework.aop.aspectj.AspectJAfterReturningAdvice;
import org.springframework.aop.aspectj.AspectJAfterThrowingAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-04 15:17
 */
public class AopTest01 {

    @Test
    public void test01(){
        //定义目标对象
        UserService userService = new UserService();

        //创建pointcut,用来拦截userService中work方法
        Pointcut pointCut = new Pointcut(){
            @Override
            public ClassFilter getClassFilter() {
                return aClass -> UserService.class.isAssignableFrom(aClass);
            }

            @Override
            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> aClass) {
                        return method.getName().equals("work");
                    }

                    @Override
                    public boolean isRuntime() {
                        return false;
                    }

                    @Override
                    public boolean matches(Method method, Class<?> aClass, Object... objects) {
                        return false;
                    }
                };
            }
        };
        //创建一个之前操作的通知，操作是打印一句话
        MethodBeforeAdvice methodBeforeAdvice = new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] objects, Object o) throws Throwable {
                System.out.println("你好："+objects[0]);
            }
        };

        //创建顾问advisor用于组装切入点和通知
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(pointCut,methodBeforeAdvice);

        //用spring代理工厂来创建代理
        ProxyFactory proxyFactory = new ProxyFactory();

        //设置代理目标
        proxyFactory.setTarget(userService);

        //添加通知
        proxyFactory.addAdvisor(defaultPointcutAdvisor);

        //创建代理对象
        UserService proxy = (UserService) proxyFactory.getProxy();

        proxy.work("haha");
    }

    /*
    * 统计方法耗时时间
    * */
    @Test
    public void test02(){
        //定义目标对象
        UserService userService = new UserService();

        //创建pointcut,用来拦截userService中work方法
        Pointcut pointCut = new Pointcut(){
            @Override
            public ClassFilter getClassFilter() {
                return aClass -> UserService.class.isAssignableFrom(aClass);
            }

            @Override
            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> aClass) {
                        return method.getName().equals("work");
                    }

                    @Override
                    public boolean isRuntime() {
                        return false;
                    }

                    @Override
                    public boolean matches(Method method, Class<?> aClass, Object... objects) {
                        return false;
                    }
                };
            }
        };
       /* //创建一个之前操作的通知，操作是打印一句话
        MethodBeforeAdvice methodBeforeAdvice = new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] objects, Object o) throws Throwable {
                System.out.println("你好："+objects[0]);
            }
        };*/

        //创建一个统计耗时的通知
        MethodInterceptor advice = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation methodInvocation) throws Throwable {
                System.out.println("开始调用"+methodInvocation.getMethod());
                //初始时间
                long startTime = System.nanoTime();
                Object proceed = methodInvocation.proceed();
                //结束时间
                long endTime = System.nanoTime();
                System.out.println("结束调用"+methodInvocation.getMethod());
                System.out.println("耗时"+(endTime - startTime) + "纳秒");
                return proceed;
            }


        };



        //创建顾问advisor用于组装切入点和通知
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(pointCut,advice);

        //用spring代理工厂来创建代理
        ProxyFactory proxyFactory = new ProxyFactory();

        //设置代理目标
        proxyFactory.setTarget(userService);

        //添加通知
        proxyFactory.addAdvisor(defaultPointcutAdvisor);

        //创建代理对象
        UserService proxy = (UserService) proxyFactory.getProxy();

        proxy.work("haha");

    }
    /*
    * 如果名称中含有粉丝字样，则执行通知，否则不执行
    *
    * */
    @Test
    public void test03(){

        //定义目标对象
        UserService userService = new UserService();

        //创建pointcut,用来拦截userService中work方法
        Pointcut pointCut = new Pointcut(){
            @Override
            public ClassFilter getClassFilter() {
                return aClass -> UserService.class.isAssignableFrom(aClass);
            }

            @Override
            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> aClass) {
                        return method.getName().equals("work");
                    }

                    @Override
                    public boolean isRuntime() {
                        return true;
                    }

                    @Override
                    public boolean matches(Method method, Class<?> aClass, Object... objects) {
                        if(Objects.nonNull(objects) && objects.length == 1){
                            return ((String)objects[0]).contains("粉丝");
                        }
                        return false;
                    }
                };
            }
        };
        //创建一个之前操作的通知，操作是打印一句话
        MethodBeforeAdvice methodBeforeAdvice = new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] objects, Object o) throws Throwable {
                System.out.println("你好："+objects[0]);
            }
        };

        //创建顾问advisor用于组装切入点和通知
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(pointCut,methodBeforeAdvice);

        //用spring代理工厂来创建代理
        ProxyFactory proxyFactory = new ProxyFactory();

        //设置代理目标
        proxyFactory.setTarget(userService);

        //添加通知
        proxyFactory.addAdvisor(defaultPointcutAdvisor);

        //创建代理对象
        UserService proxy = (UserService) proxyFactory.getProxy();

        proxy.work("丝A");
    }

    /*
    * 方法之后打印一句话，如果有异常则打印异常信息
    * */
    @Test
    public void test04(){
        //定义目标对象
        UserService userService = new UserService();

        //创建pointcut,用来拦截userService中work方法
        Pointcut pointCut = new Pointcut(){
            @Override
            public ClassFilter getClassFilter() {
                return aClass -> UserService.class.isAssignableFrom(aClass);
            }

            @Override
            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> aClass) {
                        return method.getName().equals("work");
                    }

                    @Override
                    public boolean isRuntime() {
                        return false;
                    }

                    @Override
                    public boolean matches(Method method, Class<?> aClass, Object... objects) {
                        return false;
                    }
                };
            }
        };
       /* //创建一个之前操作的通知，操作是打印一句话
        MethodBeforeAdvice methodBeforeAdvice = new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] objects, Object o) throws Throwable {
                System.out.println("你好："+objects[0]);
            }
        };*/
        //创建一个后置通知
        AfterReturningAdviceInterceptor afterReturningAdviceInterceptor = new AfterReturningAdviceInterceptor(new AfterReturningAdvice(){

            @Override
            public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
                System.out.println("再见"+objects[0]);
            }
        });

        //创建一个后置通知
        AfterReturningAdvice advice = new AfterReturningAdvice(){
            @Override
            public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
                System.out.println("再见"+objects[0]);
            }
        };
        //如果有异常则打印异常信息



        //创建顾问advisor用于组装切入点和通知
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(pointCut,advice);

        //用spring代理工厂来创建代理
        ProxyFactory proxyFactory = new ProxyFactory();

        //设置代理目标
        proxyFactory.setTarget(userService);

        //添加通知
        proxyFactory.addAdvisor(defaultPointcutAdvisor);

        //创建代理对象
        UserService proxy = (UserService) proxyFactory.getProxy();

        proxy.work("haha");
    }




}
