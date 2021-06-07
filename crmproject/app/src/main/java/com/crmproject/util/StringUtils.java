package com.crmproject.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.TextView;


import com.crmproject.MyApplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 处理字符串工具类
 * 
 * @author
 * 
 */
public class StringUtils {

	/**
	 * 是否是中文
	 */
	public static boolean isChinese(char c) {

		return Character.toString(c).matches("[\\u4E00-\\u9FA5]+");
	}

	/**
	 * 判断是否为空
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isNullOrEmpty(String text) {
		if (text == null || "".equals(text.trim()) || text.trim().length() == 0
				|| "null".equals(text.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获得MD5加密字符串
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}

	/**
	 * 对流转化成字符串
	 * 
	 * @param is
	 * @return
	 */
	public static String getStringByStream(InputStream is) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line + "\n");
			}
			return buffer.toString().replaceAll("\n\n", "\n");
		} catch (OutOfMemoryError o) {
			System.gc();
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 时间格式转换，yyyy-MM-dd xx:xx:xx为：MM-dd xx:xx: 不要年
	 * @param date
	 * @return
	 */
	public static String getStringForDate3(String date) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat f = new SimpleDateFormat("MM-dd HH:mm");
		Date d = new Date();
		try {
			d = formatter.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String dateString = f.format(d);
		return dateString;

	}


	/**
	 * 得到字符串长度
	 * 
	 * @param text
	 * @return
	 */
	public static int getCharCount(String text) {
		String Reg = "^[\u4e00-\u9fa5]{1}$";
		int result = 0;
		for (int i = 0; i < text.length(); i++) {
			String b = Character.toString(text.charAt(i));
			if (b.matches(Reg))
				result += 2;
			else
				result++;
		}
		return result;
	}

	/**
	 * 按字节截取字符串
	 *
	 * @param orignal
	 *            原始字符串
	 * @param
	 *
	 * @return 截取后的字符串
	 * @throws UnsupportedEncodingException
	 *             使用了JAVA不支持的编码格式
	 */
	public static int substring(String orignal)
			throws UnsupportedEncodingException {
		// 原始字符不为null，也不是空字符串
		int num = 0;
		if (null != orignal && !"".equals(orignal)) {
			// 将原始字符串转换为GBK编码格式
			String orignal_byte = new String(orignal.getBytes("UTF-8"), "UTF-8");
			StringBuffer buff = new StringBuffer();
			char c;

			String s = "";
			for (int i = 0; i < orignal_byte.length(); i++) {
				// charAt(int index)也是按照字符来分解字符串的
				if (orignal_byte.length() > i) {
					c = orignal_byte.charAt(i);
					buff.append(c);
					if (isChineseChar(c)) {// 遇到中文汉字，字节总数+2
						num += 2; // 一般汉字在utf-8中为3个字节长度
					} else {
						num += 1;
					}

				}

			}
			// 要截取的字节数大于0，且小于原始字符串的字节数
		}
		return num;
	}
	public static boolean isChineseChar(char c) {
		// 如果字节数大于1，是汉字
		try {
			return String.valueOf(c).getBytes("UTF-8").length > 1;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}



	/**
	 * 获取截取后的字符串
	 * 
	 * @param text
	 *            原字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String getSubString(String text, int length) {
		return getSubString(text, length, true);
	}

	/**
	 * 获取截取后的字符串
	 * 
	 * @param text
	 *            原字符串
	 * @param length
	 *            截取长度
	 * @param isOmit
	 *            是否加上省略号
	 * @return
	 */
	public static String getSubString(String text, int length, boolean isOmit) {
		if (isNullOrEmpty(text)) {
			return "";
		}
		if (getCharCount(text) <= length + 1) {
			return text;
		}

		StringBuffer sb = new StringBuffer();
		String Reg = "^[\u4e00-\u9fa5]{1}$";
		int result = 0;
		for (int i = 0; i < text.length(); i++) {
			String b = Character.toString(text.charAt(i));
			if (b.matches(Reg)) {
				result += 2;
			} else {
				result++;
			}

			if (result <= length + 1) {
				sb.append(b);
			} else {
				if (isOmit) {
					sb.append("...");
				}
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * 电话号码验证
	 * 
	 * @param phoneNumber
	 *            手机号码
	 * @return
	 */
	public static boolean validatePhoneNumber(String phoneNumber) {
		Pattern pattern = Pattern
				.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(14[0-9])|(17[0-9]))\\d{8}$");
		Matcher m = pattern.matcher(phoneNumber);
		return m.matches();
	}

	/**
	 * 邮箱验证
	 * 
	 * @param mail
	 *            邮箱
	 * @return
	 */
	public static boolean validateEmail(String mail) {
		Pattern pattern = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher m = pattern.matcher(mail);
		return m.matches();
	}

	/**
	 * 验证字符串内容是否合法
	 * 
	 * @param content
	 *            字符串内容
	 * @return
	 */
	public static boolean validateLegalString(String content) {
		String illegal = "`~!#%^&*=+\\|{};:'\",<>/?○●★☆☉♀♂※¤╬の〆";
		boolean legal = true;
		L1: for (int i = 0; i < content.length(); i++) {
			for (int j = 0; j < illegal.length(); j++) {
				if (content.charAt(i) == illegal.charAt(j)) {
					legal = false;
					break L1;
				}
			}
		}
		return legal;
	}

	/**
	 * 获取更新的时间
	 * 
	 * @param dateStr
	 * @return
	 * @throws Exception
	 */
	public static String getCreateString(String dateStr) {
		if (dateStr != null && !"".equals(dateStr)) {
			try {
				if (dateStr.indexOf(".") > -1) {
					dateStr = dateStr.substring(0, dateStr.indexOf("."));
				}
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date = sdf.parse(dateStr);
				Calendar calendar = Calendar.getInstance();

				int oneMinuteUnit = 60;
				int oneHourUnit = 60 * 60;
				int oneDayUnit = 60 * 60 * 24;
				long i = (calendar.getTimeInMillis() - date.getTime()) / 1000;
				if (i < oneMinuteUnit && i > 0) {
					return i + "秒前";
				} else if (i < oneHourUnit && i > oneMinuteUnit) {
					return i / 60 + "分钟前";
				} else if (i < oneDayUnit && i > oneHourUnit) {
					return (i / (60 * 60)) + "小时前";
				} else {
					return dateStr;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取更新的时间
	 * 
	 * @param dateStr
	 * @return
	 * @throws Exception
	 */
	public static String getCreateString(Date date) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (calendar.get(Calendar.YEAR) - (date.getYear() + 1900) > 0) {
			return sdf.format(date);
		} else if (calendar.get(Calendar.MONTH) - date.getMonth() > 0) {
			return sdf.format(date);
		} else if (calendar.get(Calendar.DAY_OF_MONTH) - date.getDate() > 0) {
			return sdf.format(date);
		} else if (calendar.get(Calendar.HOUR_OF_DAY) - date.getHours() > 0) {
			int i = calendar.get(Calendar.HOUR_OF_DAY) - date.getHours();
			return i + "小时前";
		} else if (calendar.get(Calendar.MINUTE) - date.getMinutes() > 0) {
			int i = calendar.get(Calendar.MINUTE) - date.getMinutes();
			return i + "分钟前";
		} else if (calendar.get(Calendar.SECOND) - date.getSeconds() > 0) {
			int i = calendar.get(Calendar.SECOND) - date.getSeconds();
			return i + "秒前";
		} else {
			return sdf.format(date);
		}
	}


	/**
	 * 对搜房的图片进行动态调整
	 *
	 * @param coverimg
	 * @param width
	 * @param height
	 * @return
	 */
	public static String getImgPath(String coverimg, int width, int height, boolean... iscrop) {
		if (isNullOrEmpty(coverimg)) {
			return "";
		}
		if (!coverimg.startsWith("http")) {
			return coverimg;
		}
		try {
			URL url = new URL(coverimg);
			if (url.getHost().indexOf("soufun") == -1) {
				return coverimg;
			}
		} catch (MalformedURLException e) {
			return "";
		}

		String http = coverimg.substring(7);
		String head = http.substring(0, http.indexOf("/"));
		String xurl = coverimg.replace("http://" + head, "");
		xurl = xurl.substring(0, xurl.lastIndexOf("."));
		String xend = coverimg.substring(coverimg.lastIndexOf("."));
		String h = String.valueOf(height);
		if ((width == 75 && height == 100) || (iscrop != null && iscrop.length > 0)) {
		} else {
			h = h + "c4";
		}
		// /viewimage/agents/2012_06/26/20/70/16/bj/houseinfo/401200983100/120x90
		if (width == -1 || height == -1) {
			if (coverimg.indexOf("viewimage") > -1) {
				coverimg = "http://" + head + xurl.substring(10, xurl.lastIndexOf("/")) + xend;
			} else {
				coverimg = "http://" + head + xurl + xend;
			}
		} else {
			if (coverimg.indexOf("viewimage") > -1) {
				coverimg = coverimg.substring(0, coverimg.lastIndexOf("/")) + "/" + width + "x" + h + xend;
			} else {
				coverimg = "http://" + head + "/viewimage" + xurl + "/" + width + "x" + h + xend;
			}
		}
		UtilsLog.d("img===", coverimg);
		return coverimg;
	}


	/**
	 * 如果为空显示暂无信息
	 * 
	 * @param tv
	 *            控件名
	 * @param str
	 *            信息
	 */
	public static void viewText(TextView tv, String str) {
		if (isNullOrEmpty(str)) {
			tv.setText("暂无资料");
		} else {
			tv.setText(str);
		}
	}

	/**
	 * 对流转化成字符串
	 * 
	 * @param is
	 * @return
	 */
	public static String getContentByString(InputStream is) {
		try {
			if (is == null)
				return null;
			byte[] b = new byte[1024];
			int len = -1;
			StringBuilder sb = new StringBuilder();
			while ((len = is.read(b)) != -1) {
				sb.append(new String(b, 0, len));
			}
			return sb.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * 截取字符串，去掉sign后边的
	 * 
	 * @param source
	 *            原始字符串
	 * @param sign
	 * @return
	 */
	public static String splitByIndex(String source, String sign) {
		String temp = "";
		if (isNullOrEmpty(source)) {
			return temp;
		}
		int length = source.indexOf(sign);
		if (length > -1) {
			temp = source.substring(0, length);
		} else {
			return source;
		}
		return temp;
	}

	/**
	 * 保留小数点后一位
	 * 
	 * @param d
	 * @return
	 * @throws Exception
	 */
	public static String formatNumber(double d) {
		try {
			DecimalFormat df = new DecimalFormat("#,##0.0");
			return df.format(d);
		} catch (Exception e) {
		}
		return "";
	}

	public static String formatNumber(String d) {
		return formatNumber(Double.parseDouble(d));
	}

	/*
	 * 获取字符串格式的当前时间
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/*
    * 获取字符串格式的当前时间(yyyy-MM-dd)
    */
	public static String getStringDate1() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/*
     * 获取字符串格式的当前时间(yyyyMMddHH)
     */
	public static String getStringDateTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/*
	 * 获取字符串格式的当前时间(yyyyMMdd)
	 */
	public static String getStringDate2() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取前n天日期、后n天日期
	 *
	 * @param distanceDay 前几天 如获取前7天日期则传-7即可；如果后7天则传7
	 * @return
	 */
	public static String getOldDate(int distanceDay) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
		Date endDate = null;
		try {
			endDate = dft.parse(dft.format(date.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dft.format(endDate);
	}

	/*
	 * 时间格式转换，yyyy-MM-dd xx:xx:xx为：yyyy-MM-dd
	 */
	public static String getStringDate(String date) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		try {
			d = formatter.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String dateString = formatter.format(d);
		return dateString;

	}

	/*
	 * 截取sign后边的数字
	 */
	public static String getStringNum(String str, String sign) {
		String reg = ":split:";
		return str.replace(sign, reg).replaceAll(reg + "\\d+", "")
				.replaceAll(" ", "").trim();

	}

	/*
	 * 时间格式转换，yyyy-MM-dd xx:xx:xx为：MM-dd xx:xx 不要年和秒
	 */
	public static String getStringForDate(String date) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat f = new SimpleDateFormat("MM-dd HH:mm");
		Date d = new Date();
		try {
			d = formatter.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String dateString = f.format(d);
		return dateString;

	}

	/*
	 * 时间格式转换，yyyy-MM-dd xx:xx:xx为：MM-dd xx:xx 不要年和秒
	 */
	public static String getStringForDate4(String date) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
		Date d = new Date();
		try {
			d = formatter.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String dateString = f.format(d);
		return dateString;

	}

	/*
	 * 判断价格是否为0或空
	 */
	public static boolean isPriceZero(String price) {
		if (isNullOrEmpty(price)) {
			return true;
		}
		price = splitByIndex(price, ".");
		if ("0".equals(price)) {
			return true;
		}
		return false;

	}

	/**
	 * 取价格的整数，去掉单位
	 * 
	 * @param price
	 * @return
	 */
	public static String getPrice(String price) {
		Pattern p = Pattern.compile("^\\d+");
		Matcher m = p.matcher(price);
		if (m.find()) {
			return m.group();
		}
		return "";
	}

	/**
	 * 判断是否全为数字
	 * 
	 * @param content
	 * @return
	 */
	public static boolean isAllNumber(String content) {
		boolean isAllNumber = true;
		if (isNullOrEmpty(content)) {
			return false;
		}
		for (int i = 0; i < content.length(); i++) {
			if (content.charAt(i) < '0' || content.charAt(i) > '9') {
				isAllNumber = false;
			}
		}
		return isAllNumber;
	}

	/**
	 * 整数转字节数组
	 * 
	 * @param i
	 * @return
	 */
	public static byte[] intToByte(int i) {
		byte[] bt = new byte[4];
		bt[0] = (byte) (0xff & i);
		bt[1] = (byte) ((0xff00 & i) >> 8);
		bt[2] = (byte) ((0xff0000 & i) >> 16);
		bt[3] = (byte) ((0xff000000 & i) >> 24);
		return bt;
	}

	/**
	 * 字节数组转整数
	 * 
	 * @param bytes
	 * @return
	 */
	public static int bytesToInt(byte[] bytes) {
		int num = bytes[0] & 0xFF;
		num |= ((bytes[1] << 8) & 0xFF00);
		num |= ((bytes[2] << 16) & 0xFF0000);
		num |= ((bytes[3] << 24) & 0xFF000000);
		return num;
	}

	public static CharSequence formateTime(int time) {
		int mHour, mMinute, mSecond;// 时分秒
		StringBuilder sb_time = new StringBuilder();
		mHour = time / 3600;
		if (mHour > 9)
			sb_time.append(mHour + ":");
		else if (mHour >= 0) {
			sb_time.append("0" + mHour + ":");
		} else {
			sb_time.append("00:");
		}
		time = time % 3600;
		mMinute = time / 60;
		if (mMinute > 9) {
			sb_time.append(mMinute + ":");
		} else if (mMinute >= 0) {
			sb_time.append("0" + mMinute + ":");
		} else {
			sb_time.append("00:");
		}
		mSecond = time % 60;
		if (mSecond > 9) {
			sb_time.append(mSecond);
		} else if (mSecond >= 0) {
			sb_time.append("0" + mSecond);
		}
		return sb_time.toString();
	}

	/**
	 * 判断str1和str2是否相同
	 *
	 * @param str1
	 *            str1
	 * @param str2
	 *            str2
	 * @return true or false
	 */
	public static boolean equals(String str1, String str2) {
		return str1 == str2 || str1 != null && str1.equals(str2);
	}

	public static String endtimeForMonth(String startTime, int month) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			long startTimeL = format.parse(startTime).getTime();
			long endTimeL = startTimeL + month * 30 * 24 * 60 * 60 * 1000L-24 * 60 * 60 * 1000L;
			return format.format(new Date(endTimeL));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String endtimeForWeek(String startTime,int day) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			long startTimeL = format.parse(startTime).getTime();
			long endTimeL = startTimeL + day * 24 * 60 * 60 * 1000L;
			return format.format(new Date(endTimeL));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Long convertTimeToLong(String time) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.parse(time);
			return date.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	/**
	 * 判断是否为空或0
	 *
	 * @param num
	 * @return
	 */
	public static boolean isNumZero(String num) {
		if (StringUtils.isNullOrEmpty(num)
				|| !StringUtils.canParseDouble(num)
				|| Math.abs(Double.parseDouble(num)) == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 验证一个字符串是否能解析成双精度浮点数
	 *
	 * @param numberStr
	 * @return
	 */
	public static boolean canParseDouble(String numberStr) {
		try {
			Double.parseDouble(numberStr);
			return true;
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * 字符串 转换 double
	 * 需要转化的数据
	 *
	 * @param
	 * @return 转化后的double数据
	 */
	public static double parseDouble(String s) {
		String ss = "";
		if (!isNullOrEmpty(s) && s.contains(",")) {
			int len = s.split(",").length;
			for (int i = 0; i < len; i++) {
				ss = ss + s.split(",")[i];
			}
			return canParseDouble(ss) ? Double.parseDouble(ss) : 0;
		}
		return canParseDouble(s) ? Double.parseDouble(s) : 0;
	}

	/**
	 * 保留小数点后两位
	 */
	public static String formatNumber2(double d) {
		try {
			DecimalFormat df = new DecimalFormat("0.00");
			df.setRoundingMode(RoundingMode.HALF_UP);
			return df.format(d);
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * 验证一个字符串是否能解析成整数
	 *
	 * @param numberStr
	 * @return
	 */
	public static boolean canParseInt(String numberStr) {
		try {
			Integer.parseInt(numberStr);
			return true;
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * 四舍五入
	 *
	 * @param num     要进行四舍五入的数字
	 * @param decimal 要保留的小数位数
	 * @return
	 */
	public static String numRound(String num, int decimal) {
		if (StringUtils.isNullOrEmpty(num) || !canParseDouble(num)) {
			num = "0";
		}
		BigDecimal b = new BigDecimal(num);
		num = b.setScale(decimal, RoundingMode.HALF_UP).toString();
		return num;
	}

	/**
	 * 数字字符串中以0开头的数字,去掉0
	 *
	 * @return
	 */
	public static String getMonthNum(String month) {
		int index = 0;
		char[] chars = month.toCharArray();
		for (int i = 0; i < month.length(); i++) {
			if ('0' != chars[i]) {
				index = i;
				break;
			}
		}
		return month.substring(index, month.length());
	}

	/**
	 * 判断是否是来自搜房的链接地址
	 */
	public static Boolean isSoufunUrl(String url) {
		if (StringUtils.isNullOrEmpty(url))
			return false;

		try {
			URL newUrl = new URL(url);
			url = newUrl.getHost();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (url.contains("fang.com") || url.contains("soufun.com") || url.contains("txdai.com") || url.contains("fangtx.com")
				|| url.contains("soufun.cn") || url.contains("fang.cn") || url.contains("youtx.com") || url.contains("soufunimg.com")) {
			return true;
		} else {
			return false;
		}

	}
	/**
	 * 获取异常的原因
	 * @param exc 异常
	 * @return 异常的原因
	 */

	public static String getCause(Exception exc){
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n");
		sb.append("---------------Exception-------------------");
		sb.append("\r\n");
		Writer writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		exc.printStackTrace(pw);
		Throwable excCause = exc.getCause();
		while (excCause != null) {
			excCause.printStackTrace(pw);
			excCause = excCause.getCause();
		}
		pw.close();
		String result = writer.toString();
		sb.append(result);
		sb.append("\r\n");
		sb.append("----------------end--------------------");
		sb.append("\r\n");
		return sb.toString();
	}
	/**
	 * 判断闰年平年
	 *
	 * @param year
	 * @return
	 */
	public static boolean judgeYear(int year) {
		boolean flag = false;
		if (year % 100 != 0) {
			if (year % 4 == 0) {
				flag = true;
			} else {
				flag = false;
			}
		} else if (year % 100 == 0) {
			if (year % 400 == 0) {
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}
	/**
	 * 获取字符串格式的当前时间
	 */
	public static String getCurrentDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}




	public static int dip2px(float dpValue) {
		final float scale = MyApplication.getSelf().getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}


	/**
	 * 校验电话号
	 * 获取验证码
	 *
	 * @param str
	 * @return true 可以获取验证码 false不可以
	 */
	public static boolean isGetVerifiCode(Context context, String str) {

		if (!StringUtils.isNullOrEmpty(str) && str.length() > 10 && str.length() < 14) {
			if (str.length() == 11) {
				if (!str.substring(0, 1).equals("1")) {
					Utils.toast(context, "请输入正确的手机号");
					return false;
				} else {
					return true;
				}
			} else if (str.length() == 12) {
				Utils.toast(context, "请输入正确的手机号");
				return false;
			} else {
				if (!str.substring(0, 5).equals("00852") && !str.substring(0, 5).equals("00853")) {
					Utils.toast(context, "请输入正确的手机号");
					return false;
				} else {
					return true;
				}
			}
		} else {
			Utils.toast(context, "请输入正确的手机号");
			return false;
		}
	}

	/**获取url的域名，为webview使用*/
	public static String getDomain(String url) {
		Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(url);
		boolean bool = matcher.find();
		if (bool) {
			return matcher.group();
		} else {
			return "";
		}
	}

	/**
	 * 验证密码是否符合要求
	 * 由数字和字母组成，并且要同时含有数字和字母，且长度要在8-16位之间,可包含字符
	 */

	public static boolean checkPassword(String password){
		String regex = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z^ -~]{8,16}$";
		return password.matches(regex);
	}


	/**
	 * 计算几个月后的时间
	 * dayType: 1：30天   2：31天   3: 30天,-1   4: 31天,-1
	 * 时间类型 yyyy-MM-dd
	 */
	public static String endtimeForMonth(String startTime, int month,int dayType) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			long startTimeL = format.parse(startTime).getTime();
			long endTimeL = 0;
			if(1 == dayType){
				endTimeL = startTimeL + month * 30 * 24 * 60 * 60 * 1000L;
			}else if(2 == dayType){
				endTimeL = startTimeL + month * 31 * 24 * 60 * 60 * 1000L;
			}else if(3 == dayType){
				endTimeL = startTimeL + month * 30 * 24 * 60 * 60 * 1000L-24 * 60 * 60 * 1000L;
			}else{
				endTimeL = startTimeL + month * 31 * 24 * 60 * 60 * 1000L-24 * 60 * 60 * 1000L;
			}
			return format.format(new Date(endTimeL));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 给一个当前时间，获取n个自然月后的时间
	 * @param dateNow 当前时间
	 * @param month 自然月数
	 * canMinus true: -1  false: 不处理
	 * @return
	 */
	public static String calculateDateByCalendarMonth(String dateNow , int month , boolean canMinus){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(dateNow);
			Calendar calendar =Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, month);
			String str=sdf.format(calendar.getTime());
			if(canMinus){
				long endTimeL = sdf.parse(str).getTime() - 24 * 60 * 60 * 1000L;
				str = sdf.format(new Date(endTimeL));
			}

			return str;
		} catch (ParseException e) {
			return null;
		}
	}


	/**
	 * 每月30天
	 *
	 * @param startTime
	 * @param month
	 * @param days      加n天或减n天
	 * @return
	 */
	public static String endtimeForMonth30(String startTime, int month, int days) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			long startTimeL = format.parse(startTime).getTime();
			long endTimeL = startTimeL + month * 30 * 24 * 60 * 60 * 1000L + days * 24 * 60 * 60 * 1000L;
			return format.format(new Date(endTimeL));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 每月31天计算
	 *
	 * @param startTime
	 * @param month
	 * @param days      加n天或减n天
	 * @return
	 */
	public static String endtimeForMonth31(String startTime, int month, int days) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			long startTimeL = format.parse(startTime).getTime();
			long endTimeL = startTimeL + month * 31 * 24 * 60 * 60 * 1000L + days * 24 * 60 * 60 * 1000L;
			return format.format(new Date(endTimeL));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取自然月日期
	 *
	 * @param inputDate
	 * @param month
	 * @param days      最后日期加n天或减n天
	 * @return
	 */
	public static String getAfterMonth(String inputDate, int month, int days) {

		Calendar c = Calendar.getInstance();//获得一个日历的实例
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(inputDate);//初始日期

		} catch (Exception e) {

		}
		c.setTime(date);//设置日历时间
		c.add(Calendar.MONTH, month);//在日历的月份上增加6个月
		c.set(Calendar.DATE, c.get(Calendar.DATE) + days);

		String strDate = sdf.format(c.getTime());//的到你想要得6个月后的日期
		return strDate;
	}

	/**
	 * 获取两个时间中较小的时间
	 * @param dateStr1  时间一
	 * @param dateStr2 时间二
	 * @return 较小的时间
	 */
	public static String getMinDate(String dateStr1,String dateStr2){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = sdf.parse(dateStr1);
			Date date2 = sdf.parse(dateStr2);
			if (date1.getTime() < date2.getTime()){
				return dateStr1;
			}else {
				return dateStr2;
			}
		} catch (Exception e) {
			Date date = new Date();
			return sdf.format(date);
		}
	}

	/**
	 * 获取指定时间的之前几天
	 * @param nowStr
	 * @param day
	 * @return
	 */
	public static String getDateBefore(String nowStr,int day){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date nowDate = sdf.parse(nowStr);
			Calendar now =Calendar.getInstance();
			now.setTime(nowDate);
			now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
			String str=sdf.format(now.getTime());
			return str;
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取指定时间的之后几天
	 * @param nowStr
	 * @param day
	 * @return
	 */
	public static String getDateAfter(String nowStr,int day){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date nowDate = sdf.parse(nowStr);
			Calendar now =Calendar.getInstance();
			now.setTime(nowDate);
			now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
			String str=sdf.format(now.getTime());
			return str;
		} catch (ParseException e) {
			return null;
		}
	}


	/**
	 * 获取连续几个自然月的天数
	 * @param now 现在的时间
	 * @param months 自然月数
	 * @return 天数
	 */
	public static int getContinuityNaturalMonthDays(String now,int months){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date nowDate = sdf.parse(now);
			Calendar nowCalendar =Calendar.getInstance();
			nowCalendar.setTime(nowDate);
			nowCalendar.add(Calendar.MONTH,months);
			Date endDate = nowCalendar.getTime();

			Calendar fromCalendar = Calendar.getInstance();
			fromCalendar.setTime(nowDate);
			fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
			fromCalendar.set(Calendar.MINUTE, 0);
			fromCalendar.set(Calendar.SECOND, 0);
			fromCalendar.set(Calendar.MILLISECOND, 0);

			Calendar toCalendar = Calendar.getInstance();
			toCalendar.setTime(endDate);
			toCalendar.set(Calendar.HOUR_OF_DAY, 0);
			toCalendar.set(Calendar.MINUTE, 0);
			toCalendar.set(Calendar.SECOND, 0);
			toCalendar.set(Calendar.MILLISECOND, 0);

			return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
		} catch (Exception e) {
			return 0;
		}
	}


	/**
	 * 判断日期大小
	 *
	 * @param str1 当前日期
	 * @param str2 选择日期
	 * @return str1>str2
	 */
	public static boolean isDateBigger(String str1, String str2) {

		boolean isBigger = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt1 = null;
		Date dt2 = null;

		try {
			dt1 = sdf.parse(str1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			dt2 = sdf.parse(str2);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (dt2.getTime() <= dt1.getTime() ) {
			isBigger = true;
		}
		return isBigger;
	}

	/**
	 * 获取连续几个自然月的天数
	 * @param now 现在的时间
	 * @param months 自然月数
	 * @return 天数
	 */
	public static int getContinuityNaturalMonthDaysInt(String now,int months) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date nowDate = sdf.parse(now);
			Calendar nowCalendar = Calendar.getInstance();
			nowCalendar.setTime(nowDate);
			nowCalendar.add(Calendar.MONTH, months);
			Date endDate = nowCalendar.getTime();

			Calendar fromCalendar = Calendar.getInstance();
			fromCalendar.setTime(nowDate);
			fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
			fromCalendar.set(Calendar.MINUTE, 0);
			fromCalendar.set(Calendar.SECOND, 0);
			fromCalendar.set(Calendar.MILLISECOND, 0);

			Calendar toCalendar = Calendar.getInstance();
			toCalendar.setTime(endDate);
			toCalendar.set(Calendar.HOUR_OF_DAY, 0);
			toCalendar.set(Calendar.MINUTE, 0);
			toCalendar.set(Calendar.SECOND, 0);
			toCalendar.set(Calendar.MILLISECOND, 0);

			return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * java将金额数字转换成对应的汉字
	 * 10002345
	 * 一千万二千三百四十五
	 * @param input
	 * @return
	 */
	public static String num2Word(String input){
		String out = "";
		if (null == input) {
			return out;
		}
		String[] num = {"零","一","二","三","四","五","六","七","八","九","十"};
		String[] unit = {"","十","百","千","万","十","百","千","亿"};
		String[] result = new String[input.length()];
		int length = result.length;
		for(int i = 0; i< length; i++) {
			result[i] = String.valueOf(input.charAt(i));
		}
		for(int i = 0; i< length; i++) {
			int back;
			if(!result[i].equals("0")) {
				back = length - i - 1;
				out += num[Integer.parseInt(result[i])];
				out += unit[back];
			} else {
				//最后一位不考虑
				if(i == (length - 1)) {
					if(length > 4 && result[length - 1].equals("0") && result[length - 2].equals("0") && result[length - 3].equals("0") && result[length - 4].equals("0")){
						out += unit[4];
					}
				} else {
					//九位数，千万，百万，十万，万位都为0，则不加“万”
					if(length == 9 && result[1].equals("0") && result[2].equals("0") && result[3].equals("0") && result[4].equals("0")) {

					} else {
						//大于万位，连着的两个数不为0，万位等于0则加上“万”
						if(length > 4 && !result[i+1].equals("0") && result[length -5].equals("0")){
							out += unit[4];
						}
					}
					//万位之后的零显示
					if(i == length -4 && !result[i+1].equals("0")) {
						out += num[0];
					}
				}
			}
		}
		return out;
	}

	public static boolean isAppAvilible(Context context,String packageName) {
		final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
		List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
		if (pinfo != null) {
			for (int i = 0; i < pinfo.size(); i++) {
				String pn = pinfo.get(i).packageName;
				if (pn.equals(packageName)) {
					return true;
				}
			}
		}

		return false;
	}
}
