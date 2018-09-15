package cn.aslan.mj.common.utils;

import org.apache.commons.net.ftp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FtpUtil {

    private static Logger logger = LoggerFactory.getLogger(FtpUtil.class);

    /**
     * 保存ftp图片到本地
     * @param hostName FTP服务器IP
     * @param port FTP服务器端口
     * @param remotePath 远程路径（不含文件名）
     * @param localPath 本地路径（不含文件名）
     * @param fileName 文件名
     * @return
     */
    public static boolean downFile(String hostName, int port, String remotePath, String localPath, String fileName) {
        boolean success = false;

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(hostName,port);
            logger.info("连接到ftp服务器：" + hostName + " 成功..开始登录");
            boolean b = ftpClient.login("anonymous", "anonymous@null.com");
            logger.info("登录成功." + b);

            int reply = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return success;
            }

            ftpClient.setControlEncoding("GBK");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            FTPClientConfig conf = new FTPClientConfig();
            conf.setServerLanguageCode("zh");

            for(FTPFile ff : ftpClient.listFiles(remotePath, new FTPFileFilter() {
                @Override
                public boolean accept(FTPFile file) {
                    return file != null && file.isFile() && file.getName().equals(fileName);
                }
            })) {
                logger.info(">>>>> " + ff.getName());
                File localFile = new File(localPath + "/" + fileName);
                OutputStream is = new FileOutputStream(localFile);
                String fullName = remotePath + "/" + fileName;
                ftpClient.retrieveFile(fullName, is);
                is.close();
            }

            ftpClient.logout();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return success;
    }

}
