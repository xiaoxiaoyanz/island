package com.wucc.island.common.file.client;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;

import com.wucc.island.common.file.util.FileTypeMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * 阿里oss存储
 */
//@Component
public class AliOSSClient {
    public static Logger logger = LoggerFactory.getLogger(AliOSSClient.class);
    public static String endpoint;
    public static String accessKeyId;
    private static String accessKeySecret;
    private static String enablePostfix;

    @Value("${endpoint:}")
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Value("${accessKeyId:}")
    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    @Value("${accessKeySecret:}")
    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    @Value("${enablePostfix:}")
    public void setEnablePostfix(String enablePostfix) {
        this.enablePostfix = enablePostfix;
    }

    private OSSClient client = null;

    /**
     * 阿里云文件上传
     *
     * @param bucketName
     *            oss.bucket 不能为空
     * @param fileName
     *            文件名
     * @param fileContent
     * @return
     * @throws Exception
     */
    public String upload(String bucketName, String fileName, byte[] fileContent) throws Exception {
        if ((null == bucketName) || (null == fileName) || (null == fileContent)) {
            throw new Exception("AliOSSClient invalid arguement");
        }
        String result;
        try {
            if (this.enablePostfix.equalsIgnoreCase("false")) {
                ObjectMetadata meta = new ObjectMetadata();
                meta.setContentType(FileTypeMapping.metaType(fileName));
                meta.setContentDisposition("filename=\"" + FileTypeMapping.getShortFileName(fileName) + "\"");
                getClient().putObject(bucketName, rmPostfix(fileName), new ByteArrayInputStream(fileContent), meta);
            } else {
                ObjectMetadata meta = new ObjectMetadata();
                meta.setContentDisposition("filename=\"" + FileTypeMapping.getShortFileName(fileName) + "\"");
                getClient().putObject(bucketName, fileName, new ByteArrayInputStream(fileContent), meta);
            }
            result = fileName;
        } catch (OSSException | ClientException e) {
            result = null;
            logger.error("阿里云上传文件失败", e);
        }
        return result;
    }

    /**
     * 阿里云文件上传 输入流
     *
     * @param bucketName
     *            oss.bucket 不能为空
     * @param fileName
     *            文件名
     * @param fileContent
     * @return
     * @throws Exception
     */
    public String upload(String bucketName, String fileName, InputStream fileContent) throws Exception {
        if ((null == bucketName) || (null == fileName) || (null == fileContent)) {
            throw new Exception("AliOSSClient invalid arguement");
        }
        String result;
        try {
            if (this.enablePostfix.equalsIgnoreCase("false")) {
                ObjectMetadata meta = new ObjectMetadata();
                meta.setContentType(FileTypeMapping.metaType(fileName));
                meta.setContentDisposition("filename=\"" + FileTypeMapping.getShortFileName(fileName) + "\"");
                getClient().putObject(bucketName, rmPostfix(fileName), fileContent, meta);
            } else {
                ObjectMetadata meta = new ObjectMetadata();
                meta.setContentDisposition("filename=\"" + FileTypeMapping.getShortFileName(fileName) + "\"");
                getClient().putObject(bucketName, fileName, fileContent, meta);
            }
            result = fileName;
        } catch (OSSException | ClientException e) {
            result = null;
            logger.error("阿里云上传文件失败", e);
        }
        return result;
    }

    /**
     * 阿里云文件下载
     *
     * @param bucketName
     *            oss.bucket 不能为空
     * @param fileName
     *            文件名
     * @return
     * @throws Exception
     */
    public byte[] download(String bucketName, String fileName) throws Exception {
        if ((null == bucketName) || (null == fileName)) {
            throw new Exception("AliOSSClient invalid arguement");
        }
        if (!getClient().doesBucketExist(bucketName)) {
            return null;
        }
        byte[] fileContent = null;
        try {
            OSSObject object;
            if (this.enablePostfix.equalsIgnoreCase("false")) {
                object = getClient().getObject(new GetObjectRequest(bucketName, rmPostfix(fileName)));
            } else {
                object = getClient().getObject(new GetObjectRequest(bucketName, fileName));
            }
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = object.getObjectContent().read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            fileContent = swapStream.toByteArray();
            object.getObjectContent().close();
            swapStream.close();
        } catch (OSSException | ClientException | IOException e) {
            logger.error("阿里云下载文件失败", e);
        }
        return fileContent;
    }

    /**
     * 阿里云文件下载 直接输出到输出流
     *
     * @param bucketName
     *            oss.bucket 不能为空
     * @param fileName
     *            文件名
     * @param out
     * @throws Exception
     */
    public void download(String bucketName, String fileName, OutputStream out) throws Exception {
        if ((null == bucketName) || (null == fileName)) {
            throw new Exception("AliOSSClient invalid arguement");
        }
        if (!client.doesBucketExist(bucketName)) {
            return;
        }
        try {
            OSSObject object;
            if (this.enablePostfix.equalsIgnoreCase("false")) {
                object = getClient().getObject(new GetObjectRequest(bucketName, rmPostfix(fileName)));
            } else {
                object = getClient().getObject(new GetObjectRequest(bucketName, fileName));
            }
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = object.getObjectContent().read(buff, 0, 100)) > 0) {
                out.write(buff, 0, rc);
            }
            out.flush();
            object.getObjectContent().close();
        } catch (OSSException | ClientException | IOException e) {
            logger.error("阿里云下载文件失败", e);
        }
    }

    /**
     * 阿里云文件删除
     *
     * @param bucketName
     *            oss.bucket 不能为空
     * @param fileName
     *            文件名
     * @return
     * @throws Exception
     */
    public boolean delete(String bucketName, String fileName) throws Exception {
        if ((null == bucketName) || (null == fileName)) {
            throw new Exception("AliOSSClient invalid arguement");
        }
        if (!getClient().doesBucketExist(bucketName)) {
            return false;
        }
        boolean flag = true;
        try {
            if (this.enablePostfix.equalsIgnoreCase("false")) {
                getClient().deleteObject(bucketName, rmPostfix(fileName));
            } else {
                getClient().deleteObject(bucketName, fileName);
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public boolean isBucketExist(String bucketName) {
        boolean exists = getClient().doesBucketExist(bucketName);
        return exists;
    }

    public void createBucket(String bucketName) {
        getClient().createBucket(bucketName);
    }

    public void shutdown() {
        getClient().shutdown();
    }

    private String rmPostfix(String fileName) {
        int stop = fileName.lastIndexOf(".");
        String name;
        if (stop != -1) {
            name = fileName.substring(0, stop);
        } else {
            name = fileName;
        }
        return name;
    }

    private OSSClient getClient() {
        if (null == this.client) {
            this.client = new OSSClient(this.endpoint, this.accessKeyId, this.accessKeySecret);
        }
        return this.client;
    }

}
