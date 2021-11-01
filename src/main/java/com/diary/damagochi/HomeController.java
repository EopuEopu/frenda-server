package com.diary.damagochi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testApi(Model model){
		String urlStr = "https://naveropenapi.apigw.ntruss.com/sentiment-analysis/v1/analyze";
		URL url;
		String result="";
		String errorMsg = "";
		try {
			url = new URL(urlStr);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "rs0cn7yw25");
			conn.setRequestProperty("X-NCP-APIGW-API-KEY", "HdLZw2PVtnoJ7edZBV6uNosbVvlg8ViEHlNZmy39");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setDoOutput(true);
	        conn.setDoInput(true);
	        
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
	        bw.write(makeContent().toString());
	        bw.flush();
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        
	        result = br.readLine().toString();
	        
	        bw.close();
	        br.close();
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(errorMsg);
		}
		
		model.addAttribute("jsonString", result);
		
		return "home";
	}
	
	private JSONObject makeContent() {
		JSONObject content = new JSONObject();

		content.put("content","¾È³ç");
		
		return content;
	}
	
}
