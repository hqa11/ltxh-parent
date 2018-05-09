package com.agc.utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import com.agc.utils.ye.ResourceUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * 图片处理
 * @author LL
 *
 * @date: 2016年4月27日 上午8:51:17
 */
public class ImageUtil {
	final static int BUFFER_SIZE = 4096; 
	
    public static void Test(String imgPaht,int width,int height,String format){
    	//ImageUtil.saveImage(imgPaht);
    	//创建图片文件和处理后的图片文件  
        File fromPic=new File("D:\\3.jpg");
        File toPic=new File("E:\\3.jpg");  
        //不按比例，就按指定的大小进行缩放  
        try {
			Thumbnails.of(fromPic).size(width, height).keepAspectRatio(false).toFile(toPic);
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
    
    public static void main(String[] args) {
    	compressPic("D:\\我的文档\\My Pictures\\AAA.jpg");
	}
    
    
    
    public static void compressPic(String filepath){
    	try {
    		Thumbnails.of(filepath)
//    		.size(900,900)
    		.scale(0.5f)
    		.outputQuality(0.9)
    		.toFile(filepath);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public static void compressPic(InputStream in,String filepath){
    	try {
    		Thumbnails.of(in)
    		.size(900,900)
//    		.scale(0.5f)
    		.outputQuality(0.9)
    		.toFile(filepath);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public static void compressImg(InputStream in,OutputStream out){
    	try {
			Thumbnails.of(in)  
			.scale(0.5f) 
			.toOutputStream(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    
public static void rotateImage(String filePath, int degree) {
    	
    	BufferedImage bufferedimage;
    	try {
			bufferedimage = ImageIO.read(new FileInputStream(new File(filePath)));
			int w = bufferedimage.getWidth();
	        int h = bufferedimage.getHeight();
	        int type = bufferedimage.getColorModel().getTransparency();
	        BufferedImage img;
	        Graphics2D graphics2d;
	        (graphics2d = (img = new BufferedImage(w, h, type))
	                .createGraphics()).setRenderingHint(
	                RenderingHints.KEY_INTERPOLATION,
	                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
	        graphics2d.drawImage(bufferedimage, 0, 0, null);
	        graphics2d.dispose();
	        
	        
	        OutputStream os = System.out;
    		File f= new File(filePath) ; // 声明File对象
    		try {
				os = new FileOutputStream(f);// 通过对象多态性，进行实例化
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} 
    		
	        ImageIO.write(img, "jpg", os);
			os.flush();  
			os.close();
	        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    public static void rotateIOSImage(String filePath) {
    	
    	BufferedImage bufferedimage;
    	OutputStream os = null;
    	try {
			bufferedimage = ImageIO.read(new FileInputStream(new File(filePath)));
			int w = bufferedimage.getWidth();
	        int h = bufferedimage.getHeight();
	        int type = bufferedimage.getColorModel().getTransparency();
	        int nw = (int) (bufferedimage.getWidth()/1.414);
	        int nh = (int) (bufferedimage.getHeight()/1.414);
	        int degree = w > h ? 90 : -90;
	        if(w==h){
	        	return;
	        }
	        BufferedImage img;
	        Graphics2D graphics2d;
	        (graphics2d = (img = new BufferedImage(h, w, type))
	                .createGraphics()).setRenderingHint(
	                RenderingHints.KEY_INTERPOLATION,
	                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        graphics2d.rotate(Math.toRadians(degree), nw/2, nh/2);
	        graphics2d.drawImage(bufferedimage, 0, 0, null);
	        graphics2d.dispose();
	        
	        os = System.out;
    		File f= new File(filePath) ; // 声明File对象
    		try {
				os = new FileOutputStream(f);// 通过对象多态性，进行实例化
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} 
    		
	        ImageIO.write(img, "jpg", os);
			os.flush();  
	        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(os!=null){
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
    
    
    
    
    
    /**获取网络图片
     * @param imgPath
     * @return
     */
    public static  InputStream getInputStream(String imgPath){  
        InputStream inputStream=null;  
        HttpURLConnection httpURLConnection=null;  
        try{  
            URL url=new URL(imgPath);  
            if(url!=null){  
                httpURLConnection=(HttpURLConnection) url.openConnection();  
                httpURLConnection.setConnectTimeout(3000);  
                httpURLConnection.setRequestMethod("GET");  
                int responseCode=httpURLConnection.getResponseCode();  
                if(responseCode==200){  
                    inputStream=httpURLConnection.getInputStream();
                  
                }  
            }  
              
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        return inputStream;  
    }  
      
    public static void saveImage(String imgPaht,int width,int height,String format) throws IOException{ 
    	//File newFile = new File(imgPath);
		InputStream inputStream=getInputStream(imgPaht);
		 String outFileName = imgPaht.substring(0, imgPaht.lastIndexOf("."))  
	                + imgPaht  
	                + imgPaht.substring(imgPaht.lastIndexOf("."), imgPaht  
	                        .length()); 
		OutputStream os=new FileOutputStream("D:\\upload\\"+outFileName); 
		ImageUtil.resizeImage(inputStream,os, width,height, ".png");
		/*String str_imgPath = "";
		try {
			str_imgPath = ImageUtil.InputStreamTOString(inputStream);
		} catch (Exception e1) {
			e1.printStackTrace();
		}*/
		//DataUtil.GetImageStr(imgPath);
		//DataUtil.GetImageStr(str_imgPath);
        FileOutputStream fileOutputStream=null;
        
        byte[] data=new byte[1024];  
        int len=0;  
        try{  
        fileOutputStream=new FileOutputStream("D:\\3.jpg");  
        while((len=inputStream.read(data))!=-1){  
        fileOutputStream.write(data,0,len);   
              
        }  
          
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            try {  
                inputStream.close();  
                fileOutputStream.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
          
    }
    /** 
     * 将InputStream转换成String 
     * @param in InputStream 
     * @return String 
     * @throws Exception 
     *  
     */ 
    public static String InputStreamTOString(InputStream in) throws Exception{  
        
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] data = new byte[BUFFER_SIZE];  
        int count = -1;  
        while((count = in.read(data,0,BUFFER_SIZE)) != -1)  
            outStream.write(data, 0, count);  
          
        data = null;  
        return new String(outStream.toByteArray(),"ISO-8859-1");  
    } 
    
    /* 改变图片的大小到宽为size，然后高随着宽等比例变化 
    * @param is 上传的图片的输入流 
    * @param os 改变了图片的大小后，把图片的流输出到目标OutputStream 
    * @param size 新图片的宽 
    * @param format 新图片的格式 
    * @throws IOException 
    */ 
    public static void resizeImage(InputStream is, OutputStream os, int width,int height, String format) throws IOException { 
	    BufferedImage prevImage = ImageIO.read(is); 
	      
	   //double percent = size/width; 
	    int newWidth = width; 
	    int newHeight = height; 
	    BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR); 
	    Graphics graphics = image.createGraphics(); 
	    graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null); 
	    ImageIO.write(image, format, os); 
	    os.flush(); 
	    is.close(); 
	    os.close(); 
    }

    
    public static void compressPicAddWaterMark(String filepath){
    	String watermark = ResourceUtil.getString("watermark");
		try {
			Thumbnails.of(filepath)
	        .size(900,900)
	        .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(watermark)), 0.5f)
	        .outputQuality(0.9)
	        .toFile(filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public static void addWaterMark(String filepath){
    	String watermark = ResourceUtil.getString("watermark");
		try {
			Thumbnails.of(filepath)
	        .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(watermark)), 0.5f)
	        .outputQuality(0.9)
	        .toFile(filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public static void addWaterMark(String filepath,String waterMarkName){
    	String watermark = ResourceUtil.getString(waterMarkName);
    	try {
    		Thumbnails.of(filepath)
    		.watermark(Positions.TOP_LEFT, ImageIO.read(new File(watermark)), 0.5f)
    		.outputQuality(0.9)
    		.toFile(filepath);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public static int createSmallSizeImg(String realPath,String outPath,int width,int height){
    	try {
    		File file = new File(realPath);
    		if(!file.exists()){
    			return -1;
    		}
    		
    		Thumbnails.of(realPath)
    		.size(width,height)
    		.outputQuality(0.9)
    		.toFile(outPath);
    		return 1; 
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return -1;
    }
    
    
    
    
    
    
    
    
    
    
    
}
