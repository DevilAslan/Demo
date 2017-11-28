package cn.umbrella.conmmons.util.usual;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**
 * 匿名登录FTP
 * 
 * 使用commons的net包进行ftp链接. 相关包：commons-net-1.4.1.jar ;
 * commons-io-1.2.jar;jakarta-oro-2.0.8.jar测试通过.可以列出ftp上的文件
 * 通过把ftp服务器上的文件流连接到outSteam及可以把文件下载到本机的目录. .限制如果目录为中文则需要处理.最好使用英文文件名
 * 
 */
public class FTPUtil {

	private static String hostName = "192.168.180.69";
	private static String returnHost = "202.102.111.142";
	private static int port = 21;
	private static String userName = "jsztftp";
	private static String password = "qwer@#2014";

	// 部署到二期时的配置
	// private static String hostName = "192.168.53.62";
	// private static String returnHost = "61.160.137.140";
	// private static int port = 21;
	// private static String userName = "jszt";
	// private static String password = "jszt@#1234";

	private static FTPClient ftpClient = new FTPClient();

	static Logger logger = Logger.getLogger(FTPUtil.class);

	@SuppressWarnings("unused")
	public static String upload(String name, File file, String remoteDir)
			throws Exception {
		String returnStr = null;
		String nameString = name;
		try {

			ftpClient.connect(hostName, port);
			boolean login_result = ftpClient.login(userName, password);

			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
			}

			FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
			conf.setServerLanguageCode("zh");
			boolean changeWorkingDirectory_result = ftpClient
					.changeWorkingDirectory(remoteDir);
			// System.out.println("remoteDir:" + remoteDir +
			// "changeWorkingDirectory result: " +
			// changeWorkingDirectory_result);

			ftpClient.setBufferSize(1024);
			ftpClient.setControlEncoding("GBK");
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			FileInputStream fis = null;
			fis = new FileInputStream(file);
			boolean storeFile_result = ftpClient.storeFile(name, fis);
			// System.out.println("file name:" + name + "storeFile result: " +
			// storeFile_result);

			// returnStr = "http://" + returnHost + ":8082/jsztftpfiles/images/"
			// + remoteDir.substring(remoteDir.lastIndexOf("/") + 1) + "/" +
			// nameString;
			returnStr = "http://" + returnHost + ":8080/zthd/images/"
					+ remoteDir.substring(remoteDir.lastIndexOf("/") + 1) + "/"
					+ nameString;
			IOUtils.closeQuietly(fis);
			ftpClient.logout();
			return returnStr;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			throw e;
		} finally {
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

	}

	@SuppressWarnings("unused")
	public static String upload(String name, InputStream inputStream,
			String remoteDir) throws Exception {
		String returnStr = null;
		String nameString = name;
		try {

			ftpClient.connect(hostName, port);

			boolean login_result = ftpClient.login(userName, password);
			// System.out.println("userName: " + userName + "password: " +
			// password + " login result: " + login_result);

			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
			}

			FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
			conf.setServerLanguageCode("zh");

			boolean changeWorkingDirectory_result = ftpClient
					.changeWorkingDirectory(remoteDir);

			ftpClient.setBufferSize(1024);
			ftpClient.setControlEncoding("GBK");
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			boolean storeFile_result = ftpClient.storeFile(name, inputStream);
			// System.out.println("file name:" + name + "storeFile result: " +
			// storeFile_result);

			// returnStr = "http://" + returnHost + ":8082/jsztftpfiles/images/"
			// + remoteDir.substring(remoteDir.lastIndexOf("/") + 1) + "/" +
			// nameString;
			returnStr = "http://" + returnHost + ":8080/zthd/images/"
					+ remoteDir.substring(remoteDir.lastIndexOf("/") + 1) + "/"
					+ nameString;
			IOUtils.closeQuietly(inputStream);
			ftpClient.logout();
			return returnStr;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			throw e;
		} finally {
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

	}

	public static String upload(String name, InputStream inputStream,
			String remoteDir, String accnbr) throws Exception {
		String returnStr = null;
		try {

			ftpClient.connect(hostName, port);
			ftpClient.login(userName, password);

			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
			}

			FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
			conf.setServerLanguageCode("zh");
			if (!ftpClient.changeWorkingDirectory(remoteDir + "/" + accnbr)) {
				ftpClient.makeDirectory(remoteDir + "/" + accnbr);
				ftpClient.changeWorkingDirectory(remoteDir + "/" + accnbr);
			}

			ftpClient.setBufferSize(1024);
			// ftpClient.setControlEncoding("gb2312");
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			// ftpClient.storeFile(new
			// String(name.getBytes("GBK"),"iso-8859-1"), inputStream);
			ftpClient.storeFile(
					new String(name.getBytes("UTF-8"), "iso-8859-1"),
					inputStream);

			String nameString = name;
			// returnStr = "http://" + returnHost + ":8082/jsztftpfiles/images/"
			// + remoteDir.substring(remoteDir.lastIndexOf("/") + 1) + "/" +
			// accnbr + "/" + nameString;
			returnStr = "http://" + returnHost + ":8080/zthd/images/"
					+ remoteDir.substring(remoteDir.lastIndexOf("/") + 1) + "/"
					+ accnbr + "/" + nameString;
			IOUtils.closeQuietly(inputStream);
			ftpClient.logout();
			return returnStr;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			throw e;
		} finally {
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

	}

	public static void main(String[] args) throws Exception {

		// String url = "http://www.baidu.com";
		// int width = 300;
		// int height = 300;
		//
		// String remoteDir = "zthd/images/saomabanyewu";
		// String newFileName = "kevin5.png";
		// InputStream inputStream = MatrixToImageWriter.getImageStream(url,
		// width, height);
		// String filePath = FTPUtil.upload(newFileName,inputStream, remoteDir);
		// filePath = filePath.replace("\\", "/");
		// System.out.println(filePath);
		// http://202.102.111.142:8080/zthd/images/saomabanyewu/kevin5.png
		// String result =
		// HttpClientHelper.get3("http://202.102.111.142:8080/zthd/images/saomabanyewu/kevin5.png");
		// System.out.println(result);

		// String url =
		// "http://202.102.111.142:8080/zthd/images/saomabanyewu/kevin5.png";
		// boolean isFileExist = HttpClientHelper.isFileExist(url);
		// System.out.println(isFileExist);

		// String url =
		// "http://wapjs.189.cn/ztws/pages/smbyw/smbyw_specialProducts.html?usercode=123";
		//
		// InputStream imageStream = MatrixToImageWriter.getImageStream(url,
		// 300, 300);
		// String image_name = "123.png";
		// //部署到二期时需要更改
		// String remoteDir = "zthd/images/saomabanyewu";
		// // String remoteDir =
		// "/hxkhd/jszt/web/site/jsztftpfiles/images/saomabanyewu";
		// String dataCodeAddress = FTPUtil.upload(image_name, imageStream,
		// remoteDir);
		// System.out.println(dataCodeAddress);

	}
}