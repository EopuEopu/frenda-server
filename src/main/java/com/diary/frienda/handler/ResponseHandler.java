package com.diary.frienda.handler;

import com.diary.frienda.response.Data;
import com.diary.frienda.response.Response;

public class ResponseHandler {
	public static Response failResponse() {
		return new Response(500, "FAIL", null);
	}
	
	public static Response failResponse(Data data) {
		return new Response(500, "FAIL", data);
	}
	
	public static Response successResponse(Data data) {
		return new Response(200, "SUCCESS", data);
	}
	
}
