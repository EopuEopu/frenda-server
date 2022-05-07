package com.eopueopu.frenda.handler.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EncryptHandler {
	
	@Value("#{frenda['frenda.diary.start']}")
	private String subStart;
	
	@Value("#{frenda['frenda.diary.end']}")
	private String subEnd;
	
	private String alg = "AES/CBC/PKCS5Padding";
	
	public String encryptContent(String key, String content) throws Exception {
		key = key.substring(Integer.parseInt(subStart), Integer.parseInt(subEnd));
		Cipher cipher = Cipher.getInstance(alg);
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
		IvParameterSpec paramSpec = new IvParameterSpec(key.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);
		
		byte[] encryptedContent = cipher.doFinal(content.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(encryptedContent);
	}
	
	public String decryptContent(String key, String encryptedContent) throws Exception {
		key = key.substring(Integer.parseInt(subStart), Integer.parseInt(subEnd));
		Cipher cipher = Cipher.getInstance(alg);
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
		IvParameterSpec paramSpec = new IvParameterSpec(key.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
		
		byte[] decodedBytes = Base64.getDecoder().decode(encryptedContent);
		byte[] decryptedContent = cipher.doFinal(decodedBytes);
		
		return new String(decryptedContent, "UTF-8");
	}
}
