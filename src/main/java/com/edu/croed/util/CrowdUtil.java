package com.edu.croed.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import com.edu.croed.constant.CrowdConstant;

/**
 * @author wyg_edu
 * @date 2020年5月7日 下午9:30:20
 * @version v1.0 
 * 工具类
 */
public class CrowdUtil {

	/**
	 * 判断请求是否ajax请求
	 * 
	 * @param request
	 * @return
	 */
	public static boolean judgeReqyestType(HttpServletRequest request) {
		// 1、获取请求消息头
		String acceptHeader = request.getHeader("Accept");
		String xRequestHeader = request.getHeader("X-Requested-With");
		// 2、判断
		return (acceptHeader != null && acceptHeader.contains("application/json")
				|| (xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest")));
	}
	
	
	/**
	 * 对字符串进行加密
	 * @param source 铭文 
	 * @return
	 */
	public static String md5(String source) {
		
		// 1、判断原文是否有效
		if(null == source || source.length() == 0) {
			// 2、如果不是有效字符串，则抛出异常
			throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
		}
		
		try {
			// 3、获取MessageDigest对象
			String algoruthm = "md5";
			MessageDigest digest = MessageDigest.getInstance(algoruthm);
			
			// 4、获取铭文字符串对应的数组
			byte[] input = source.getBytes();
			
			// 5、进行加密
			byte[] output = digest.digest(input);
			
			// 6、创建BigInteger
			int signum = 1; // 代表正数
			BigInteger bigInteger = new BigInteger(signum, output);
			
			// 7、按照16进制将其转换成字符串
			int radix = 16;
			String encoded = bigInteger.toString(radix).toUpperCase();
			
			// 8、返回加密串
			return encoded;
			
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
