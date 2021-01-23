package com.wucc.island.common.file.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 文件上传下载配置属性
 * 
 * @author zhoujiej
 * @date 2020/12/16
 */
//@Component
public class FileProperties {

    public static String DEFAULT_BUCKET;

    public static String DEFAULT_BUCKET_READ;

    public static String DEFAULT_BUCKET_FULL;

    @Value("${defaultBucket:}")
    public void setDefaultBucket(String defaultBucket) {
        FileProperties.DEFAULT_BUCKET = defaultBucket;
    }

    @Value("${defaultBucketRead:}")
    public void setDefaultBucketRead(String defaultBucketRead) {
        FileProperties.DEFAULT_BUCKET_READ = defaultBucketRead;
    }

    @Value("${defaultBucketFull:}")
    public void setDefaultBucketFull(String defaultBucketFull) {
        FileProperties.DEFAULT_BUCKET_FULL = defaultBucketFull;
    }

    private FileProperties() {

    }
}
