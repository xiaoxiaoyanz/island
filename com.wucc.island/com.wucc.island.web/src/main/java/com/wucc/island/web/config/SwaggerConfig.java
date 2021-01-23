package com.wucc.island.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.ServletContext;

/**
 * swagger配置
 * 
 * @author zhoujiej
 *
 */
//@Configuration
public class SwaggerConfig {

    @Autowired
    private ServletContext servletContext;

    //@Bean
    public Docket config() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
            .apis(RequestHandlerSelectors.basePackage("com.wucc.island.controller")).paths(PathSelectors.any()).build();
        // .pathProvider(new RelativePathProvider(servletContext) {
        // @Override
        // public String getApplicationBasePath() {
        // String applicationBasePath = super
        // .getApplicationBasePath();
        // return applicationBasePath + "/swagger";
        // }
        // });
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("springboot利用swagger2构建api文档").description("转移支付政法api文档")
            .contact(new Contact("联系人", "http://www.yonyougov.com", "wudjb@yonyou.com")).version("1.0").build();
    }

    /**
     * 防止@EnableMvc把默认的静态资源路径覆盖了，手动设置的方式
     *
     * @param registry
     */
    // @Override
    // protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    // // 解决静态资源无法访问
    // registry.addResourceHandler("/**").addResourceLocations(
    // "classpath:/static/");
    // // 解决swagger无法访问
    // registry.addResourceHandler("/swagger-ui.html").addResourceLocations(
    // "classpath:/META-INF/resources/");
    // // 解决swagger的js文件无法访问
    // registry.addResourceHandler("/webjars/**").addResourceLocations(
    // "classpath:/META-INF/resources/webjars/");
    //
    // }
}