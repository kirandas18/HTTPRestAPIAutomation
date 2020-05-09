package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	//1. GET Method without headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException
	{

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);//http get request
		CloseableHttpResponse closableHttpResponse = httpClient.execute(httpget);//Hit the get URL
		return closableHttpResponse;

	}


	//2. GET Method with headers
	public CloseableHttpResponse get(String url,HashMap<String,String> headerMap) throws ClientProtocolException, IOException
	{

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);//http get request

		for(Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closableHttpResponse = httpClient.execute(httpget);//Hit the get URL
		return closableHttpResponse;

	}


	//3. Post Method
	public CloseableHttpResponse post(String url,String entityString, HashMap<String,String> headerMap) throws ClientProtocolException, IOException
	{

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);//http post request
		httppost.setEntity(new StringEntity(entityString));

		for(Map.Entry<String, String> entry : headerMap.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closableHttpResponse = httpClient.execute(httppost);//Hit the get URL
		return closableHttpResponse;

	}



}
