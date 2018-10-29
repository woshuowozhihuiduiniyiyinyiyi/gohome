package com.hj.tj.gohome.utils;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.hj.tj.gohome.exception.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;


public class JwtUtil {

	public static final Logger log = LoggerFactory.getLogger(JwtUtil.class);
	public static final String SID = "sid";
	public static final String USER_ID = "user_id";
	public static final String KUAI_ID = "kuai_id";
	public static final String PHONE = "phone";
	public static final String IMEI = "imei";
	public static final String MAC = "mac";
	public static final String CREATED_AT = "created_at";

	private static String jwtKey = "jfdsaafauefas125azv^&&&";

	public static Map<String, Object> decode(String signStr) throws JwtException {
		try {
			return new JWTVerifier(jwtKey).verify(signStr);
		} catch (InvalidKeyException | NoSuchAlgorithmException | IllegalStateException | SignatureException
				| IOException | JWTVerifyException e) {
			throw new JwtException(e);
		}
	}

	public static String getKuaiId(String sid) throws Exception {
		Map<String, Object> sidMap = JwtUtil.decode(sid);
		return (String) sidMap.get(KUAI_ID);
	}
	
	public static Integer getUserId(String sid) throws Exception {
		if(StringUtil.isBlank(sid)){
			return 0;
		}
		Map<String, Object> sidMap = JwtUtil.decode(sid);
		return (Integer) sidMap.get(USER_ID);
	}
	
	public static String getImei(String sid) throws Exception {
		Map<String, Object> sidMap = JwtUtil.decode(sid);
		return (String) sidMap.get(IMEI);
	}
	
	public static String getMac(String sid) throws Exception {
		Map<String, Object> sidMap = JwtUtil.decode(sid);
		return (String) sidMap.get(MAC);
	}
	
	public static long getCreatedAt(String sid) throws Exception{
		Map<String, Object> sidMap = JwtUtil.decode(sid);
		long createdAt = (Long) sidMap.get(JwtUtil.CREATED_AT);
		return createdAt;
	}

	public static String encode(int userId, String phone, String imei) {
		Map<String, Object> sidMap = new HashMap<String, Object>();
		sidMap.put(USER_ID, userId);
		sidMap.put(PHONE, phone);
		sidMap.put(IMEI, imei);
		sidMap.put(CREATED_AT, System.currentTimeMillis());
		JWTSigner signer = new JWTSigner(jwtKey);
		String sid = signer.sign(sidMap);
		return sid;
	}
	
	public static String encode(int userId) {
		return encode(userId, "", "");
	}

	public static String encode(int userId, String phone) {
		return encode(userId, phone, "");
	}

	public static void  main(String[] args) throws Exception {
		//System.out.println(JwtUtil.encode(6373, "empty", "869157024059774"));
		System.out.println(JwtUtil.decode("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpbWVpIjoiODYyNTU1MDI0Mjg1NTQ2IiwidXNlcl9pZCI6MTMwNDk2MDYzNCwicGhvbmUiOiJlbXB0eSJ9.R7U9uRf-bkR1nZhswr2IYbIUVnPPPUw1ECl0jEFSueY"));
		//System.out.println(AESUtil.encrypt("1918"));

	}

}
