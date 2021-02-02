package com.wucc.island.base.mybatis.common.utils;

import com.wucc.island.base.mybatis.common.bean.User;
import com.wucc.island.base.mybatis.common.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
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
 * @date 2021-02-01 11:03
 */
@Slf4j
public class MapperUtil {

   private final static SqlSessionFactory sqlSessionFactory= build();

   public static SqlSessionFactory build(){
       try {
           return new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
       } catch (IOException e) {
           log.error(e.getMessage(),e);
           throw new RuntimeException(e);
       }
   }

   @FunctionalInterface
   public interface SessionCall<O>{
       O call(SqlSession sqlSession) throws Exception;
   }

   @FunctionalInterface
   public interface MapperCall<T,O>{
       O call(T mapper) throws Exception;
   }

   public static <T,O> O callMapper(Class<T> tClass,MapperCall<T,O> mapper) throws Exception {

       return call(sqlSession -> mapper.call(sqlSession.getMapper(tClass)));

   }

    //此方法的参数是 SessionCall<O>
    /*
    * 当调用
    * */
   public static <O> O call(SessionCall<O> sessionCall) throws Exception {
       try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){
           return sessionCall.call(sqlSession);
       }
   }

    public static void main(String[] args) throws Exception {
        User user = User.builder().username("路人甲Java").build();
        MapperUtil.callMapper(UserMapper.class, mapper -> { mapper.insert(user); return null; });
    }








}
