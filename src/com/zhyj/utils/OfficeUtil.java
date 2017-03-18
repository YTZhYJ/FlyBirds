package com.zhyj.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * 
 * @author zhang
 *
 */
public class OfficeUtil {

	//页面尺寸
	private static Rectangle pageSize = PageSize.A4;

	//部分数据设置静态
	private static Font titleFont ;
    // 小标题字体风格  
	private static Font subTitleFont ;
    // 正文字体风格  
	private static Font contextFont ; 
    // 页眉页脚字体风格  
	private static  Font headerFooterFont ;
	static{ 
        BaseFont baseFontChinese; 
        try { 
        	baseFontChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED); 
        	   // 标题字体风格  
        	   titleFont = new Font(baseFontChinese, 12, Font.BOLD);  
        	   // 小标题字体风格  
        	   subTitleFont = new Font(baseFontChinese, 11, Font.BOLD);  
        	   // 正文字体风格  
        	   contextFont = new Font(baseFontChinese, 10, Font.NORMAL);  
        	   // 页眉页脚字体风格  
        	   headerFooterFont = new Font(baseFontChinese, 10, Font.BOLD); 
        } catch (Exception e) {          
            e.printStackTrace(); 
        }  
	}
	public void createWordByIO(String author ,String filePath){
		try{
			Document document = new Document(pageSize);
			RtfWriter2 rtfWriter = RtfWriter2.getInstance(document, new FileOutputStream(filePath));
			/*
			 * 添加文件信息
			 */
			//标题
			document.addTitle("Hello mingri example");
			//作者
			document.addAuthor("wolf");
			//主题
			document.addSubject("This example explains how to add metadata.");
			document.addKeywords("iText, Hello mingri");
			document.addCreator(author);//添加作者
			
			document.open();
			/*
			 * 添加文件内容
			 */
			document.close();
			System.out.println("Word 创建成功...");
		}catch(Exception e){
			
		}
	}
	
	//##########################################

	//部分数据设置静态
    private static Font headfont ;// 设置字体大小 
    private static Font keyfont;// 设置字体大小 
    private static Font textfont;// 设置字体大小
	static{ 
        BaseFont baseFontChinese; 
        try { 
        	baseFontChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED); 
            headfont = new Font(baseFontChinese, 10, Font.BOLD);// 设置字体大小 
            keyfont = new Font(baseFontChinese, 8, Font.BOLD);// 设置字体大小 
            textfont = new Font(baseFontChinese, 8, Font.NORMAL);// 设置字体大小 
        } catch (Exception e) {          
            e.printStackTrace(); 
        }  
    } 
	
	/*
	 *	//1.新建document对象
	 *	//第一个参数是页面大小。接下来的参数分别是左、右、上和下页边距。
	 *	//2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
	 *	//创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
	 *	//3.标题，作者，主题,关键字设置
	 *  //4.打开文档
	 *	//5.向文档中添加内容
	 *	//通过 com.lowagie.text.Paragraph 来添加文本。可以用文本及其默认的字体、颜色、大小等等设置来创建一个默认段落
	 *	//6.关闭文档
	 */
	@SuppressWarnings("hiding")
	public void createPDF(String fileName,String author, String filePath){
		try{
			/*
			 * 设置文档信息：页面大小，背景，密码，间距，标题信息等
			 */
			//第一个参数是页面大小。
			Document document =new Document(PageSize.A4) ;
			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(filePath+"\\\\"+fileName));
			//设置文档打开密码   
			String USER_PASS = "Hello123";
			String OWNER_PASS = "Owner123";
			pdfWriter.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(),
			        PdfWriter.AllowPrinting,PdfWriter.STANDARD_ENCRYPTION_128);
			//设置PDF版本
			pdfWriter.setPdfVersion(PdfWriter.VERSION_1_7);
			//标题
			document.addTitle("Hello mingri example");
			//作者
			document.addAuthor("wolf");
			//主题
			document.addSubject("This example explains how to add metadata.");
			document.addKeywords("iText, Hello mingri");
			document.addCreator(author);//添加作者
			//接下来的参数分别是左、右、上和下页边距。
			document.setMargins(50, 50, 50, 50);
			
			document.open();//打开文件
			/*
			 * 写入文件内容
			 */
			//所有添加的内容都是以对象为单位的
			document.add(new Paragraph("我的PDF文档"));
			
			//字符串
			String contents = "zeshidsd\\dadfffffffffzheshiff";
			Chunk chunk = new Chunk(contents,FontFactory.getFont(FontFactory.HELVETICA,12));
			document.add(chunk);
			/*
			 * 根据需要添加内容
			 * 1.表格 Table table = new Table(列数，行数);
			 * 	   添加表格数据tabal.addCell(cell);
			 * 	 添加数据到文件document.add(table);
			 * 2.其他数据等
			 */			
			//中文设置
			Paragraph paragraph = new Paragraph(contents,textfont);
			document.add(paragraph);
			document.close();
			System.out.println("PDF创建完成");
			
		}catch(FileNotFoundException e){
			e.getStackTrace();
		}catch (IOException e) {
			e.getStackTrace();
		}catch (DocumentException e) {
			e.getStackTrace();
		}
		
	}
}
