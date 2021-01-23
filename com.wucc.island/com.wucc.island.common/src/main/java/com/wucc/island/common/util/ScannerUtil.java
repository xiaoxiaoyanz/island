package com.wucc.island.common.util;

import java.util.Scanner;

import com.wucc.island.common.exception.BaseException;
import org.apache.commons.lang3.StringUtils;



public class ScannerUtil {

	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotEmpty(ipt)) {
				scanner.close();
				return ipt;
			}
		}
		scanner.close();
		throw new BaseException("请输入正确的" + tip + "！");
	}
}
