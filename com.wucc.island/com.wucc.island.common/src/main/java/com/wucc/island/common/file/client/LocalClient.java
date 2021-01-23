package com.wucc.island.common.file.client;

import com.aliyun.oss.common.utils.IOUtils;

import com.wucc.island.common.constant.enums.PictureSizeEnum;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * 本地文件存储实现
 */
//@Component
public class LocalClient {
    public static final Logger logger = LoggerFactory.getLogger(LocalClient.class);
    private static String THUMBNAIL_CONNECTOR = "_";
    public static String StoreDir;
    public static String pictureDir;
    private static String enablePostfix;

    @Value("${enablePostfix}")
    public void setEnablePostfix(String enablePostfix) {
        this.enablePostfix = enablePostfix;
    }

    @Value("${storeDir}")
    public void setStoreDir(String storeDir) {
        StoreDir = storeDir + File.separator;
    }

    @Value("${pictureDir}")
    public void setPictureDir(String pictureDir) {
        this.pictureDir = pictureDir + File.separator;
    }

    /**
     * 本地文件上传
     *
     * @param bucketName
     *            子路径 （可以为空）
     * @param fileName
     *            存储文件名
     * @param fileContent
     *            内容
     * @return
     * @throws Exception
     */
    public String upload(String bucketName, String fileName, byte[] fileContent) throws Exception {
        if ((null == fileName) || (null == fileContent)) {
            throw new Exception("LocalClient invalid arguement");
        }
        String basePath = StoreDir;
        if (StringUtils.isNotBlank(bucketName)) {
            basePath = basePath + bucketName.trim() + File.separator;
        }
        File fileDir = new File(basePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        String savePath =
            fileDir + File.separator + (enablePostfix.equalsIgnoreCase("false") ? rmPostfix(fileName) : fileName);
        File file = new File(savePath);
        FileUtils.writeByteArrayToFile(file, fileContent);
        return fileName;
    }

    /**
     * 本地文件上传
     *
     * @param bucketName
     *            子路径 （可以为空）
     * @param fileName
     *            存储文件名
     * @param fileContent
     *            输入流
     * @return
     * @throws Exception
     */
    public String upload(String bucketName, String fileName, InputStream fileContent) throws Exception {
        if ((null == fileName) || (null == fileContent)) {
            throw new Exception("LocalClient invalid arguement");
        }
        String basePath = StoreDir;
        if (StringUtils.isNotBlank(bucketName)) {
            basePath = basePath + bucketName.trim() + File.separator;
        }
        File fileDir = new File(basePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        String savePath =
            fileDir + File.separator + (enablePostfix.equalsIgnoreCase("false") ? rmPostfix(fileName) : fileName);;
        File file = new File(savePath);
        FileUtils.writeByteArrayToFile(file, IOUtils.readStreamAsByteArray(fileContent));
        return fileName;
    }

    /**
     * 本地文件下载
     *
     * @param bucketName
     *            子路径 （可以为空）
     * @param fileName
     *            文件名
     * @return
     * @throws Exception
     */
    public byte[] download(String bucketName, String fileName) throws Exception {
        if (null == fileName) {
            throw new Exception("LocalClient invalid arguement");
        }
        String basePath = StoreDir;
        if (StringUtils.isNotBlank(bucketName)) {
            basePath = basePath + bucketName.trim() + File.separator;
        }
        File file = new File(basePath + (enablePostfix.equalsIgnoreCase("false") ? rmPostfix(fileName) : fileName));
        if (!file.exists()) {
            throw new Exception("File is not found " + file.getName());
        }
        long length = file.length();
        if (length > 2147483647L) {
            throw new IOException("File is to large " + file.getName());
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] bytes = IOUtils.readStreamAsByteArray(in);
            return bytes;
        } catch (IOException e) {
            logger.error("本地文件系统下载异常", e);
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("本地文件系统关闭IO异常", e);
                }
            }
        }
    }

    /**
     * 本地文件下载 直接输出到输出流
     *
     * @param bucketName
     *            子路径 （可以为空）
     * @param fileName
     *            文件名
     * @param out
     * @throws Exception
     */
    public void download(String bucketName, String fileName, OutputStream out, boolean isPicture) throws Exception {
        if (null == fileName) {
            throw new Exception("LocalClient invalid arguement");
        }
        String basePath = StoreDir;
        if (isPicture) {
            basePath = pictureDir;
        }
        if (StringUtils.isNotBlank(bucketName)) {
            basePath = basePath + bucketName.trim() + File.separator;
        }
        File file = new File(basePath + (enablePostfix.equalsIgnoreCase("false") ? rmPostfix(fileName) : fileName));
        if (!file.exists()) {
            throw new Exception("File is not found " + file.getName());
        }
        long length = file.length();
        if (length > 2147483647L) {
            throw new IOException("File is to large " + file.getName());
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            org.apache.commons.io.IOUtils.write(IOUtils.readStreamAsByteArray(in), out);
            out.flush();
        } catch (IOException e) {
            logger.error("本地文件系统下载异常", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("本地文件系统关闭IO异常", e);
                }
            }
        }
    }

    /**
     * 文件删除
     *
     * @param bucketName
     *            子路径 （可以为空）
     * @param fileName
     *            文件名
     * @return
     * @throws Exception
     */
    public boolean deleteFile(String bucketName, String fileName) throws Exception {
        if (null == fileName) {
            throw new Exception("LocalClient invalid arguement");
        }
        String basePath = StoreDir;
        if (StringUtils.isNotBlank(bucketName)) {
            basePath = basePath + bucketName.trim() + File.separator;
        }
        File file = new File(basePath + (enablePostfix.equalsIgnoreCase("false") ? rmPostfix(fileName) : fileName));
        boolean flag = false;
        if ((file.isFile()) && (file.exists())) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    public File getFile(String bucketName, String fileName) throws Exception {
        if (null == fileName) {
            throw new Exception("LocalClient invalid arguement");
        }
        String basePath = StoreDir;
        if (StringUtils.isNotBlank(bucketName)) {
            basePath = basePath + bucketName.trim() + File.separator;
        }
        File file = new File(basePath + (enablePostfix.equalsIgnoreCase("false") ? rmPostfix(fileName) : fileName));
        if (!file.exists()) {
            throw new Exception("File is not found " + file.getName());
        }
        long length = file.length();
        if (length > 2147483647L) {
            throw new IOException("File is to large " + file.getName());
        }
        return file;
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

    /**
     * 上传的同时生成缩略图
     * 
     * @param bucketName
     * @param fileName
     * @param postfix
     * @param fileContent
     * @return
     * @throws Exception
     */
    public String uploadWithThumbnail(String bucketName, String fileName, String postfix, InputStream fileContent)
        throws Exception {
        if ((null == fileName) || (null == fileContent)) {
            throw new Exception("LocalClient invalid arguement");
        }
        String basePath = pictureDir;
        if (StringUtils.isNotBlank(bucketName)) {
            basePath = basePath + bucketName.trim() + File.separator;
        }
        File fileDir = new File(basePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        String savePath =
            fileDir + File.separator + (enablePostfix.equalsIgnoreCase("false") ? rmPostfix(fileName) : fileName);;
        File file = new File(savePath);
        FileUtils.writeByteArrayToFile(file, IOUtils.readStreamAsByteArray(fileContent));

        // 生成缩略图
        String tThumbnailFilePath = fileDir + File.separator + fileName;
        toThumbnail(tThumbnailFilePath, postfix);
        return fileName;
    }

    private static void toThumbnail(String filePath, String postfix) throws IOException {
        PictureSizeEnum[] values = PictureSizeEnum.values();
        for (PictureSizeEnum pictureSizeEnum : values) {
            Thumbnails.of(filePath).size(pictureSizeEnum.width(), pictureSizeEnum.height()).keepAspectRatio(false)
                .toFile(filePath + THUMBNAIL_CONNECTOR + pictureSizeEnum.toSize() + postfix);
        }

    }
}
