package com.agc.web.controller.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agc.core.annotion.Pass;
import com.agc.core.utils.CreateQRcode;
import com.agc.core.utils.FileUtils;
import com.agc.core.utils.JsonUtils;
import com.agc.web.util.ImageUtil;
import com.agc.web.util.SambaFileUploadUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 * admin图片上传controller
 *
 *@author Hqa
 *@date 2016年5月23日
 *
 */
@Controller
@RequestMapping("fileserver")
@Pass
public class FileServerUploadController {

	/**
	 * web图片上传通用方法
	 * @param file0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/web/upload")
	@ResponseBody
	public String imgfileUpolad(MultipartFile file_0,HttpServletRequest request, HttpServletResponse response,
			Integer w,Integer h,@RequestParam(defaultValue="0")Integer needCompress,
			@RequestParam(defaultValue="0")Integer needWater,Double maxSize,String tarDir) {
		
		try {		
			BufferedImage big=ImageIO.read(file_0.getInputStream());
			Integer hight=big.getHeight();
			Integer width=big.getWidth();
			//1.校验宽
			if(w!=null && w!=0){
				if(width<w-10 || width>w+10){
					//302代表宽高不符合要求
					return "302";
				}			
			}
			//2.校验高
			if(h!=null && h!=0){
				if(hight<h-10 || hight>h+10){
					//302代表宽高不符合要求
					return "302";
				}			
			}
			//3.校验大小单位为M
			double size_byte = file_0.getSize();
			double size=size_byte/1024/1024;
			if(maxSize!=null && maxSize!=0){
				if(size > maxSize){
					//304代表大小超出
					return "304";
				}
			}
			//获取文件的扩展名
			String ext=file_0.getOriginalFilename().substring(file_0.getOriginalFilename().lastIndexOf("."), file_0.getOriginalFilename().length());
			//新文件名			
			String newFileName=System.currentTimeMillis()+""+(int)(Math.random()*9000+1000)+ext;
			//执行文件上传
			SambaFileUploadUtil.uploadFile(file_0,this.getProjectPath(request)+"/upload/"+tarDir+"/", newFileName);
			//是否需要压缩加水印
			if(needCompress==1 && needWater==1){
				ImageUtil.compressPicAddWaterMark(getProjectPath(request)+"/upload"+tarDir+"/"+newFileName);

			}
			//是否需要压缩不加水印
			if(needCompress==1 && needWater==0){
				ImageUtil.compressPic(getProjectPath(request)+"/upload"+tarDir+"/"+newFileName);

			}
			//是否只加水印不压缩
			if(needCompress==0 && needWater==1){
				ImageUtil.addWaterMark(getProjectPath(request)+"/upload"+tarDir+"/"+newFileName);

			}
			//返回路径地址
			return urlSub(tarDir+"/"+newFileName);

		} catch (IIOException e1) {

			e1.printStackTrace();

			return "imageio_error";
			
		}	catch (Exception e) {

			e.printStackTrace();

			return "error";
		}	
	}
	
	
	
	/**
	 * web附件上传通用方法
	 * @param file0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/web/uploadDoc",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String uploadDoc(MultipartFile file_0,HttpServletRequest request, HttpServletResponse response,
			Double maxSize,String tarDir) {
		Map<String,String> ret = new HashMap<String, String>();
		try {		
			//3.校验大小单位为M
			double size_byte = file_0.getSize();
			double size=size_byte/1024/1024;
			if(maxSize!=null && maxSize!=0){
				if(size > maxSize){
					//304代表大小超出
					ret.put("code","304");
					return JsonUtils.toJson(ret);
				}
			}
			String oldFilename = file_0.getOriginalFilename();
			ret.put("filename", oldFilename);
			//获取文件的扩展名
			String ext=oldFilename.substring(file_0.getOriginalFilename().lastIndexOf("."), file_0.getOriginalFilename().length());
			//新文件名			
			String newFileName=System.currentTimeMillis()+""+(int)(Math.random()*9000+1000)+ext;
			//执行文件上传
			SambaFileUploadUtil.uploadFile(file_0,this.getProjectPath(request)+"/upload/"+tarDir+"/", newFileName);
			//返回路径地址
			ret.put("url", urlSub(tarDir+"/"+newFileName));
			return JsonUtils.toJson(ret);

		} catch (IIOException e1) {
			ret.put("code","500");
			e1.printStackTrace();
			return JsonUtils.toJson(ret);
			
		}	catch (Exception e) {
			ret.put("code","500");
			e.printStackTrace();
			return JsonUtils.toJson(ret);
		}	
	}

	
	@RequestMapping("/upoadFiles")
	@ResponseBody
	public Map<String,Object> upoadFiles(@RequestParam("file") CommonsMultipartFile[] files,HttpServletRequest request){  
		
		Map<String,Object> ret = new HashMap<String, Object>();
		ret.put("flag", false);
		ret.put("code",1);
		ret.put("info","失败");
		Map<String,Object> uriMap = new HashMap<String,Object>();
		List<Map<String,String>> data = new ArrayList<Map<String,String>>();

		String filePath = request.getParameter("filePath");
		if( filePath==null || filePath.trim().length()==0 ){
			filePath = "all";
		}
		int isAtt = 0;
		if(filePath.indexOf("/att")!=-1){
			isAtt = 1;
		}
		String realPath =getProjectPath(request)+"/upload/"+filePath;//物理路径
		File file = new File(realPath);
		if (!file.exists()) {  
			file.mkdirs();  
		}
		InputStream in = null;
		try {
			for(int i = 0;i<files.length;i++){
				System.out.println("fileName---------->" + files[i].getOriginalFilename());  
				if(!files[i].isEmpty()){
					String truename = files[i].getOriginalFilename();
					String fileName = truename.toLowerCase();
					String fileType = "";
					if(fileName.indexOf(".")!=-1){
						fileType = fileName.substring(fileName.lastIndexOf("."),fileName.length());
					}
					if(fileType==null || fileType.trim().length()==0){
						fileType = ".jpg";
					}
					fileName = System.currentTimeMillis()+RandomStringUtils.randomNumeric(3)+fileType;
					String uri = filePath+"/"+fileName;
					in = files[i].getInputStream();
					
					if(isAtt==0 && (fileType.indexOf("jpg")!=-1 ||fileType.indexOf("png")!=-1 ||fileType.indexOf("jpeg")!=-1 ) ){ //不是附件,并且是图片类型文件
						ImageUtil.compressPic(in,realPath+"/"+fileName); //压缩保存
					}else{
						FileUtils.save(in, realPath+"/"+fileName);
					}
					
					Map<String,String> img = new HashMap<String,String>();
					img.put("realname", truename);
					img.put("uri", uri);
					img.put("path",getHost(request)+"upload/"+uri);
					data.add(img);
				}  
			}

		} catch (Exception e) {  
			e.printStackTrace();  
			System.out.println("上传出错");
			return ret;
		} finally{
			try {
				if(in!=null){
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		uriMap.put("urlList", data);

		ret.put("flag", true);
		ret.put("code", 0);
		ret.put("info","ok");
		ret.put("data",uriMap);
		return ret;
	}
	
	
	/**
	 * web视频上传通用方法
	 * @param file0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/uploadVideo")
	@ResponseBody
	public String uploadVideo(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		String maxSize1 = request.getParameter("maxSize");
		if(StringUtils.isNotBlank(maxSize1)){
			Integer maxSize = Integer.parseInt(maxSize1);
			//校验大小单位为M
			double size_byte = file.getSize();
			double size=size_byte/1024/1024;
			if(maxSize!=null && maxSize!=0){
				if(size > maxSize){
					//304代表大小超出
					return "304";
				}
			}
		}
		String filePath = request.getParameter("filePath");
		try {		
			BufferedImage big=ImageIO.read(file.getInputStream());
			//获取文件的扩展名
			String ext=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
			if(".mp4".equalsIgnoreCase(ext) || ".avi".equalsIgnoreCase(ext) || ".flv".equalsIgnoreCase(ext) || ".dvd".equalsIgnoreCase(ext)){
				//新文件名			
				String newFileName=System.currentTimeMillis()+""+(int)(Math.random()*9000+1000)+ext;
				//执行文件上传
				SambaFileUploadUtil.uploadFile(file,this.getProjectPath(request)+"/upload/"+filePath+"/", newFileName);
				//返回路径地址
				return urlSub(filePath+"/"+newFileName);
			}else{
				return "300";
			}
			

		} catch (IIOException e1) {

			e1.printStackTrace();

			return "imageio_error";
			
		}	catch (Exception e) {

			e.printStackTrace();

			return "error";
		}	
	}


	/**
	 * 字符串去'/'
	 * @param str
	 * @return
	 */
	private String urlSub(String str){
		return str.substring(1, str.length());
	}
	
	/**
	 * 获取项目的绝对路径
	 * @param request
	 * @return
	 */
	public String getProjectPath(HttpServletRequest request){
		String projectPath=request.getSession().getServletContext().getRealPath("");
		return projectPath;
	}
	
	/**
	 * 获取网络地址
	 * @param request
	 * @return
	 */
	public String getHost(HttpServletRequest request){
		String path = request.getContextPath();
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
	}
	
	/**
	 * 生成商品二维码图片请求
	 * @param relPath
	 * @return
	 */
	@RequestMapping("/createGoodsQRCode")
	@ResponseBody
	public String createGoodsQRCode(String relPath,HttpServletRequest request,String path,String tar){
		try {
			System.out.println("coming~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			String abs_goodsPicPath=this.getProjectPath(request)+"/upload/"+relPath;
			String goods_image_path=this.getProjectPath(request)+"/upload/"+tar;
			System.out.println("##############################");
			System.out.println(abs_goodsPicPath);
			System.out.println(goods_image_path);
			System.out.println(path);
			CreateQRcode.createQRCode(path,goods_image_path,abs_goodsPicPath);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	
}
