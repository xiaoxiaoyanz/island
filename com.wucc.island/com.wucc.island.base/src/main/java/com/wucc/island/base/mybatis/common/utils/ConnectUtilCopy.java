package com.wucc.island.base.mybatis.common.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-01 14:36
 */
public class ConnectUtilCopy {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            sqlSessionFactory = build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory build() throws IOException {
        return new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
    }


    @FunctionalInterface
    public interface CallSession<O>{
        O call(SqlSession sqlSession) throws Exception;
    }

    @FunctionalInterface
    public interface CallMapper<T,O>{
        O call(T mapper) throws Exception;
    }


    public static <O>  O call(CallSession<O> callSession) throws Exception{
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
            return callSession.call(sqlSession);
        }
    }


    public static <T,O> O callMapper(Class<T> tClass,CallMapper<T,O> callMapper) throws Exception {
        return call(sqlSession -> callMapper.call(sqlSession.getMapper(tClass)));
    }
}
