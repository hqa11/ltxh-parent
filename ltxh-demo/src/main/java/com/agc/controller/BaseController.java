package com.agc.controller;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import com.agc.common.Base;
import com.agc.constant.C;
/**
 * 
 * @author Hqa
 * @date 2016-7-21 
 * @email 18806278373@sina.cn
 * @to 获取当前线程域对象，用于继承
 */
public abstract class BaseController extends Base{

    /**
     * 获取当前Session中对象. 若当前无{@code session}则返回{@code null}且不会创建{@code session}.
     *
     * @param <T> 保存的对象类型.
     * @param key 对象保存键值.
     * @return 保存的对象或{@code null}.
     * @throws IllegalStateException 当前线程不是web请求抛出此异常.
     */
	@SuppressWarnings("unchecked")
	protected  <T> T getSessionAttribute(String key) throws IllegalStateException {
        return (T) WebUtils.getSessionAttribute(currentRequest(), key);
    }

    /**
     * 获取当前Session中对象. 若当前无{@code session}则创建一个.
     *
     * @param <T> 保存的对象类型.
     * @param key 对象保存键值.
     * @param clazz 保存的对象类型.
     * @return 保存的对象.
     * @throws IllegalStateException 当前线程不是web请求抛出此异常.
     */
	@SuppressWarnings("unchecked")
	protected <T> T getOrCreateSessionAttribute(String key, Class<T> clazz) throws IllegalStateException {
        HttpSession session = currentRequest().getSession();
        return (T) WebUtils.getOrCreateSessionAttribute(session, key, clazz);
    }

    /**
     * 保存变量到当前Session. 若保存的对象为{@code null}，则移除该键值所保存的对象。
     *
     * @param key 键值.
     * @param obj 要保存的对象或{@code null}.
     * @throws IllegalStateException 当前线程不是web请求抛出此异常.
     */
    protected void setSessionAttribute(String key, Object obj) throws IllegalStateException {
        WebUtils.setSessionAttribute(currentRequest(), key, obj);
    }

    /**
     * 获取当前Request对象.
     *
     * @return 当前Request对象或{@code null}.
     * @throws IllegalStateException 当前线程不是web请求抛出此异常.
     */
    protected HttpServletRequest currentRequest() throws IllegalStateException {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            throw new IllegalStateException("当前线程中不存在 Request 上下文");
        }
        return attrs.getRequest();
    }
    
    /**
     * 获取当前session对象. 若当前线程不是web请求或当前尚未创建{@code session}则返回{@code null}.
     *
     * @return 当前session对象或{@code null}.
     */
    protected HttpSession currentSession() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            return null;
        }
        return attrs.getRequest().getSession(false);
    }
    
    /**
     * 获取客户端真实IP
     * @return 
     */
    protected String getRemoteHost(){
    	String ip = currentRequest().getHeader("x-forwarded-for"); 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = currentRequest().getHeader("Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = currentRequest().getHeader("WL-Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = currentRequest().getRemoteAddr(); 
        } 
        return ip; 
    }
    
    protected String getMACAddress(String ip){ 
        String str = ""; 
        String macAddress = ""; 
        try { 
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip); 
            InputStreamReader ir = new InputStreamReader(p.getInputStream()); 
            LineNumberReader input = new LineNumberReader(ir); 
            for (int i = 1; i < 100; i++) { 
                str = input.readLine(); 
                if (str != null) { 
                    if (str.indexOf("MAC Address") > 1) { 
                        //macAddress = str.substring(str.indexOf("MAC Address") + 9, str.length());
                    	macAddress = str;
                        break; 
                    } 
                  if (str.indexOf("MAC 地址") > 1) { 
                       // macAddress = str.substring(str.indexOf("MAC 地址") + 9, str.length()); 
                	  	macAddress = str;
                        break; 
                    } 
                } 
            } 
        } catch (IOException e) { 
            e.printStackTrace(System.out); 
        }
        return macAddress; 
    }
    
    
    /**
	 * 异常处理器
	 * @param e
	 * @param response
	 */
	@ExceptionHandler(Exception.class)  	    
	protected  void  exceptionHandler(Exception e,HttpServletResponse response) {  
		PrintWriter wr=null;
		try {
			e.printStackTrace();
			wr= response.getWriter();
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			wr.print(C.SERVER_EXCEPTION);
		} catch (IOException e1) {			
			e1.printStackTrace();			
			wr.print(C.UNKNOW_ERROR);
		}finally{
			wr.flush();
			wr.close();
		}
	}  
}
