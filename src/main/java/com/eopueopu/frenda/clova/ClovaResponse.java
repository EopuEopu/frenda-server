package com.eopueopu.frenda.clova;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClovaResponse {
	private Document document;

	public Document getDocument() {
		return document;
	}
}
