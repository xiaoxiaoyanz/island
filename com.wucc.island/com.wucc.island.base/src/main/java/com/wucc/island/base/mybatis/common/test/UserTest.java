package com.wucc.island.base.mybatis.common.test;

import com.wucc.island.base.mybatis.common.bean.User;
import com.wucc.island.base.mybatis.common.mapper.UserMapper;
import com.wucc.island.base.mybatis.common.utils.MapperUtil;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-01 14:47
 */
@Slf4j
public class UserTest {
    private static Random random = new Random();


    private static User user = User.builder().id((random.nextInt()+1)<<2).username("zhangsan").password("123").lastName("aa").birthDate(new Date()).sex("1").build();

    @Test
    public void test01() throws Exception {
        MapperUtil.callMapper(UserMapper.class, mapper -> {
            return mapper.insert(user);
        });

        log.info("插入结果：{}", this.getModelById(user.getId()));
    }

    public User getModelById(Integer id) throws Exception {
        return MapperUtil.callMapper(UserMapper.class,mapper -> {
            return  mapper.queryById(id);
        });

    }
    @Test
    public void test02() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.insert(user);


    }
}
