package com.wucc.island.common.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.support.PropertiesLoaderSupport;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 获取系统properties文件参数工具类
 *
 * @author peak.edge
 * @date Dec 13, 2016
 */
public class ContextPropertiesUtils {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private PropertyPlaceholderConfigurer propertyConfigurer;

    public String getProperty(String key) {
        try {
            Method mergeProperties = PropertiesLoaderSupport.class.getDeclaredMethod("mergeProperties");
            mergeProperties.setAccessible(true);
            Properties props = (Properties) mergeProperties.invoke(propertyConfigurer);
            return props.getProperty(key, "");
        } catch (Exception e) {
        }
        return "";
    }

    public void setPropertyConfigurer(
            PropertyPlaceholderConfigurer propertyConfigurer) {
        this.propertyConfigurer = propertyConfigurer;
    }

}
