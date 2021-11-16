package com.diary.frienda;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.diary.frienda.clova.ClovaResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@PropertySource("classpath:/properties/frienda.properties")
public class ClovaHandler {
	@Value("${frienda.clova.url}")
	public String clova_url;
	
	@Value("${frienda.clova.id}")
	public String clova_id;
	
	@Value("${frienda.clova.key}")
	public String clova_key;
	
	public String callClovaSentiment(String diary) {		
		URL url;
		String result="";

		try {
			url = new URL(clova_url);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clova_id);
			conn.setRequestProperty("X-NCP-APIGW-API-KEY", clova_key);
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setDoOutput(true);
	        conn.setDoInput(true);
	        
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
	        bw.write(makeContent(diary));
	        bw.flush();
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        
	        result = br.readLine().toString();
	        bw.close();
	        br.close();
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ClovaResponse convertJsonToDocument(String json) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		ClovaResponse res = objectMapper.readValue(json, ClovaResponse.class);
		return res;
	}
	
	private String makeContent(String content) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"content\": \"").append(content).append("\"}");
		
		return sb.toString();
	}	
	
}
