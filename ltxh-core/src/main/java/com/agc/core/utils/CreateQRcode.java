package com.agc.core.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateQRcode implements Serializable {
	
	/** 
     * 生成二维码(QRCode)图片 
     * @param content 二维码图片的内容
     * @param imgPath 生成二维码图片完整的路径
     * @param ccbPath  二维码图片中间的logo路径
     */  
    public static int createQRCode(String content, String imgPath,String ccbPath) {  
        try {  
        	imgPath = imgPath.replaceAll("\\\\","/");
        	String fp = imgPath.substring(0,imgPath.lastIndexOf("/"));
        	File ff = new File(fp);
        	if(!ff.exists()){
        		ff.mkdirs();
        	}
        	
            Qrcode qrcodeHandler = new Qrcode();  
            qrcodeHandler.setQrcodeErrorCorrect('M');  
            qrcodeHandler.setQrcodeEncodeMode('B');  
            qrcodeHandler.setQrcodeVersion(6);
  
            // System.out.println(content);  
            byte[] contentBytes = content.getBytes("gb2312");  
            BufferedImage bufImg = new BufferedImage(130,130,BufferedImage.TYPE_INT_RGB);  
            Graphics2D gs = bufImg.createGraphics();  
  
            gs.setBackground(Color.WHITE);  
            gs.clearRect(0, 0,130,130);
            // 设定图像颜色 > BLACK  
            gs.setColor(Color.BLACK);
            
            // 设置偏移量 不设置可能导致解析出错  
            int pixoff = 2;  
            // 输出内容 > 二维码  
            if (contentBytes.length > 0 && contentBytes.length < 120) {  
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);  
                for (int i = 0; i < codeOut.length; i++) {  
                    for (int j = 0; j < codeOut.length; j++) {  
                        if (codeOut[j][i]) {  
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);  
                        }  
                    }  
                }  
            } else {  
                System.err.println("QRCode content bytes length = "  
                        + contentBytes.length + " not in [ 0,120 ]. ");  
                return -1;
            }  
            Image img = ImageIO.read(new File(ccbPath));//实例化一个Image对象。
            gs.drawImage(img,50,50,35,35,null);
            gs.dispose();  
            bufImg.flush();
            // 生成二维码QRCode图片  
            File imgFile = new File(imgPath);  
            ImageIO.write(bufImg, "png", imgFile);
  
        } catch (Exception e) 
        {  
            e.printStackTrace();
            return -100;
        }  
        
        return 0;
}  
    
    

}
