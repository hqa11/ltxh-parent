package com.agc.core.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {
	
	public static void save(InputStream in,String path){
		if(in==null)return ;
		OutputStream out = null;
		try {
			out = new FileOutputStream(new File(path));
			byte[] arr = new byte[2048];
			int num = 0;
			while((num=in.read(arr))!=-1){
				out.write(arr, 0, num);
			}
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(out!=null)
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
