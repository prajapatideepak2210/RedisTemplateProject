package com.bridgeLabz.RedisTemplateProject.extractServices;

import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;
import com.textrazor.TextRazor;
import com.textrazor.annotations.Entity;
import com.textrazor.annotations.AnalyzedText;

/**
 *
 * Extracts noun phrases from a sentence. To create sentences using OpenNLP use
 * the SentenceDetector classes.
 */
public class KeyWordExtractor {

	private static final String API_KEY = "a664884404d69624720e01a8324466ee4f46fbfe1042e2c4b50e922d";

	public static AnalyzedText getKeyWords(String string) throws NetworkException, AnalysisException {

		TextRazor client = new TextRazor(API_KEY);
		client.addExtractor("words");
		client.addExtractor("entities");

		AnalyzedText response = client.analyze(string);
		for (Entity entity : response.getResponse().getEntities()) {
			System.out.println("Matched Entity: " + entity.getEntityId());
		}
		
		return response;
	}

}
