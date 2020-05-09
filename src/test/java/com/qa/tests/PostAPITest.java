package com.qa.tests;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase{

	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	CloseableHttpResponse closableHttpResponse;

	@BeforeMethod
	public void setup()
	{
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");

		url = serviceUrl + apiUrl;

	}

	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException{

		RestClient restClient = new RestClient();

		//Pass headers
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("content-Type", "application/json");

		//Jackson API
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("morpheus","leader");

		//object to JSON File
		mapper.writeValue(new File("C:\\Users\\kgaddam\\eclipse_Apr\\HttpClientRestAPIAutomation\\src\\main\\java\\com\\qa\\data\\users.json"), users);

		//object to json in string
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println(usersJsonString);

		closableHttpResponse=restClient.post(url,usersJsonString,headerMap);

		//Status Code
		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code is ---->" + statusCode);

		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201,"Status Code is not 201");

		//Json String
		String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API--->"+ responseJson);

		//json to java object
		Users usersResobj = mapper.readValue(responseString,Users.class);
		System.out.println(usersResobj);
		
		Assert.assertTrue(users.getName().equals(usersResobj.getName()));
		Assert.assertTrue(users.getJob().equals(usersResobj.getJob()));
		
		System.out.println(usersResobj.getCreatedAt());
		System.out.println(usersResobj.getId());
		
	}


}
