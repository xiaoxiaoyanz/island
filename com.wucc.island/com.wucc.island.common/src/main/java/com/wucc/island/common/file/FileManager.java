package com.wucc.island.common.file;

import com.wucc.island.common.file.client.AliOSSClient;
import com.wucc.island.common.file.client.LocalClient;
import com.wucc.island.common.file.util.BucketInfoFromFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件上传、下载实现 支持阿里云oss ,本地文件
 * <p>
 * 使用说明 ，在配置文件 application.properties 中增加以下参数
 * <p>
 * #使用哪种文件存储系统（AliOss阿里云，Local本地文件存储） storeType=AliOss/Local #使用本地文件系统时的存储路径 storeDir=/etc/filetest #使用阿里云文件系统
 * #阿里云endpoint、accessKeyId、accessKeySecret endpoint=oss-cn-shanghai.aliyuncs.com accessKeyId=LTAIWNzm5ss8GhxD
 * accessKeySecret=XATX02IwBV4CK1fSmcwJ88yRWHAU14 #默认bucket名（private权限） defaultBucket=fcfa #默认bucket名（read权限）
 * defaultBucketRead=fcfa
 */

//@Component
public class FileManager {
    public static Logger logger = LoggerFactory.getLogger(FileManager.class);
    public static BucketInfoFromFile bucketMapper = new BucketInfoFromFile();

    public static final String LOCAL = "Local";
    public static final String ALIOSS = "AliOss";

    public static String storeType;

    @Value("${storeType}")
    public void setStoreType(String storeType) {
        FileManager.storeType = storeType;
    }

    /**
     * 文件上传
     *
     * @param bucketName
     *            oss.bucket/子路径
     * @param fileName
     * @param fileContent
     * @return
     */
    public static String uploadFile(String bucketName, String fileName, byte[] fileContent) {
        String saveName = fileName;
        if (LOCAL.equalsIgnoreCase(storeType)) {
            try {
                LocalClient client = new LocalClient();
                saveName = client.upload(bucketName, saveName, fileContent);
            } catch (Exception e) {
                saveName = null;
                logger.error("本地文件长传失败", e);
            }
        } else if (ALIOSS.equalsIgnoreCase(storeType)) {
            if (bucketName == null) {
                bucketName = "";
            } else {
                bucketName = bucketName + "/";
            }
            AliOSSClient client = new AliOSSClient();
            // 当不存在的时候，创建bucket
            // if (!client.isBucketExist(bucketName)) {
            // client.createBucket(bucketName);
            // }
            try {
                saveName = client.upload(bucketMapper.getBucket(), bucketName + saveName, fileContent);
            } catch (Exception e) {
                saveName = null;
                logger.error("阿里云文件上传传失败", e);
            } finally {
                client.shutdown();
            }
        }
        return saveName;
    }

    /**
     * 文件上传,输入流
     *
     * @param bucketName
     *            oss.bucket/子路径
     * @param fileName
     * @param fileContent
     * @return
     */
    public static String uploadFile(String bucketName, String fileName, InputStream fileContent) {
        String saveName = fileName;
        if (LOCAL.equalsIgnoreCase(storeType)) {
            try {
                LocalClient client = new LocalClient();
                saveName = client.upload(bucketName, saveName, fileContent);
            } catch (Exception e) {
                saveName = null;
                logger.error("本地文件上传失败", e);
            }
        } else if (ALIOSS.equalsIgnoreCase(storeType)) {
            if (bucketName == null) {
                bucketName = "";
            } else {
                bucketName = bucketName + "/";
            }
            AliOSSClient client = new AliOSSClient();
            // 当不存在的时候，创建bucket
            // if (!client.isBucketExist(bucketName)) {
            // client.createBucket(bucketName);
            // }
            try {
                saveName = client.upload(bucketMapper.getBucket(), bucketName + saveName, fileContent);
            } catch (Exception e) {
                saveName = null;
                logger.error("阿里云文件上传传失败", e);
            } finally {
                client.shutdown();
            }
        }
        return saveName;
    }

    /**
     * 文件下载
     *
     * @param bucketName
     *            oss.bucket/子路径
     * @param fileName
     * @return
     */
    public static byte[] downLoadFile(String bucketName, String fileName) {
        byte[] contents = null;
        if (LOCAL.equalsIgnoreCase(storeType)) {
            LocalClient client = new LocalClient();
            try {
                contents = client.download(bucketName, fileName);
                if (contents == null) {
                    logger.error("文件不存在");
                }
            } catch (Exception e) {
                logger.error("文件下载失败", e);
            }
        } else if (ALIOSS.equalsIgnoreCase(storeType)) {
            if (bucketName == null) {
                bucketName = "";
            } else {
                bucketName = bucketName + "/";
            }
            AliOSSClient client = new AliOSSClient();
            try {
                contents = client.download(bucketMapper.getBucket(), bucketName + fileName);
                if (contents == null) {
                    logger.error("文件不存在");
                }
            } catch (Exception e) {
                logger.error("文件下载失败", e);
            }
            client.shutdown();
        }
        return contents;
    }

    /**
     * 文件下载 直接输出到输出流
     *
     * @param bucketName
     *            oss.bucket/子路径
     * @param fileName
     * @param out
     */
    public static void downLoadFile(String bucketName, String fileName, OutputStream out, boolean isPicture) {
        if (LOCAL.equalsIgnoreCase(storeType)) {
            LocalClient client = new LocalClient();
            try {
                client.download(bucketName, fileName, out, isPicture);
            } catch (Exception e) {
                logger.error("文件下载失败", e);
            }
        } else if (ALIOSS.equalsIgnoreCase(storeType)) {
            if (bucketName == null) {
                bucketName = "";
            } else {
                bucketName = bucketName + "/";
            }
            AliOSSClient client = new AliOSSClient();
            try {
                byte[] bytes = client.download(bucketMapper.getBucket(), bucketName + fileName);
                out.write(bytes);
            } catch (Exception e) {
                logger.error("文件下载失败", e);
            }
            client.shutdown();
        }
    }

    /**
     * 文件删除
     *
     * @param bucketName
     *            oss.bucket/子路径
     * @param fileName
     * @return
     */
    public static boolean delFile(String bucketName, String fileName) {
        boolean contents = false;
        if (LOCAL.equalsIgnoreCase(storeType)) {
            LocalClient client = new LocalClient();
            try {
                contents = client.deleteFile(bucketName, fileName);
            } catch (Exception e) {
                logger.error("文件下载失败", e);
            }
        } else if (ALIOSS.equalsIgnoreCase(storeType)) {
            if (bucketName == null) {
                bucketName = "";
            } else {
                bucketName = bucketName + "/";
            }
            AliOSSClient client = new AliOSSClient();
            try {
                contents = client.delete(bucketMapper.getBucket(), bucketName + fileName);
            } catch (Exception e) {
                logger.error("文件下载失败", e);
            }
            client.shutdown();
        }
        return contents;
    }

    public static String uploadPicture(String bucketName, String fileName, String postfix, InputStream fileContent) {
        String saveName = fileName;
        if (LOCAL.equalsIgnoreCase(storeType)) {
            try {
                LocalClient client = new LocalClient();
                saveName = client.uploadWithThumbnail(bucketName, saveName, postfix, fileContent);
            } catch (Exception e) {
                saveName = null;
                logger.error("本地文件上传失败", e);
            }
        } else if (ALIOSS.equalsIgnoreCase(storeType)) {
            if (bucketName == null) {
                bucketName = "";
            } else {
                bucketName = bucketName + "/";
            }
            AliOSSClient client = new AliOSSClient();
            // 当不存在的时候，创建bucket
            if (!client.isBucketExist(bucketName)) {
                client.createBucket(bucketName);
            }
            try {
                saveName = client.upload(bucketMapper.getBucket(), bucketName + saveName, fileContent);
            } catch (Exception e) {
                saveName = null;
                logger.error("阿里云文件上传传失败", e);
            } finally {
                client.shutdown();
            }
        }
        return saveName;
    }

    public static void downLoadFile(String bucketName, String fileName, OutputStream out) {
        downLoadFile(bucketName, fileName, out, false);
    }

    /**
     * 文件下载 直接输出到输出流
     *
     * @param bucketName
     *            oss.bucket/子路径
     * @param fileName
     * @param out
     */
    public static void downLoadPhoto(String bucketName, String fileName, OutputStream out) {
        downLoadFile(bucketName, fileName, out, true);
    }
}
