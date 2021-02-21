package com.wucc.island.base.spring.proxy.cglibproxy;

import org.springframework.cglib.core.DefaultNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-10 09:23
 */
public class ProxyTest01 {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[]{Iservice1.class,Iservice2.class});
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println(method.getName());
                return null;
            }
        });

        enhancer.setNamingPolicy(new DefaultNamingPolicy(){
            @Override
            protected String getTag() {
                return "haha";
            }
        });

        Object proxy = enhancer.create();
        if(proxy instanceof Iservice1){
            ((Iservice1)proxy).m1();
        }

        if(proxy instanceof Iservice2){
            ((Iservice2)proxy).m2();
        }

        for (Class<?> anInterface : proxy.getClass().getInterfaces()) {
            System.out.println(anInterface);
        }

        System.out.println(proxy.getClass());

    }
}
