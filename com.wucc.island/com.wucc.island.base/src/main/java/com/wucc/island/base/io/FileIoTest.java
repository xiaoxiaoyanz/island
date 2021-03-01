package com.wucc.island.base.io;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-24 16:00
 */
@Slf4j
public class FileIoTest {


    @Test
    public void test01(){

        BufferedInputStream in = FileUtil.getInputStream("./haha.txt");
        BufferedOutputStream out = FileUtil.getOutputStream("classpath:haha03.txt");
        long copySize = IoUtil.copy(in, out, IoUtil.DEFAULT_BUFFER_SIZE);

    }
}
