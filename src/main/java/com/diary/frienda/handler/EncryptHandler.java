package com.diary.frienda.handler;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptHandler {
	private String alg = "AES/CBC/PKCS5Padding";
	private String key;
	
	public EncryptHandler(String key) {
		this.key = key;
	}
	
	public String encryptContent(String content) throws Exception {
		Cipher cipher = Cipher.getInstance(alg);
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
		IvParameterSpec paramSpec = new IvParameterSpec(key.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);
		
		byte[] encryptedContent = cipher.doFinal(content.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(encryptedContent);
	}
	
	public String decryptContent(String encryptedContent) throws Exception {
		Cipher cipher = Cipher.getInstance(alg);
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
		IvParameterSpec paramSpec = new IvParameterSpec(key.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
		
		byte[] decodedBytes = Base64.getDecoder().decode(encryptedContent);
		byte[] decryptedContent = cipher.doFinal(decodedBytes);
		
		return new String(decryptedContent, "UTF-8");
	}
}
