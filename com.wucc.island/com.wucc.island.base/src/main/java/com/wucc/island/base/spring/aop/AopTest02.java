package com.wucc.island.base.spring.aop;

import com.wucc.island.base.spring.service.FundsService;
import org.junit.Test;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-05 10:09
 */
public class AopTest02 {


    /*
    * 只有username为cc时可以访问
    * */
    @Test
    public void test02(){
        ProxyFactory proxyFactory = new ProxyFactory(new FundsService());

        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                String userName = (String) args[0];
                if(!userName.equals("cc")){
                    throw new RuntimeException("非法访问");
                }
            }
        });

        FundsService fundsService = (FundsService)proxyFactory.getProxy();

        double cc = fundsService.changeOut("cc", 100);

        double haha = fundsService.changeOut("haha", 100);
    }

    /*
    * 通过通知来捕获所有方法运行的异常
    * */
    public static class SendMegThrowsAdvice implements ThrowsAdvice{

        //注意方法名称必须为afterThrowing
        public void afterThrowing(Method method,Object[] args,Object target,RuntimeException e){
            System.out.println("异常警报");
            System.out.println(String.format("method:[%s]，args:[%s]", method.toGenericString(), Arrays.stream(args).collect(Collectors.toList())));
            System.out.println(e.getMessage());
            System.out.println("请尽快修复");
        }

    }

    @Test
    public void test03(){

        ProxyFactory proxyFactory = new ProxyFactory(new FundsService());
        proxyFactory.addAdvice(new SendMegThrowsAdvice());
        FundsService fundsService = (FundsService)proxyFactory.getProxy();

        double cc = fundsService.changeOut("cc", 2000);

        double haha = fundsService.changeOut("haha", 1000);
    }





}
