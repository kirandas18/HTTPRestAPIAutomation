package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {

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

	@Test(priority=1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException 
	{
		RestClient restClient = new RestClient();
		closableHttpResponse=restClient.get(url);

		//Status Code
		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code is ---->" + statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status Code is not 200");

		//Response String
		String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API--->"+ responseJson);
		
		//perpage value validation from JSON
		String perPageValue=TestUtil.getValueByJpath(responseJson,"/per_page");
		System.out.println("Per page values---->"+ perPageValue);
		Assert.assertEquals(perPageValue, Integer.toString(6));
		
		//Total value validation from JSON
		String totalValue=TestUtil.getValueByJpath(responseJson,"/total");
		System.out.println("Total Value---->"+ totalValue);
		Assert.assertEquals(totalValue, Integer.toString(12));
		
		//Get the value from JSON Data Array
		String lastName=TestUtil.getValueByJpath(responseJson,"/data[0]/last_name");
		String id=TestUtil.getValueByJpath(responseJson,"//data[0]/id");
		String avatar=TestUtil.getValueByJpath(responseJson,"//data[0]/avatar");
		String firstName=TestUtil.getValueByJpath(responseJson,"//data[0]/first_name");
		
		//Displaying values from Data Array
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);		
		System.out.println(firstName);

		//All Headers
		Header[] headersArray = closableHttpResponse.getAllHeaders();
		HashMap<String,String> allHeaders = new HashMap<String,String>();
		for(Header header: headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}

		System.out.println("Headers Array is "+ allHeaders);

	}
	
	
	@Test(priority=2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException 
	{
		RestClient restClient = new RestClient();
		
		//Pass headers
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("content-Type", "application/json");
		/*headerMap.put("username", "application/json");
		headerMap.put("password", "application/json");
		headerMap.put("AuthToken", "application/json");
		headerMap.put("GrantType", "application/json");*/
		
		closableHttpResponse=restClient.get(url,headerMap);

		//Status Code
		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code is ---->" + statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status Code is not 200");

		//Response String
		String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API--->"+ responseJson);
		
		//perpage value validation from JSON
		String perPageValue=TestUtil.getValueByJpath(responseJson,"/per_page");
		System.out.println("Per page values---->"+ perPageValue);
		Assert.assertEquals(perPageValue, Integer.toString(6));
		
		//Total value validation from JSON
		String totalValue=TestUtil.getValueByJpath(responseJson,"/total");
		System.out.println("Total Value---->"+ totalValue);
		Assert.assertEquals(totalValue, Integer.toString(12));
		
		//Get the value from JSON Data Array
		String lastName=TestUtil.getValueByJpath(responseJson,"/data[0]/last_name");
		String id=TestUtil.getValueByJpath(responseJson,"//data[0]/id");
		String avatar=TestUtil.getValueByJpath(responseJson,"//data[0]/avatar");
		String firstName=TestUtil.getValueByJpath(responseJson,"//data[0]/first_name");
		
		//Displaying values from Data Array
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);		
		System.out.println(firstName);

		//All Headers
		Header[] headersArray = closableHttpResponse.getAllHeaders();
		HashMap<String,String> allHeaders = new HashMap<String,String>();
		for(Header header: headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}

		System.out.println("Headers Array is "+ allHeaders);

	}

}
