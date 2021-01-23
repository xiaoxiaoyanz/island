package com.wucc.island.common.configure;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

//@Configuration
public class DozerBeanMapperConfigure {
    //@Bean
    public DozerBeanMapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        InputStream inputStream =
            DozerBeanMapperConfigure.class.getClassLoader().getResourceAsStream("configuration/DozerMapping.xml");
        if (inputStream != null) {
            mapper.addMapping(inputStream);
        }
        return mapper;
    }
}
