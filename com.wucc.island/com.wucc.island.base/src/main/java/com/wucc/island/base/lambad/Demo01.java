package com.wucc.island.base.lambad;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.*;

@Slf4j
public class Demo01 {

    @Test
    public void test01() throws MalformedURLException, UnknownHostException {
        URL url = new URL("http://47.97.215.99:8080/aa/bb/hello.txt");

        System.out.println(url);

        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);
    }
}
