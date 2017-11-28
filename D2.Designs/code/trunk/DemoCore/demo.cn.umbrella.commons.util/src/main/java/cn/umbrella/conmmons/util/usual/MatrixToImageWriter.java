package cn.umbrella.conmmons.util.usual;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.awt.image.BufferedImage;

public final class MatrixToImageWriter {

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	static Logger logger = Logger.getLogger(MatrixToImageWriter.class);
	
	private MatrixToImageWriter() {
	}

	public static void main(String[] args) {
		try {
			String url = "http://www.baidu.com";  
			int width = 300;  
			int height = 300;  
			MatrixToImageWriter.toBufferedImage(url, width, height);
		} catch (WriterException e) {
			e.printStackTrace();
		}  
	}
	
	public static InputStream getImageStream(String url, int width, int height) throws Exception { 
		
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
		InputStream is = null;
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints); 
			BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
			bufferedImage.flush(); 
			
			ByteArrayOutputStream bs = new ByteArrayOutputStream();  
			ImageOutputStream imOut = ImageIO.createImageOutputStream(bs); 
			ImageIO.write(bufferedImage, "png",imOut); 
			is = new ByteArrayInputStream(bs.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			throw e;
		} 
        return is; 
        
    } 
	
	public static InputStream getImageStreamFromBufferedImage(BufferedImage bufferedImage) throws IOException  { 
		
		try {
			bufferedImage.flush(); 
			
			ByteArrayOutputStream bs = new ByteArrayOutputStream();  
			ImageOutputStream imOut = ImageIO.createImageOutputStream(bs); 
			ImageIO.write(bufferedImage, "png",imOut); 
			InputStream is = new ByteArrayInputStream(bs.toByteArray());
			return is;
		} catch (IOException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			throw e;
		}
        
    } 
	
	public static BufferedImage toBufferedImage(String url, int width, int height) throws WriterException {
		try {
			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
			BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints); 
			BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
			return bufferedImage;
		} catch (WriterException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			throw e;
		}
	}

	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format " + format + " to " + file);
		}
	}

	public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format " + format);
		}
	}

}