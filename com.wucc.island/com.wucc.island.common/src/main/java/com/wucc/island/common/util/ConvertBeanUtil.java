package com.wucc.island.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.dozer.Mapper;
import org.springframework.stereotype.Component;

//@Component
public class ConvertBeanUtil {

    @Resource
    private Mapper mapper;

    // 维护一个本类的静态变量
    public static ConvertBeanUtil convertBeanUtil;

    // 初始化的时候，将本类中的sysConfigManager赋值给静态的本类变量
    @PostConstruct
    public void init() {
        convertBeanUtil = this;
        convertBeanUtil.mapper = this.mapper;
    }

    /**
     * List 实体类 转换器
     *
     * @param source
     *            原数据
     * @param clz
     *            转换类型
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S> List<T> convertor(List<S> source, Class<T> clz) {
        if (source == null)
            return null;
        List<T> map = new ArrayList<>();
        for (S s : source) {
            map.add(convertBeanUtil.mapper.map(s, clz));
        }
        return map;
    }

    /**
     * Set 实体类 深度转换器
     *
     * @param source
     *            原数据
     * @param clz
     *            目标对象
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S> Set<T> convertor(Set<S> source, Class<T> clz) {
        if (source == null)
            return null;
        Set<T> set = new TreeSet<>();
        for (S s : source) {
            set.add(convertBeanUtil.mapper.map(s, clz));
        }
        return set;
    }

    /**
     * 实体类 深度转换器
     *
     * @param source
     * @param clz
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S> T convertor(S source, Class<T> clz) {
        if (source == null) {
            return null;
        }
        return convertBeanUtil.mapper.map(source, clz);
    }

    public static void convertor(Object source, Object object) {
        convertBeanUtil.mapper.map(source, object);
    }

    public static <T> void copyConvertor(T source, Object object) {
        convertBeanUtil.mapper.map(source, object);
    }

}
