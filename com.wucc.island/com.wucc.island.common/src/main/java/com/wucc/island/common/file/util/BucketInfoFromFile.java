package com.wucc.island.common.file.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class BucketInfoFromFile {
    private static final Logger LOGGER = LoggerFactory.getLogger(BucketInfoFromFile.class);
    private static final Map<String, String> bucketInfoMap = new HashMap();

    public BucketInfoFromFile() {

        if (StringUtils.isNotBlank(FileProperties.DEFAULT_BUCKET)) {
            bucketInfoMap.put("tenantBucket", FileProperties.DEFAULT_BUCKET);
            bucketInfoMap.put(FileProperties.DEFAULT_BUCKET, "Private");
        }
        if (StringUtils.isNotBlank(FileProperties.DEFAULT_BUCKET_READ)) {
            bucketInfoMap.put("tenantBucketRead", FileProperties.DEFAULT_BUCKET_READ);
            bucketInfoMap.put(FileProperties.DEFAULT_BUCKET_READ, "Read");
        }
        if (StringUtils.isNotBlank(FileProperties.DEFAULT_BUCKET_FULL)) {
            bucketInfoMap.put("tenantBucketFull", FileProperties.DEFAULT_BUCKET_FULL);
            bucketInfoMap.put(FileProperties.DEFAULT_BUCKET_FULL, "Full");
        }
    }

    public String getBucket(BucketPermission permission) {
        if (null == permission) {
            LOGGER.error("bucket permission error");
            return null;
        }
        String result = null;
        switch (permission) {
            case PRIVATE:
                result = (String)bucketInfoMap.get("tenantBucket");
                break;
            case READ:
                result = (String)bucketInfoMap.get("tenantBucketRead");
                break;
            case FULL:
                result = (String)bucketInfoMap.get("tenantBucketFull");
                break;
            default:
                result = (String)bucketInfoMap.get("tenantBucket");
        }
        if (result == null) {
            LOGGER.error("����bucket��������");
        }
        return result;
    }

    public String getBucket() {
        return getBucket(BucketPermission.PRIVATE);
    }

    public BucketPermission getPermissionByBucket(String bucketname) {
        if (bucketInfoMap.containsKey(bucketname)) {
            if ("Full".equalsIgnoreCase((String)bucketInfoMap.get(bucketname))) {
                return BucketPermission.FULL;
            }
            if ("Read".equalsIgnoreCase((String)bucketInfoMap.get(bucketname))) {
                return BucketPermission.READ;
            }
            if ("Private".equalsIgnoreCase((String)bucketInfoMap.get(bucketname))) {
                return BucketPermission.PRIVATE;
            }
        }
        return BucketPermission.PRIVATE;
    }
}