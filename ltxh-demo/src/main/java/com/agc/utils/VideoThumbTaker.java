package com.agc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.agc.utils.ye.ResourceUtil;


public class VideoThumbTaker
{
	
    protected static String ffmpegApp;

    public VideoThumbTaker(){
    	if(ffmpegApp==null){
    		ffmpegApp = ResourceUtil.getString("ffmpeg");
    	}
    }

    @SuppressWarnings("unused")
    /****
     * 获取指定时间内的图片
     * @param videoFilename:视频路径
     * @param thumbFilename:图片保存路径
     * @param width:图片长
     * @param height:图片宽
     * @param hour:指定时
     * @param min:指定分
     * @param sec:指定秒
     * @throws IOException
     * @throws InterruptedException
     */
    public void getThumb(String videoFilename, String thumbFilename, int width,
            int height, int hour, int min, float sec) throws IOException,
            InterruptedException
    {
        ProcessBuilder processBuilder = new ProcessBuilder(ffmpegApp, "-y",
                "-i", videoFilename, "-vframes", "1", "-ss", hour + ":" + min
                        + ":" + sec, "-f", "mjpeg", "-s", width + "*" + height,
                "-an", thumbFilename);

        Process process = processBuilder.start();

        InputStream stderr = process.getErrorStream();
        InputStreamReader isr = new InputStreamReader(stderr);
        BufferedReader br = new BufferedReader(isr);
        String line;
        //打印 sb，获取更多信息。 如 bitrate、width、heigt
        StringBuffer sb = new StringBuffer();
        while ((line = br.readLine()) != null){
            sb.append(line);
        }
        int rotate = (int)imgRotate(sb.toString());
        System.out.println("rotate="+rotate);
        if(rotate!=0){//旋转
        	ImageUtil.rotateImage(thumbFilename, rotate);
        }
        
        process.waitFor();
        
        if(br != null)
            br.close();
        if(isr != null)
            isr.close();
        if(stderr != null)
            stderr.close();
    }
    
    public double imgRotate(String sb){
    	Pattern pattern = Pattern.compile("rotate[' ']+:[' ']+\\d+");
    	Matcher matcher = pattern.matcher(sb);
        String b= "";
        if (matcher.find())
        {
            String wh = matcher.group();
            //w:100 h:100
            String[] strs = wh.split(":");
            if(strs != null && strs.length == 2)
            {
                b =  strs[1].trim();
            }
        }
        if(b.length()>0){
        	return Double.parseDouble(b);
        }
        return 0;
    }
    
    public void getFirstThumb(String videoFilename, String thumbFilename) throws IOException, InterruptedException{
        getThumb(videoFilename, thumbFilename, 320, 240, 0, 0, 0);
    }
    

    public static void main(String[] args)
    {
    	long now = System.currentTimeMillis();
        VideoThumbTaker videoThumbTaker2 = new VideoThumbTaker();
        try
        {
        	String name = "F:/新建文件夹/7.mp4";
        	videoThumbTaker2.getFirstThumb(name, name+".jpg");
        	System.out.println("over");
        } catch (Exception e)
        {
        	e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis()-now);
    }
}