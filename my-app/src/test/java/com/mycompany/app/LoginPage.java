package com.mycompany.app;

import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import resources.Base;

public class LoginPage extends Base {
	
	private WebDriver driver;
	
	String baseURL_for_Registration="https://reqres.in/api/register";
	String baseURL_for_LoginSuccessful="https://reqres.in/api/login";
	
	@SuppressWarnings("unchecked")
	@Test
	public void registrationSuccessful()
	{ 
		RestAssured.baseURI =baseURL_for_Registration;
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("email",pro.getProperty("email")); 
		requestParams.put("password",pro.getProperty("password")); 
		request.body(requestParams.toJSONString());
		Response response = request.put();
	 
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode,200);
	}
	
	@Test
	public void login()
	{
		driver = intializeDriver(); 
		driver.get(pro.getProperty("baseUrl"));
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		signin();  
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void loginSuccessful()
	{ 
		RestAssured.baseURI =baseURL_for_LoginSuccessful;
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("email",pro.getProperty("email")); 
		requestParams.put("password",pro.getProperty("password")); 
		request.body(requestParams.toJSONString());
		Response response = request.put();
	 
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode,200);	
	}
	
	@AfterClass
	public void logOut()
	{	
		driver.close();
        driver.quit();
	}
}
