package com.diary.frienda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.diary.frienda.clova.Document;
import com.diary.frienda.db.test.Test;
import com.diary.frienda.db.test.TestDAOService;
import com.diary.frienda.request.AdditionDiary;
import com.diary.frienda.response.AdditionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
public class TestController {
	@Autowired
	private TestDAOService testDAO;
	
	@Autowired
	private ClovaHandler ch;
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeDiary() {
		return "write";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView showSents(){		
		TestHandler th = new TestHandler(testDAO);
		List<Test> tests = new ArrayList<Test>();
		int count = 0;
		
		ModelAndView mv = new ModelAndView();
		
		try {
			tests = th.getAllSentiments();
			count = th.getAllCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.setViewName("/list");
		mv.addObject("tests", tests);
		mv.addObject("count", count);
		mv.addObject("value", "ÀüÃ¼");
		
		return mv;
	}
	
	@RequestMapping(value = "diaryWrite.do", method = RequestMethod.POST)
	public String diaryWrite(String diary) throws Exception {
		insertAllToTest(diary);
		return "write";
	}
	
	@RequestMapping(value = "/diary", method = RequestMethod.POST)
	@ResponseBody
	public AdditionResponse additionDiary(@RequestParam("user_id") String user_id, @RequestBody final AdditionDiary addi) {				
		try {
			insertAllToTest(addi.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		AdditionResponse res = new AdditionResponse(200, 9999, "OK");
		return res;
	}
	
	@RequestMapping(value = "/listBySent", method = RequestMethod.GET)
	public ModelAndView showSentsByValue(@RequestParam("value") String value){		
		TestHandler th = new TestHandler(testDAO);
		List<Test> tests = new ArrayList<Test>();
		int count = 0;
		
		ModelAndView mv = new ModelAndView();
		
		try {
			tests = th.getResultsBySentiment(value);
			count = th.getCountBySentiment(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.setViewName("/list");
		mv.addObject("tests", tests);
		mv.addObject("count", count);
		mv.addObject("value", value);
		
		return mv;
	}
	
	private double doRound(double value) {
		return Math.round(value * 100) / 100.0;
	}
	
	private Document getDocumentFromDiary(String diary) throws JsonMappingException, JsonProcessingException {
		String response = ch.callClovaSentiment(diary);
		
		return ch.convertJsonToDocument(response).getDocument();
	}
	
	private void insertAllToTest(String diary) throws Exception {
		Document document = getDocumentFromDiary(diary);
		Test test = new Test();
		
		test.setContent(diary);
		test.setSentiment(document.getSentiment());
		test.setNegative_value(doRound(document.getConfidence().getNegative()));
		test.setNeutral_value(doRound(document.getConfidence().getNeutral()));
		test.setPositive_value(doRound(document.getConfidence().getPositive()));
		
		testDAO.insertAll(test);
	}
}
