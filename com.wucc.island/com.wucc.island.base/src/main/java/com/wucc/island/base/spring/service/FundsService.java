package com.wucc.island.base.spring.service;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-05 10:02
 */
public class FundsService {

    private double balance = 1000;

    public double reChange(String userName,double price){
        System.out.printf("%s提现%s%n",userName,balance);
        balance += price;
        return balance;
    }

    public double changeOut(String userName,double price){
        if(balance < price){
            throw new RuntimeException("余额不足");
        }
        System.out.printf("%s提现%s%n",userName,price);
        balance -= price;
        return  balance;
    }

    public double getBalance() {
        return balance;
    }
}
