package com.wucc.island.web.config;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.config.GlobalConfig.DbConfig;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

// import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;

/**
 * mybatis配置类
 * 
 * @author zhoujiej
 * @date 2020/05/30
 */
@Configuration
public class MybatisConfig {

    /**
     * 因为多数源（暂时不知道是哪儿引入的），所以此处得重新配置， 且必须用MybatisSqlSessionFactoryBean才能扫描到mybatis-plus原生方法
     * 
     * @param driverName
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Value("${spring.datasource.driverClassName}") String driverName,
        DataSource dataSource) throws Exception {
        String dbType = "";
        if (driverName != null && !"".equals(driverName) && !"".equals("${spring.datasource.driverClassName}")) {
            if (driverName.indexOf("oracle") >= 0 || driverName.indexOf("dm") >= 0
                || driverName.indexOf("gauss") >= 0) {
                dbType = "oracle";
            } else if (driverName.indexOf("mysql") >= 0) {
                dbType = "mysql";
            } else if (driverName.indexOf("postgresql") >= 0) {
                dbType = "postgresql";
            }
        }
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        GlobalConfig globalConfig = new GlobalConfig();
        DbConfig dbConfig = new DbConfig();
        dbConfig.setUpdateStrategy(FieldStrategy.IGNORED);
        globalConfig.setDbConfig(dbConfig);
        bean.setGlobalConfig(globalConfig);

        // 配置插件
        PaginationInterceptor paginationInterceptor = paginationInterceptor();
        paginationInterceptor.setDbType(DbType.getDbType(dbType));
        bean.setPlugins(optimisticLockerInterceptor(), paginationInterceptor);

        bean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
        Resource[] databaseResource = pathResolver.getResources("classpath*:/mappings/" + dbType + "/**/*.xml");
        List<Resource> resourceList = new ArrayList<Resource>();
        List<String> nonredundantResourcelist = new ArrayList<String>();
        for (Resource resource : databaseResource) {
            String relativePath = resource.getURL().toString().split("mappings")[1];
            if (!nonredundantResourcelist.contains(relativePath)) {
                nonredundantResourcelist.add(relativePath);
                resourceList.add(resource);
            }
        }
        Resource[] resources = resourceList.toArray(new Resource[0]);
        bean.setMapperLocations(resources);
        // mybatis 需要注意的点 MyBatis 插入空值时，需要指定JdbcType
        bean.getObject().getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        return bean.getObject();
    }

    /**
     * 乐观锁插件
     */
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 分页插件
     * 
     * @return
     */
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求 默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }

}
