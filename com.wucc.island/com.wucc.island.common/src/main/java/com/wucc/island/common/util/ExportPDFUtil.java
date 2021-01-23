/**  
 * <p> @Title: ExportPdfUtil.java</p>
 * <p> @Package com.ufgov.pvdf.util.office.pdf</p>
 * <p> @Description: 导出PDF工具类</p>
 * <p> @Copyright: Copyright 2019 UFGOV</p>
 * <p> @Company: 北京用友政务软件有限公司</p>
 * <p> @author wangpengz  </p>
 * <p> @date 2019年3月22日 下午7:59:11</p>
 * <p> @version V8.5  </p>
 *//*

package com.wucc.island.common.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;

import com.wucc.island.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;

import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;



*/
/**
 * @ClassName: ExportPdfUtil
 * @Description: 导出PDF工具类 linux启动 /usr/bin/libreoffice6.2 --headless
 *               --accept="socket,host=127.0.0.1,port=8100;urp;"
 *               --nofirststartwizard
 * @author wangpengz
 * @date 2019年3月22日 下午7:59:11
 *//*

//@Component
@Lazy
@Slf4j
public class ExportPDFUtil {

	private static OfficeManager officeManager;

	*/
/**
	 * @Title: getOpenOfficeHome
	 * @Description: 获取libreoffice或openOffice地址 //Linux地址类似：/opt/libreoffice6.2
	 * @return
	 * @throws BillParseException
	 *//*

	private static String getOpenOfficeHome() throws Exception {
		Map<String, String> map = System.getenv();
		return map.get("LIBRE_OFFICE_URL");
	}

	*
	 * @Title: exchangeDocToPDF
	 * @Description: 导出PDF
	 * @param sourceFile
	 *            原流式文件
	 * @throws BillParseException
	 * @throws ConnectException
	// public static void exchangeDocToPDF(InputStream sourceFile, OutputStream
	// outputStream)
	// throws BillParseException, ConnectException {
	// OpenOfficeConnection connection = null;
	// try {
	// // 设置文件转换格式
	// DocumentFormat pdf = new DocumentFormat("Portable Document Format",
	// "application/pdf", "pdf");
	// pdf.setExportFilter(DocumentFamily.DRAWING, "draw_pdf_Export");
	// pdf.setExportFilter(DocumentFamily.PRESENTATION, "impress_pdf_Export");
	// pdf.setExportFilter(DocumentFamily.SPREADSHEET, "calc_pdf_Export");
	// pdf.setExportFilter(DocumentFamily.TEXT, "writer_pdf_Export");
	//
	// DocumentFormat docx = new DocumentFormat("Microsoft Word 2007 XML",
	// DocumentFamily.TEXT,
	// "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
	// "docx");
	//
	// // 如果当前端口连接不上则手动启动office转化服务
	// if (!isHostConnectable(OpenOfficeParam.URL, OpenOfficeParam.PORT)) {
	// startService();
	// }
	// // 连接到office转化服务
	// connection = new SocketOpenOfficeConnection(OpenOfficeParam.URL,
	// OpenOfficeParam.PORT);
	// connection.connect();
	//
	// DocumentConverter converter = new
	// StreamOpenOfficeDocumentConverter(connection);
	// converter.convert(sourceFile, docx, outputStream, pdf);
	//
	// } catch (Exception e) {
	// throw new BillParseException(e);
	// } finally {
	// if (connection != null) {
	// connection.disconnect();
	// }
	// }
	// }

	*/
/**
	 * @throws BillParseException
	 * @Title: startService
	 * @Description: 手动启动OpenOffice或Libreoffice服务
	 *//*

	public static void startService(int port) throws BaseException {
		try {
			log.debug("准备启动安装在{}目录下的openoffice服务....", getOpenOfficeHome());
			DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
			// 设置OpenOffice.org安装目录
			configuration.setOfficeHome(getOpenOfficeHome());
			// 设置转换端口，默认为8100
			configuration.setPortNumbers(port);
			// 设置任务执行超时为5分钟
			configuration.setTaskExecutionTimeout(1000 * 60 * 5L);
			// 设置任务队列超时为24小时
			configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);

			officeManager = configuration.buildOfficeManager();
			// 启动服务
			officeManager.start();
			log.debug("office转换服务启动成功!");
		} catch (Exception e) {
			log.debug("office转换服务启动失败!详细信息:" + e);
			officeManager = null;
			throw new BaseException("未找到转换服务");
		}
	}

	*/
/**
	 * @Title: isHostConnectable
	 * @Description: 判断端口是否可连接
	 * @param host
	 * @param port
	 * @return
	 *//*

	public static boolean isHostConnectable(String host, int port) {
		Socket socket = new Socket();
		try {
			socket.connect(new InetSocketAddress(host, port));
		} catch (IOException e) {
			log.debug("转化PDF服务未启动");
			return false;
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
*/
