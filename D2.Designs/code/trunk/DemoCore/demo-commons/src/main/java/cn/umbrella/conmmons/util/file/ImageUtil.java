package cn.umbrella.conmmons.util.file;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * BASE64图片转换工具类  
 *
 * @ClassName: ImageUtil  
 * @author zhou.xy
 * @date 2016年11月15日 下午5:05:36  
 *
 */
@SuppressWarnings("restriction")
public class ImageUtil {
	private static BASE64Encoder encoder = new BASE64Encoder();
	private static BASE64Decoder decoder = new BASE64Decoder();
	
	/**
	 * 将图片转换成BASE64加密字符串
	 *
	 * @Title: convertImageToByte 
	 * @param imagePath 图片路径
	 * @param format 图片格式 jpg/png/...
	 * @return String
	 */
	public static String convertImageToByte(String imagePath, String format) {
		File file = new File(imagePath);
		BufferedImage bi = null;
		ByteArrayOutputStream baos = null;
		String result = null;
		try {
			bi = ImageIO.read(file);
			baos = new ByteArrayOutputStream();
			ImageIO.write(bi, format == null ? "jpg" : format, baos);
			byte[] bytes = baos.toByteArray();
			result = encoder.encodeBuffer(bytes).trim();
		} catch (Exception e) {
			// 将图片转换成BASE64加密字符串失败
			e.printStackTrace();
		} finally {
			try {
				if(baos != null) {
					baos.close();
					baos = null;
				}
			} catch(Exception e) {
				// 关闭文件流异常
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 将图片流转换成BASE64加密字符串 
	 *
	 * @Title: convertImageStreamToByte 
	 * @param imageInputStream
	 * @param format
	 * @return 
	 * @return String
	 * @throws
	 */
	public static String convertImageStreamToByte(InputStream  imageInputStream, String format) {
		BufferedImage bi = null;
		ByteArrayOutputStream baos = null;
		String result = null;
		try {
			bi = ImageIO.read(imageInputStream);
			baos = new ByteArrayOutputStream();
			ImageIO.write(bi, format == null ? "jpg" : format, baos);
			byte[] bytes = baos.toByteArray();
			result = encoder.encode(bytes).trim();
		} catch (Exception e) {
			// 将图片流转换成BASE64加密字符串失败
			e.printStackTrace();
		} finally {
			try {
				if(baos != null) {
					baos.close();
					baos = null;
				}
			} catch (Exception e) {
				// 关闭文件流异常
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static void convertByteToImage(String base64String, String imagePath, String format) {
		byte[] bytes = null;
		ByteArrayInputStream bais = null;
		BufferedImage bi = null;
		File file = null;
		try {
			bytes = decoder.decodeBuffer(base64String);
			bais = new ByteArrayInputStream(bytes);
			bi = ImageIO.read(bais);
			file = new File(imagePath);
			ImageIO.write(bi, format == null ? "jpg" : format, file);
		} catch (Exception e) {
			// 将BASE64加密字符串转换成图片失败
			e.printStackTrace();
		} finally {
			try {
				if(bais != null) {
					bais.close();
					bais = null;
				}
			} catch (Exception e) {
				// 关闭文件流异常
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		String base64string = convertImageToByte("D:\\data\\snapshoot\\20161115\\2016111500010.png", "png");
		System.out.println(base64string);
	}
}
