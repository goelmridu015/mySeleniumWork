package com.mycompany.app;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.Base;


public class MyStore extends Base implements ITestListener{
	private WebDriver driver;
	
	@BeforeMethod
	public void login()
	{
		driver=intializeDriver();
		driver.get("http://automationpractice.com/index.php");
			
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		signin();  
	}
	
	
	
	@Test
	public void addShoppingCart() throws InterruptedException 
	{
		mystorePageModel.getbtntopMenu().click();
		mystorePageModel.getbtnsubcategories().click();
		String[] itemsNeeded= {"Faded Short Sleeve T-shirts","Blouse"};
		addItems(driver,itemsNeeded);
		mystorePageModel.getbtnViewCart().click();
		
		String total=mystorePageModel.getlbltotal().getText();  		
		Assert.assertTrue(total.equalsIgnoreCase(pro.getProperty("productprice")), "Total price is correct");
		
		mystorePageModel.getbtnProceedonSummaryPage().click();
		mystorePageModel.getbtnProceedonAddressPage().click();
		mystorePageModel.getcbTermsandCond().click();
		mystorePageModel.getbtnProceedonShippingPage().click();
		mystorePageModel.getbtnPaymentbyWire().click();
		mystorePageModel.getbtnConfirmOrder().click();
	}
	
	@Test
	public void previousOrders()
	{
		String formattedName;
		mystorePageModel.getbtnOrderHistory().click();
		orderPageModel.getbtnDateOrderList().click();
			
			String[] name=orderPageModel.getOrderList().get(0).getText().split("\\s+");
			formattedName=name[0];
			driver.findElement(By.xpath("//tbody/tr/td[1]/a[contains(text(),'"+formattedName+"')]")).click();
			
			
		
		Select select=new Select(orderPageModel.getddProduct());
	    select.selectByVisibleText(pro.getProperty("drpdwnproduct"));
	    orderPageModel.gettxtmsgText().sendKeys(pro.getProperty("message"));
	    orderPageModel.getbtnSend().click();
	    
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		
		for(int j=0;j<orderPageModel.getlbldate().size();j++)
		{
			if(orderPageModel.getlbldate().get(j).getText().contains(dateFormat.format(date)))
			{
			  Assert.assertTrue(orderPageModel.getlbldate().get(j).getText().contains(pro.getProperty("message")), "Message found");
			}
		}
	    
	}
	
	@Test
	public void captureImage()
	{
		
		mystorePageModel.getbtnOrderHistory().click();
		orderPageModel.getbtnDateOrderList().click();
		
			String[] name=orderPageModel.getOrderList().get(0).getText().split("\\s+");
			String formattedName=name[0];
			driver.findElement(By.xpath("//tbody/tr/td[1]/a[contains(text(),'"+formattedName+"')]")).click();
			
		
		
		for(int i=0;i<orderPageModel.getProductcolorList().size();i++)
		{
			String[] name_color=orderPageModel.getProductcolorList().get(i).getText().split(":");
			String formattedName_color=name_color[1];
			String [] color = formattedName_color.split(",");
			String colorfinal=color[0].trim();
			Assert.assertTrue(colorfinal.equals(pro.getProperty("color")), "Color matched");
		}
	}
   
	
	@AfterMethod
	public void onTestFailure(ITestResult result) 
	{
		if(ITestResult.FAILURE==result.getStatus()){
		try{
			// To create reference of TakesScreenshot
			TakesScreenshot screenshot=(TakesScreenshot)driver;
			// Call method to capture screenshot
			File src=screenshot.getScreenshotAs(OutputType.FILE);
			// Copy files to specific location 
			// result.getName() will return name of test case so that screenshot name will be same as test case name
			FileUtils.copyFile(src, new File("C:\\Users\\ADMIN\\my-app\\Screenshot\\"+result.getName()+".png"));
			
		}
		catch (Exception e)
		{
			e.getMessage();
		} 
		}
		
	}
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
	@AfterMethod
	public void logOut()
	{
        driver.close();
        driver.quit();
	}
}

