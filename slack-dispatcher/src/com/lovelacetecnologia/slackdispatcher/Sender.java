package com.lovelacetecnologia.slackdispatcher;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
import org.json.JSONObject;

public class Sender {

	public static void main(String[] args) {

		String url = "https://hooks.slack.com/services/<token>";
		PostMethod post = new PostMethod(url);

		try {
			
			String message = "Hello world!";
	
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("channel", "#channel");
			jsonObject.put("username", "#name-bot");
			jsonObject.put("text", message);
			
			post.addParameter("payload", jsonObject.toString());
			post.getParams().setContentCharset("UTF-8");

			HttpClient httpClient = new HttpClient();
			int statusCode = httpClient.executeMethod(post);
			String response = post.getResponseBodyAsString();
			if (statusCode != HttpStatus.SC_OK) {
				System.out.println("Error sending message: " + response);
			}

		} catch (JSONException | IllegalArgumentException | IOException e) {
			System.err.println("Erro: " + e);
		} finally {
			post.releaseConnection();
		}
	}
}
