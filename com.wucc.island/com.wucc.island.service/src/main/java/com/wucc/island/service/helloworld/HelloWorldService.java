package com.wucc.island.service.helloworld;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wucc.island.api.helloworld.HelloWoldServiceI;
import com.wucc.island.entity.helloworld.HelloWorld;
import com.wucc.island.repository.helloworld.HelloWorldMapper;
import org.springframework.stereotype.Service;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-01-16 11:45
 */
@Service
public class HelloWorldService extends ServiceImpl<HelloWorldMapper,HelloWorld> implements HelloWoldServiceI {
}
