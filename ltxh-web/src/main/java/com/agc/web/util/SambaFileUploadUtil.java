package com.agc.web.util;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

/**
 * 局域网文件上传工具
 * @author Hqa
 * 2015-05-10
 *
 */
public class SambaFileUploadUtil {
	/**
	 * 创建文件夹
	 */
	public static void createDir(String smburl,String dir) throws Exception{  
		SmbFile fp = new SmbFile(smburl+"//"+dir);    
		//File fp = new File("Z://"+dir);    
		// 目录已存在创建文件夹    
		if (fp.exists() && fp.isDirectory()) {    

		} else{  
			// 目录不存在的情况下，会抛出异常    
			fp.mkdir();    
		}  
	}  
	/**
	 * 文件上传
	 */
	public static void fileUpload(String url, String originFilePath){
		SmbFile smbFile=null;
		SmbFileOutputStream smbOut=null ; 
		File file=new File(originFilePath);
		try { 
			System.out.println("开始连接...url：" + url); 
			smbFile = new SmbFile(url); 
			smbFile.connect(); 
			System.out.println("连接成功...url：" +url); 
		} catch (MalformedURLException e) { 
			e.printStackTrace(); 
			System.out.print(e); 
		} catch (IOException e) { 
			e.printStackTrace(); 
			System.out.print(e); 
		} 
		int flag = -1; 
		BufferedInputStream bf = null; 
		try { 
			smbOut = new SmbFileOutputStream(url + "/" + file.getName(), false); 
			bf = new BufferedInputStream(new FileInputStream(file)); 
			byte[] bt = new byte[8192]; 
			int n = bf.read(bt); 
			while (n != -1) { 
				smbOut.write(bt, 0, n); 
				smbOut.flush(); 
				n = bf.read(bt); 
			} 
			flag = 0; 
			System.out.println("文件传输结束..."); 
		} catch (SmbException e) { 
			e.printStackTrace(); 
			System.out.println(e); 
		} catch (MalformedURLException e) { 
			e.printStackTrace(); 
			System.out.println(e); 
		} catch (UnknownHostException e) { 
			e.printStackTrace(); 
			System.out.println("找不到主机...url：" + url); 
		} catch (IOException e) { 
			e.printStackTrace(); 
			System.out.println(e); 
		} finally { 
			try { 
				if (null !=smbOut) 
					smbOut.close(); 
				if (null != bf) 
					bf.close(); 
			} catch (Exception e2) { 
				e2.printStackTrace(); 
			} 
		} 
	}
	public static void fileUploadWithFile(String url,MultipartFile file){
		SmbFile smbFile=null;
		SmbFileOutputStream smbOut=null ; 
		try { 
			System.out.println("开始连接...url：" + url); 
			smbFile = new SmbFile(url); 
			smbFile.connect(); 
			System.out.println("连接成功...url：" +url); 
		} catch (MalformedURLException e) { 
			e.printStackTrace(); 
			System.out.print(e); 
		} catch (IOException e) { 
			e.printStackTrace(); 
			System.out.print(e); 
		} 
		int flag = -1; 
		BufferedInputStream bf = null; 
		try { 
			smbOut = new SmbFileOutputStream(url + "/" + file.getOriginalFilename(), false); 
			bf = new BufferedInputStream(file.getInputStream()); 
			byte[] bt = new byte[8192]; 
			int n = bf.read(bt); 
			while (n != -1) { 
				smbOut.write(bt, 0, n); 
				smbOut.flush(); 
				n = bf.read(bt); 
			} 
			flag = 0; 
			System.out.println("文件传输结束..."); 
		} catch (SmbException e) { 
			e.printStackTrace(); 
			System.out.println(e); 
		} catch (MalformedURLException e) { 
			e.printStackTrace(); 
			System.out.println(e); 
		} catch (UnknownHostException e) { 
			e.printStackTrace(); 
			System.out.println("找不到主机...url：" + url); 
		} catch (IOException e) { 
			e.printStackTrace(); 
			System.out.println(e); 
		} finally { 
			try { 
				if (null !=smbOut) 
					smbOut.close(); 
				if (null != bf) 
					bf.close(); 
			} catch (Exception e2) { 
				e2.printStackTrace(); 
			} 
		} 
	}
	/**
	 * 
	 * @param url
	 * @param file
	 */
	public static void fileUploadWithFile(String url, File file){
		SmbFile smbFile=null;
		SmbFileOutputStream smbOut=null ; 
		try { 
			System.out.println("开始连接...url：" + url); 
			smbFile = new SmbFile(url); 
			smbFile.connect(); 
			System.out.println("连接成功...url：" +url); 
		} catch (MalformedURLException e) { 
			e.printStackTrace(); 
			System.out.print(e); 
		} catch (IOException e) { 
			e.printStackTrace(); 
			System.out.print(e); 
		} 
		int flag = -1; 
		BufferedInputStream bf = null; 
		try { 
			smbOut = new SmbFileOutputStream(url + "/" + file.getName(), false); 
			bf = new BufferedInputStream(new FileInputStream(file)); 
			byte[] bt = new byte[8192]; 
			int n = bf.read(bt); 
			while (n != -1) { 
				smbOut.write(bt, 0, n); 
				smbOut.flush(); 
				n = bf.read(bt); 
			} 
			flag = 0; 
			System.out.println("文件传输结束..."); 
		} catch (SmbException e) { 
			e.printStackTrace(); 
			System.out.println(e); 
		} catch (MalformedURLException e) { 
			e.printStackTrace(); 
			System.out.println(e); 
		} catch (UnknownHostException e) { 
			e.printStackTrace(); 
			System.out.println("找不到主机...url：" + url); 
		} catch (IOException e) { 
			e.printStackTrace(); 
			System.out.println(e); 
		} finally { 
			try { 
				if (null !=smbOut) 
					smbOut.close(); 
				if (null != bf) 
					bf.close(); 
			} catch (Exception e2) { 
				e2.printStackTrace(); 
			} 
		} 
	}
	/**
	 * spingMVC后台直接上传
	 * @throws Exception 
	 * 服务器地址，格式：url:smb://Administrator:admin@192.168.1.201/App_Images/upload
	 * 源文件：file:new MultipartFile...
	 * 服务器基础路径目标文件夹：tarDir:/test001/test002
	 * 新文件名：newFileName:xxxxxx.jpg
	 */
	@SuppressWarnings("static-access")
	public static void fileUpLoadWithMutiPartFile(String url,MultipartFile file,String tarDir,String newFileName) throws Exception{
		//如果没有目标路径，则在服务器上创建路径
		SambaFileUploadUtil.createDir(url, tarDir);
		//上传文件
		SambaFileUploadUtil.fileUploadWithFile(url+tarDir, file);	
		//将文件重命名
		SmbFile f=new SmbFile(url+tarDir+"/"+file.getOriginalFilename());
		f.renameTo(new SmbFile(url+tarDir+"/"+newFileName));   
	}
	/**
	 * MultipartFile上传本地服务器
	 * @param file
	 * @param tarDir
	 * @param newFileName
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static  void uploadFile(MultipartFile file,String tarDir,String newFileName) throws IllegalStateException, IOException{
		File f=new File(tarDir);
		if(!f.exists()){
			f.mkdirs();
		}
		file.transferTo(new File(tarDir+"/"+newFileName));
	}
}
