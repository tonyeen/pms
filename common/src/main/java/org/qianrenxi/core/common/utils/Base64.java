package org.qianrenxi.core.common.utils;

import java.io.UnsupportedEncodingException;

public class Base64 {
	public static String getBase64(String s) throws UnsupportedEncodingException {
		byte[] bytes = org.apache.commons.codec.binary.Base64.encodeBase64(s.getBytes("utf-8"));
		return new String(bytes, "utf-8");
	}

	public static String getFromBase64(String s) throws UnsupportedEncodingException {
		byte[] bytes = s.getBytes("GBK");
		byte[] convertBytes = org.apache.commons.codec.binary.Base64.decodeBase64(bytes);
		return new String(convertBytes, "GBK");
	}
}
