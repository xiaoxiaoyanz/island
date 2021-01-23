package com.wucc.island.controller.helloworld;

import com.wucc.island.api.helloworld.HelloWoldServiceI;
import com.wucc.island.api.idgenerator.UidGenerateServiceI;
import com.wucc.island.common.result.BaseResultHandler;
import com.wucc.island.entity.helloworld.HelloWorld;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-01-16 11:25
 */

@Api(tags = {"helloworld"})
@RestController
@RequestMapping(value = "/helloworld")
public class HelloWorldController {


    @Autowired
    private HelloWoldServiceI helloWoldService;

    @Autowired
    private UidGenerateServiceI uidGenerateService;


    /**
     * gethelloworld
     */
    @ApiOperation(value = "获取helloworld", notes = "获取helloworld")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Object getHelloWorld() {
        HelloWorld build = HelloWorld.builder().id(uidGenerateService.getUid()).name("lisi").password("12346").description("haha").build();
        helloWoldService.save(build);
        return build;
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存helloworld", notes = "保存helloworld")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(@RequestBody HelloWorld helloWorld) {

        Long uid = uidGenerateService.getUid();
        helloWorld.setId(uid);
        helloWoldService.save(helloWorld);

        return BaseResultHandler.success(helloWoldService.getById(uid));
    }





}
