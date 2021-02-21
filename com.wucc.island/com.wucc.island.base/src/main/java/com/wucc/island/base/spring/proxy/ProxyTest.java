package com.wucc.island.base.spring.proxy;

import com.wucc.island.base.spring.proxy.service.IService;
import com.wucc.island.base.spring.proxy.service.ServiceA;
import com.wucc.island.base.spring.proxy.service.ServiceB;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.*;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-08 19:33
 */
public class ProxyTest {


    /*
    * @Transactional 事务管理
    * @EnableAsync  异步处理
    * @EnableCaching  缓存技术使用
    * @EnableAspectJAutoProxy 各种拦截器
    * 以上spring功能都是通过代理来实现的
    *
    * */


    /*
    * 简单代理
    * */

    public class ServiceProxy implements IService{

        //被代理对象
        private IService target;

        public ServiceProxy(IService target) {
            this.target = target;
        }

        @Override
        public void m1() {
            long startTime = System.nanoTime();
            this.target.m1();
            long endTime = System.nanoTime();
            System.out.println(this.target.getClass()+"的m1()方法耗时：" + (endTime - startTime) + "纳秒");
        }

        @Override
        public void m2() {
            long startTime = System.nanoTime();
            this.target.m2();
            long endTime = System.nanoTime();
            System.out.println(this.target.getClass()+"的m2()方法耗时：" + (endTime - startTime) + "纳秒");

        }

        @Override
        public void m3() {

            long startTime = System.nanoTime();
            this.target.m3();
            long endTime = System.nanoTime();
            System.out.println(this.target.getClass()+"的m3()方法耗时：" + (endTime - startTime) + "纳秒");
        }
    }

    @Test
    public void test01(){
        ServiceProxy serviceProxyA = new ServiceProxy(new ServiceA());
        ServiceProxy serviceProxyB = new ServiceProxy(new ServiceB());

        serviceProxyA.m1();
        serviceProxyA.m2();
        serviceProxyA.m3();

        serviceProxyB.m1();
        serviceProxyB.m2();
        serviceProxyB.m3();
    }


    /*
    * 通用代理实现有以下两种方法实现
    *   1，jdk动态代理，只能代理接口
    *   2，cglib动态代理，能给具体的类代理
    *
    * */

    //jdk动态代理，主要用到的一个类，java.lang.reflect.Proxy

    @Test
    public void test02() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        //为指定的接口创建代理类，返回代理类的class对象
        Class<IService> proxyClassOfIService = (Class<IService>) Proxy.getProxyClass(IService.class.getClassLoader(), IService.class);
        //创建代理类的处理器
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("方法名为"+proxy.getClass()+method.getName());
                return null;
            }
        };

        //创建实例
        IService iService = proxyClassOfIService.getConstructor(InvocationHandler.class).newInstance(invocationHandler);

        iService.m1();
        iService.m2();
        iService.m3();
    }

    @Test
    public void test03(){
        //创建代理类的处理器
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("方法名为"+proxy.getClass()+method.getName());

                return null;
            }
        };

        IService iService = (IService) Proxy.newProxyInstance(IService.class.getClassLoader(), new Class[]{IService.class}, invocationHandler);
        iService.m3();
        iService.m2();
    }


    /*
    * 任意方法中的耗时统计
    *
    * */
    public static class CostTimeInvoCationHandler implements InvocationHandler{

        //目标对象
        private Object target;

        public CostTimeInvoCationHandler(Object target) {
            this.target = target;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long startTime = System.nanoTime();
            Object result = method.invoke(this.target, args);
            long endTime = System.nanoTime();
            System.out.println(this.target.getClass()+"的"+method.getName()+"方法耗时：" + (endTime - startTime) + "纳秒");
            return result;
        }

        //最重要代码
        public static <T> T createProxy(Object target,Class<T> interfaceTarget){
            if(!interfaceTarget.isInterface()){
                throw new IllegalStateException(interfaceTarget.getName()+"必须是接口类型");
            }
            if (!interfaceTarget.isAssignableFrom(target.getClass())){
                throw new IllegalStateException(target.getClass().getName()+"必须是"+interfaceTarget.getName()+"的实现");
            }
            return (T)Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),new CostTimeInvoCationHandler(target));
        }
    }

    /*测试通用代理类*/
    @Test
    public void test04(){
        IService serviceA = CostTimeInvoCationHandler.createProxy(new ServiceA(), IService.class);
        IService serviceB = CostTimeInvoCationHandler.createProxy(new ServiceB(), IService.class);

        serviceA.m1();
        serviceB.m1();
    }


    /*
    * cglib代理
    *
    * */

    @Test
    public void test05(){

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ServiceA.class);

        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println(o.getClass().getName()+method.getName());
                Object result = methodProxy.invokeSuper(o, objects);
                return result;
            }
        });

        ServiceA serviceA = (ServiceA) enhancer.create();

        serviceA.m1();
    }




}
