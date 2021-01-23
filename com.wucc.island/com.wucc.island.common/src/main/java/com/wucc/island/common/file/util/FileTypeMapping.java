package com.wucc.island.common.file.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileTypeMapping {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(FileTypeMapping.class);
	private static Properties prop = new Properties();

	static {
		InputStream in = null;
		in = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("fileMapping");
		try {
			prop.load(in);
		} catch (IOException e) {
			LOGGER.error("Fail to load fileMapping", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					LOGGER.error("Fail to Close inputStream", e);
				}
			}
		}
	}

	public static String metaType(String fileName) {
		int start = fileName.lastIndexOf(".");
		String postfix = fileName.substring(start);
		String metaType = getFileType(postfix);
		return metaType;
	}

	public static String getFileType(String filePostfix) {
		String type = prop.getProperty(filePostfix);
		if (type != null) {
			return prop.getProperty(filePostfix);
		}
		LOGGER.error("file type error");
		return null;
	}

	public static String getShortFileName(String fileName) {
		int start = fileName.lastIndexOf("_") + 1;
		String shortName = fileName.substring(start);
		return shortName;
	}
}