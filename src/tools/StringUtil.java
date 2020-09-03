package tools;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 * @author 22219
 *
 */
public class StringUtil {

	/**
	 * 判断是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否不是空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if (str != null && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否可转换String为整形
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 获取字符串长度
	 * @param str
	 * @return
	 */
	public static int getStringLength(String str) {
		return str.length();
	}

	/**
	 * 判断是否是一个汉字
	 * @param c
	 * @return
	 */
	public static boolean isChineseChar(char c) {
		try {
			return String.valueOf(c).getBytes("GBK").length > 1;
		} catch (UnsupportedEncodingException e) {
			return false;
		}
		
	}

	/**
	 * 获得汉字的长度
	 * @param s
	 * @return
	 */
	public static int getChineseCount(String s) {
		char c;
		int chineseCount = 0;
		if (!"".equals("")) {
			try {
				s = new String(s.getBytes(), "GBK");
			} catch (UnsupportedEncodingException e) {
			}
		}
		
		for (int i = 0; i < s.length(); i++) {// for循环
			c = s.charAt(i); // 获得字符串中的每个字符
			if (isChineseChar(c)) {// 调用方法进行判断是否是汉字
				chineseCount++; // 等同于chineseCount=chineseCount+1
			}
		}
		return chineseCount; // 返回汉字个数
	}

	/**
	 * 查看字符串里有多少个空格
	 * @param s
	 * @return
	 */
	public static int getBlankCount(String s) {
		char ch;
		int blank = 0;
		for (int i = 0; i < s.length(); i++) {
			ch = s.charAt(i);
			if (ch == ' ')
				blank++;
		}
		return blank;
	}
}
