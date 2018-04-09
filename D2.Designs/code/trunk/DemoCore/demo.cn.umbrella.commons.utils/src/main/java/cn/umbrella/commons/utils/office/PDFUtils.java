package cn.umbrella.commons.utils.office;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.util.CollectionUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;


public class PDFUtils {
	private static  Logger logger = Logger.getLogger("PDFUtils");
	
	/**
	 * 给PDF文件添加图片水印
	 * 
	 * @param inPdf 要加水印的PDF 
	 * @param outPdf 加了水印后的PDF
	 * @param markImage 水印图片
	 * @throws Exception
	 */
	public static void addImageWaterMarkForPDF(String inPDF, String outPDF, String markImage) {
		try {
			PdfReader reader = new PdfReader(inPDF, "PDF".getBytes());
			int pageSize = reader.getNumberOfPages();
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outPDF));
			Image img = Image.getInstance(markImage);// 插入水印
			img.setAbsolutePosition(20, 20);
			for (int i = 1; i <= pageSize; i++) {
				// 在当前页的上层打印内容（stamp.getOverContent(i)）、在下层打印内容（stamp.getUnderContent(i)）
				PdfContentByte over = stamper.getOverContent(i);
				over.addImage(img);
			}
			stamper.close();// 关闭
			logger.info("*****************" + inPDF + "添加图片水印成功！");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	* @Title: addImagesToPDF
	* @Description: 将图片追加到PDF
	* @param inPDF 原PDF
	* @param outPDF 加完图片的PDF
	* @param images 需要添加的图片
	* @return void
	 */
	public static void addImagesToPDF(String inPDF, String outPDF, List<String> images) {
		if (CollectionUtils.isEmpty(images)) {
			logger.info("*****************没有可添加的图片！");
		} else {
			try {
				Document document = new Document();
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outPDF));
				document.open();
				PdfReader reader = new PdfReader(inPDF);
				int pageNumbers = reader.getNumberOfPages();
				PdfImportedPage page;
				for (int i = 1; i <= pageNumbers; i++) {
					page = writer.getImportedPage(reader, i);
					Image image = Image.getInstance(page);
					image.setAbsolutePosition(0, 0);
					document.add(image);
					document.newPage();
				}
				for (String img : images) {
					Image image = Image.getInstance(img);
					image.setAlignment(Image.ALIGN_CENTER);
					// 根据图片大小设置页面，一定要先设置页面，再newPage（），否则无效
					document.setPageSize(new Rectangle(image.getWidth(), image.getHeight()));
					document.newPage();
					document.add(image);
				}
				document.close();
				logger.info("*****************添加图片成功！");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 将图片转换成PDF
	 * 
	 * @param PDF 输出PDF
	 * @param images 要转换的图片
	 */
	public static void imagesToPDF(String PDF, List<String> images) {
		if (CollectionUtils.isEmpty(images)) {
			logger.info("*****************没有可转换的图片！");
		} else {
			Document document = new Document();// 1、创建一个Document对象
			document.setMargins(5, 5, 5, 5);
			try {
				PdfWriter.getInstance(document, new FileOutputStream(PDF));// 2、创建PdfWriter实例
				document.open();// 3、打开文档
				// 4、在文档中添加图片
				for (String i : images) {
					Image img = Image.getInstance(i);
					img.setAlignment(Image.ALIGN_CENTER);
					// 根据图片大小设置页面，一定要先设置页面，再newPage（），否则无效
					document.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
					document.newPage();
					document.add(img);
					logger.info("*****************添加图片：" + i);
				}
				document.close();// 5、关闭文档
				logger.info("*****************转换成功！");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	* @Title: PDFtoImages
	* @Description: 将PDF转换成图片
	* @param PDF pdf文件
	* @param imageDir 图片存放路径，如：D:/data/，需要加上最后的/
	* @return void
	 */
	public static void PDFtoImages(String PDF, String imageDir) {
		try {
			File dir = new File(imageDir);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			
			File file = new File(PDF);
			PDDocument doc = PDDocument.load(file);
			PDFRenderer renderer = new PDFRenderer(doc);
			int pageCount = doc.getNumberOfPages();
			for (int i = 0; i < pageCount; i++) {
				FileOutputStream out = new FileOutputStream(imageDir + (i + 1) + ".jpg");
				BufferedImage image = renderer.renderImage(i, 1.25f);// 第二个参数越大生成图片分辨率越高
				ImageIO.write(image, "jpg", out);
			}
			doc.close();
			logger.info("*****************转换成功！");
		} catch (InvalidPasswordException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 合并PDF
	 * 
	 * @param PDFs 要合并的PDF
	 * @param outPDF 输出PDF
	 * @param paginate 是否分页 true/false
	 */
	public static void mergePDFs(List<String> PDFs, String outPDF, boolean paginate) {
		if (CollectionUtils.isEmpty(PDFs)) {
			logger.info("*****************没有可合并的的PDF！");
		} else {
	        Document document = new Document();
	        FileOutputStream out = null;// 输出PDF
	        try {
				out = new FileOutputStream(outPDF);
	
				int totalPages = 0;// 总页数
	            List<PdfReader> readers = new ArrayList<PdfReader>();
				for (String pdf : PDFs) {
					PdfReader reader = new PdfReader(pdf);
					readers.add(reader);
					totalPages += reader.getNumberOfPages();
				}
				
	            PdfWriter writer = PdfWriter.getInstance(document, out);// 创建PdfWriter实例
	
	            document.open();
	            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,  BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
	            PdfContentByte cb = writer.getDirectContent();
	
	            PdfImportedPage page;
	            int currentPageNumber = 0;
	            int pageOfCurrentReaderPDF = 0;
	            Iterator<PdfReader> iteratorPDFReader = readers.iterator();
	
	            // 将PDF追加到输出文件
	            while (iteratorPDFReader.hasNext()) {
	                PdfReader pdfReader = iteratorPDFReader.next();
	                while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
	                	pageOfCurrentReaderPDF++;
	                	currentPageNumber++;
	                	page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
	                	document.setPageSize(new Rectangle(page.getWidth(), page.getHeight()));
	                    document.newPage();
	                    cb.addTemplate(page, 0, 0);
	
	                    // 是否分页
	                    if (paginate) {
	                        cb.beginText();
	                        cb.setFontAndSize(bf, 9);
	                        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "" + currentPageNumber + " of " + totalPages, 520, 5, 0);
	                        cb.endText();
	                    }
	                }
	                pageOfCurrentReaderPDF = 0;
	            }
	            out.flush();
	            document.close();
	            out.close();
	            logger.info("*****************合并成功！");
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (document.isOpen())
	                document.close();
	            try {
	                if (out != null)
	                    out.close();
	            } catch (IOException ioe) {
	                ioe.printStackTrace();
	            }
	        }
		}
    }
	
	public static void main(String[] args) {
		try {
			// 添加图片水印
//			addImageWaterMarkForPDF("D:/data/PDF.pdf", "D:/data/6.pdf", "D:/data/watermark2.png");
			
			// 向PDF中添加图片
			List<String> images = new ArrayList<String>();
			images.add("D:/data/1.jpeg");
			images.add("D:/data/2.jpeg");
			images.add("D:/data/3.jpg");
			images.add("D:/data/4.jpg");
//			addImagesToPDF("D:/data/PDF.pdf", "D:/data/out.pdf", images);
			
			// 将多个图片转换成PDF文件
//			imagesToPDF("D:/data/imageToPDF.pdf", images);
			
			// 按先后顺序合并多个PDF
			List<String> PDFs = new ArrayList<String>();
			PDFs.add("D:/data/PDF.pdf");
			PDFs.add("D:/data/out.pdf");
			mergePDFs(PDFs, "D:/data/both.pdf", true);
			
			// 将PDF转成图片
//			PDFtoImages("D:/data/PDF.pdf", "D:/data/images/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
