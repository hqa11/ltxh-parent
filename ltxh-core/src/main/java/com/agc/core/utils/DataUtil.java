package com.agc.core.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
public class DataUtil{

	/**
	 * 上传图片base64字符串转化成图片
	 * @param directionry 保存的文件夹名称
	 * @param basePath 保存路径
	 * @param imgStr 图片码流文件
	 * @return
	 */
	public static String GenerateImage(String directionry,String basePath,String imgStr) { // 对字节数组字符串进行Base64解码并生成图片
		String imgFilePath = "";
		String fileName = System.currentTimeMillis()+DataUtil.getRond4()+".jpg";
		if (imgStr == null) // 图像数据为空
			return null;
		try{
			if(imgStr.indexOf(",")!=-1){
				imgStr=imgStr.substring(imgStr.indexOf(",")+1);
			}
			byte dataByte[] = Base64.decode(imgStr);

			File file = new File(directionry+basePath);
			if (!file.exists()) {
				file.mkdirs();// 创建父目录地址
			}
			// 生成jpeg图片
			imgFilePath=directionry+basePath+"/"+fileName;
			//imgPath=directionry+basePath+"/"+fileName;//创建图片地址
			OutputStream out=new FileOutputStream(imgFilePath);

			out.write(dataByte);
			out.flush();
			out.close();
		}catch (Exception e){

		}
		return basePath+"/"+fileName;
	}


	/**
	 * 将空字符串转变为null
	 */
	public static void blankToNull(String str[]){
		if(null!=str){
			for (int i=0;i<str.length;i++) {
				if(StringUtils.isBlank(str[i])){
					str[i]=null;
				}
			}
		}
	}
	/**
	 * 获取当天00:00:00的时间戳
	 * @param date
	 * @return
	 */
	public static Long getTodayStartTime(Date date){
		String now=DataUtil.timeMilionsFormat(date.getTime(), "yyyy-MM-dd");
		Long result=null;
		try {
			result=DataUtil.stringFormatToTimeMilions("yyyy-MM-dd",now);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 获取当天23:59:59的时间戳
	 * @param date
	 * @return
	 */
	public static Long getTodayEndTime(Date date){
		String now=DataUtil.timeMilionsFormat(date.getTime(), "yyyy-MM-dd");
		Long result=null;
		String end=now+" 23:59:59";
		try {
			result=DataUtil.stringFormatToTimeMilions("yyyy-MM-dd HH:mm:ss",end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 日期格式
	 */
	private static final String FORMAT_DATE = "yyyyMMddHHmmssSSS";
	private static SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
	private final  static String defaultKey="bricsz_nongmuren";
	/**
	 * 
	 * 取得64UUID+当前时间蹉
	 * 
	 * @return
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String date = sdf.format(new Date());
		return uuid.toString() + date;
	}
	/**
	 * 取得随机6位整数
	 * 
	 * @return
	 */
	public static String getRond6() {
		String rand = String.valueOf(Math.random() * (999999 - 100000) + 100000);
		return String.valueOf(rand).substring(0, rand.indexOf('.'));
	}

	/**
	 * 取得随机4位整数
	 * 
	 * @return
	 */
	public static String getRond4() {
		String rand = String.valueOf(Math.random() * (9999 - 1000) + 1000);
		return String.valueOf(rand).substring(0, rand.indexOf('.'));
	}

	/**
	 * 取得随机3位整数
	 * 
	 * @return
	 */
	public static String getRond3() {
		String rand = String.valueOf(Math.random() * (999 - 100) + 100);
		return String.valueOf(rand).substring(0, rand.indexOf('.'));
	}
	/**
	 * 根据Email地址取得改地址的收信服务器地址
	 * 
	 * @param emailAddress
	 * @return
	 */
	public static String getSMTPName(String emailAddress) {
		if (emailAddress == null || "".equals(emailAddress)) {
			return null;
		}
		int start = emailAddress.indexOf("@");// @的位置
		return "smtp." + emailAddress.substring(start + 1);
	}

	/**
	 * 手机号验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		// 验证13,14,15,17,18
		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * 电话号码验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str) {
		Pattern p1 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^([0][1-9]{2,3}-)?[0-9]{5,8}$"); // 验证带区号的
		m = p1.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * 
	 * 邮箱验证
	 * 
	 * @param line
	 * @return
	 */
	public static boolean isEmail(String line) {
		Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher m = p.matcher(line);
		return m.find();
	}
	/**
	 * 设置消息编号
	 * 
	 * @return
	 */
	public static int getSendNo() {
		SimpleDateFormat sdf = new SimpleDateFormat("mmssSSS");
		return Integer.parseInt(sdf.format(new Date()));
	}


	/***
	 * aes加密
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String aesEncrypt(String str) throws Exception {
		if(str == null) return null;
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(defaultKey.getBytes("utf-8"), "AES"));
		byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
		return new BASE64Encoder().encode(bytes);
	}

	/**aes解密
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String aesDecrypt(String str) throws Exception {
		if (str == null) return null;
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(defaultKey.getBytes("utf-8"), "AES"));
		byte[] bytes = new BASE64Decoder().decodeBuffer(str);
		bytes = cipher.doFinal(bytes);
		return new String(bytes, "utf-8");
	}

	// 图片转化成base64字符串
	public static String GetImageStr(String imageFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imageFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);//返回Base64编码过的字节数组字符串
	}

	//生成订单编号
	public  static String getOrders(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmssSSS");
		String str=sdf.format(new Date().getTime());
		str+=DataUtil.getRond4();
		return str;
	}

	//生成流水号
	public  static String getRoundNumber(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmssSSS");
		String str=sdf.format(new Date().getTime());
		str+=DataUtil.getRond3();
		return str;
	}
	/**
	 * 根据经纬度，获取两点间的距离
	 * 
	 * @param lng1 当前经度
	 * @param lat1 当前纬度
	 * @param lng2 目标经度
	 * @param lat2 目标纬度
	 * @return
	 *
	 */
	public static Double  distanceByLngLat(double lng1, double lat1, double lng2, double lat2) {

		double radLat1 = lat1 * Math.PI / 180;
		double radLat2 = lat2 * Math.PI / 180;
		double a = radLat1 - radLat2;
		double b = lng1 * Math.PI / 180 - lng2 * Math.PI / 180;
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
				* Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
		s = Math.round(s * 10000) / 10000;
		//String distance=(int)s+"m";
		DecimalFormat   df   =new   DecimalFormat("#0.00");

		return Double.parseDouble(df.format(s/1000));
	}
	/**
	 * 根据路径保存图片
	 * @param directionry 保存的文件夹名称
	 * @param basePath 保存路径
	 * @param OriginPath 图片原路径
	 * @return 图片保存后的路径
	 */
	public static String saveImage(String directionry, String basePath,String OriginPath) { // 对字节数组字符串进行Base64解码并生成图片


		return null;
	}


	/**
	 * 正则表达式提取字符串中的数字
	 * @param str
	 * @return
	 */
	public static Integer CheckNumber(String str){
		String regEx="[^0-9]";   
		Pattern p = Pattern.compile(regEx);   
		Matcher m = p.matcher(str);    
		return Integer.parseInt(m.replaceAll("").trim());
	}

	/**
	 * 删除服务器端图片
	 * @param fileName
	 */
	public static void deleteImg(String fileName){
		File file=new File(fileName);
		if(file.exists()){
			file.delete();
		}
	}
	/***
	 * 
	 * java自带的Md5加密方式
	 * @params
	 * @return
	 */
	public static String MD5(String s) {  
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};         
		try {  
			byte[] btInput = s.getBytes();  
			// 获得MD5摘要算法的 MessageDigest 对象  
			MessageDigest mdInst = MessageDigest.getInstance("MD5");  
			// 使用指定的字节更新摘要  
			mdInst.update(btInput);  
			// 获得密文  
			byte[] md = mdInst.digest();  
			// 把密文转换成十六进制的字符串形式  
			int j = md.length;  
			char str[] = new char[j * 2];  
			int k = 0;  
			for (int i = 0; i < j; i++) {  
				byte byte0 = md[i];  
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
				str[k++] = hexDigits[byte0 & 0xf];  
			}  
			return new String(str);  
		} catch (Exception e) {  
			e.printStackTrace();  
			return null;  
		}  
	}	 
	/**
	 * 字符串转换unicode
	 */
	public static String string2Unicode(String string) {

		StringBuffer unicode = new StringBuffer();

		for (int i = 0; i < string.length(); i++) {

			// 取出每一个字符
			char c = string.charAt(i);

			// 转换为unicode
			unicode.append("\\u" + Integer.toHexString(c));
		}

		return unicode.toString();
	}
	/**
	 * unicode 转字符串
	 */
	public static String unicode2String(String unicode) {

		StringBuffer string = new StringBuffer();

		String[] hex = unicode.split("\\\\u");

		for (int i = 1; i < hex.length; i++) {

			// 转换出每一个代码点
			int data = Integer.parseInt(hex[i], 16);

			// 追加成string
			string.append((char) data);
		}

		return string.toString();
	}
	/**
	 * 计算时间差
	 */
	public static long dateDifference(long time){
		Long s = (System.currentTimeMillis() - time) / (1000);
		return s;
	}
	/**
	 * 判断是否不为空 包括null和""
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotNull(String str) {
		if ((str == null) || (str.trim().length() == 0)) {
			return false;
		}

		return (!(str.trim().equalsIgnoreCase("null")));
	}

	/**
	 * 判断字符串是否为空，包括null和""
	 * 
	 * @param str
	 *            待判断的字符。
	 * @return
	 */
	public static boolean isNull(String str) {
		return ((str == null) || (str.trim().length() == 0));
	}


	//base64实现加密
	public static String getBase64(String str) {  
		byte[] b = null;  
		String s = null;  
		try {  
			b = str.getBytes("utf-8");  
		} catch (UnsupportedEncodingException e) {  
			e.printStackTrace();  
		}  
		if (b != null) {  
			s = new BASE64Encoder().encode(b);  
		}  
		return s;  
	}  

	// base64实现解密  
	public static String getFromBase64(String s) {  
		byte[] b = null;  
		String result = null;  
		if (s != null) {  
			BASE64Decoder decoder = new BASE64Decoder();  
			try {  
				b = decoder.decodeBuffer(s);  
				result = new String(b, "utf-8");  
			} catch (Exception e) {  
				e.printStackTrace();  
			}  
		}  
		return result;  
	}  
	/**
	 * 时间戳转化为时间字符串
	 * @param timeMilions
	 * @return
	 */
	public static String timeMilionsFormat(Long timeMilions,String parterner){
		if(timeMilions==null){
			return "";
		}
		SimpleDateFormat sdf=new SimpleDateFormat(parterner);
		Date date=new Date(timeMilions);
		String result=sdf.format(date);
		return result;
	}
	/**
	 * 时间字符创转化为时间戳
	 * @param timeMilions
	 * @return
	 * @throws ParseException 
	 */
	public static Long stringFormatToTimeMilions(String parterner,String time) throws ParseException{
		if(!StringUtils.isNotBlank(time)){
			return 0L;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(parterner);
		Date date=sdf.parse(time);
		return date.getTime();
	}  
	/**

	 * 返回首字母

	 * @param strChinese

	 * @param bUpCase

	 * @return

	 */

	public static String getPYIndexStr(String strChinese, boolean bUpCase){

		try{

			StringBuffer buffer = new StringBuffer();

			byte b[] = strChinese.getBytes("GBK");//把中文转化成byte数组

			for(int i = 0; i < b.length; i++){

				if((b[i] & 255) > 128){

					int char1 = b[i++] & 255;

					char1 <<= 8;//左移运算符用“<<”表示，是将运算符左边的对象，向左移动运算符右边指定的位数，并且在低位补零。其实，向左移n位，就相当于乘上2的n次方

					int chart = char1 + (b[i] & 255);

					buffer.append(getPYIndexChar((char)chart, bUpCase));

					continue;

				}

				char c = (char)b[i];

				if(!Character.isJavaIdentifierPart(c))//确定指定字符是否可以是 Java 标识符中首字符以外的部分。

					c = 'A';

				buffer.append(c);

			}

			return buffer.toString();

		}catch(Exception e){

			System.out.println((new StringBuilder()).append("\u53D6\u4E2D\u6587\u62FC\u97F3\u6709\u9519").append(e.getMessage()).toString());

		}

		return null;

	}
	/**
	 * 将时间戳转为yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String getDateFormat(Long date){
		String result="";
		if(date!=null){
			result=timeMilionsFormat(date, "yyyy-MM-dd HH:mm:ss");
		}
		return result;
	}

	/**
	 * 将时间戳转为yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String getDateFormatYmd(Long date){
		String result="";
		if(date!=null){
			result=timeMilionsFormat(date, "yyyy-MM-dd");
		}
		return result;
	}

	/**

	 * 得到首字母

	 * @param strChinese

	 * @param bUpCase

	 * @return

	 */

	private static char getPYIndexChar(char strChinese, boolean bUpCase){

		int charGBK = strChinese;

		char result;

		if(charGBK >= 45217 && charGBK <= 45252)

			result = 'A';

		else

			if(charGBK >= 45253 && charGBK <= 45760)

				result = 'B';

			else

				if(charGBK >= 45761 && charGBK <= 46317)

					result = 'C';

				else

					if(charGBK >= 46318 && charGBK <= 46825)

						result = 'D';

					else

						if(charGBK >= 46826 && charGBK <= 47009)

							result = 'E';

						else

							if(charGBK >= 47010 && charGBK <= 47296)

								result = 'F';

							else

								if(charGBK >= 47297 && charGBK <= 47613)

									result = 'G';

								else

									if(charGBK >= 47614 && charGBK <= 48118)

										result = 'H';

									else

										if(charGBK >= 48119 && charGBK <= 49061)

											result = 'J';

										else

											if(charGBK >= 49062 && charGBK <= 49323)

												result = 'K';

											else

												if(charGBK >= 49324 && charGBK <= 49895)

													result = 'L';

												else

													if(charGBK >= 49896 && charGBK <= 50370)

														result = 'M';

													else

														if(charGBK >= 50371 && charGBK <= 50613)

															result = 'N';

														else

															if(charGBK >= 50614 && charGBK <= 50621)

																result = 'O';

															else

																if(charGBK >= 50622 && charGBK <= 50905)

																	result = 'P';

																else

																	if(charGBK >= 50906 && charGBK <= 51386)

																		result = 'Q';

																	else

																		if(charGBK >= 51387 && charGBK <= 51445)

																			result = 'R';

																		else

																			if(charGBK >= 51446 && charGBK <= 52217)

																				result = 'S';

																			else

																				if(charGBK >= 52218 && charGBK <= 52697)

																					result = 'T';

																				else

																					if(charGBK >= 52698 && charGBK <= 52979)

																						result = 'W';

																					else

																						if(charGBK >= 52980 && charGBK <= 53688)

																							result = 'X';

																						else

																							if(charGBK >= 53689 && charGBK <= 54480)

																								result = 'Y';

																							else

																								if(charGBK >= 54481 && charGBK <= 55289)

																									result = 'Z';

																								else

																									result = (char)(65 + (new Random()).nextInt(25));

		if(!bUpCase)

			result = Character.toLowerCase(result);

		return result;

	}

	/**
	 * 双精度数保留2位小数
	 * @return
	 */
	public static String doubleFormat(Double num){
		if(num!=null){

			DecimalFormat  df  = new DecimalFormat("######0.00");  

			return df.format(num);
		}

		return "";
	}



	public static void main(String[] args) throws Exception {
		System.out.println(aesDecrypt("dTMgnELisbD/qfJGExnBBQ=="));

	}


	public static Map<String,Object> retMap(int code,String msg,Object data){
		Map<String,Object> ret = new HashMap<String,Object>();
		ret.put("code", code);
		ret.put("msg", msg);
		ret.put("data", data);
		return ret;
	}

	/**
	 * 生成八位[0-9][a-z][A-Z]混排卡密
	 * @return
	 */
	public static final List<String> ge_list=new ArrayList();

	static{		
		for(int k=0;k<10;k++){
			ge_list.add(k+"");
		}
		for(char lowerC='a';lowerC<='z';lowerC++){
			ge_list.add(lowerC+"");
		}
		for(char upperC='A';upperC<='Z';upperC++){
			//将大写的i略过淆
			if('I'==upperC){
				continue;
			}
			ge_list.add(upperC+"");
		}
	}


	public static String generateCardPwd(){				
		StringBuilder result=new StringBuilder();
		//生成一个0-60的随机数
		int r=0;
		for(int k=0;k<8;k++){
			r=new Random().nextInt(61);
			result.append(ge_list.get(r));
		}
		return result+"";
	}

	/**
	 * 生成年月日时分秒+六位随机数卡号
	 * @return
	 */
	public static  String generateCardNum(){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		String format = sdf.format(date);
		String random = RandomStringUtils.randomNumeric(6);//随机六位数
		return format+random;

	}

	/**
	 * 生成年月日时分秒+4位编号
	 * @return
	 */
	public static  String generateAcNum(){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		String format = sdf.format(date);
		String random = RandomStringUtils.randomNumeric(4);//随机六位数
		return format+random;

	}


	/**
	 * 生成年月日时分秒+3位编号
	 * @return
	 */
	public static  String generateFtNum(){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		String format = sdf.format(date);
		String random = RandomStringUtils.randomNumeric(3);//随机六位数
		return format+random;

	}

	/**
	 * 生成不定额充值卡卡号
	 */
	public static  String generateAnymoneyCardNum(String cardNumber){
		//660000+00001
		StringBuilder sb=new StringBuilder("660000");

		Long l = null;
		if(cardNumber == null || cardNumber == ""){
			sb.append("00001");
			l = Long.parseLong(sb.toString());
		}else{
			l = Long.parseLong(cardNumber)+1;
		}
		String anymoneyCardNumber = l.toString();
		return anymoneyCardNumber;

	}
	/**
	 * 生成八位[0-9][a-z][A-Z]混排不定额激活码
	 * @return
	 */
	public static String generateAnymoneyCardPwd(){				
		StringBuilder result=new StringBuilder();
		//生成一个0-8的随机数
		int r=0;
		for(int k=0;k<8;k++){
			r=new Random().nextInt(61);
			result.append(ge_list.get(r));
		}
		return result+"";
	}

	/**
	 * 新规则生成卡号
	 * @return
	 */
	public static  synchronized String generateCardNum(String storeId,String type,String maxId){
		StringBuilder sb=new StringBuilder("");
		//1.第一位(电子为8,纸质)		
		//2.第二到六位店铺id,不足用0补足
		while(storeId.length()<5){
			storeId="0"+storeId;
		}
		//3.第七到十三位卡密表id,不足用0补足
		while(maxId.length()<7){
			maxId="0"+maxId;
		}
		return sb.append(type).append(storeId).append(maxId)+"";

	}

	//乘法
	public static String mul(String v1,String v2){   
		BigDecimal b1 = new BigDecimal(v1);   
		BigDecimal b2 = new BigDecimal(v2);   
		//return b1.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		BigDecimal b= b1.multiply(b2).divide(new BigDecimal(1) ,2 ,BigDecimal.ROUND_HALF_UP);
		return b.toString();
	}



	public static List<String> getAllTheDateOftheMonth(Date date) {
		List<String> list = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);

		int month = cal.get(Calendar.MONTH);
		while(cal.get(Calendar.MONTH) == month){
			list.add(new SimpleDateFormat("dd").format(cal.getTime()));
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}
	
	
	private  final static String AJAX_ACCEPT_CONTENT_TYPE = "text/html;type=ajax";
	private  final static String AJAX_SOURCE_PARAM = "ajaxSource";
	public static boolean isAjax(HttpServletRequest request){
		String acceptHeader = request.getHeader("Accept");
		String ajaxParam = request.getParameter(AJAX_SOURCE_PARAM);
		if (AJAX_ACCEPT_CONTENT_TYPE.equals(acceptHeader) || (ajaxParam!=null && ajaxParam.trim().length() > 0)) {
			return true;	
		} else {
			String requestType = request.getHeader("X-Requested-With"); 
			if("XMLHttpRequest".equals(requestType)){
				return true;
			}
			return false;
		}
	}


	/**
	 * 移除map中value为空的key
	 * @param beanMap
	 */
	public static void removeNullKey(Map<String, Object> beanMap) {
		if(beanMap == null || beanMap.isEmpty())return;
		Iterator<Entry<String, Object>> iterator = beanMap.entrySet().iterator();
		while(iterator.hasNext()){
			if(iterator.next().getValue() == null){
				iterator.remove();
			}
		}
	}

}
