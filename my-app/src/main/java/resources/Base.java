package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.HomePageModel;
import pageObjects.LoginPageModel;
import pageObjects.MyStorePageModel;
import pageObjects.OrderPageModel;

public class Base {
	
	private static WebDriver driver;
	
	public static HomePageModel homePageModel; 
	public static LoginPageModel loginPageModel;
	public static MyStorePageModel mystorePageModel;
	public static Properties pro;
	public static FileInputStream fis;
	
	public static OrderPageModel orderPageModel;
	
	public static WebDriver intializeDriver()
	{
		pro=new Properties();
		try
		{
		  fis=new FileInputStream("C:\\Users\\ADMIN\\my-app\\src\\main\\java\\resources\\data.properties");
		}
		
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
	    } 
		try
		{
			pro.load(fis);
		} 
		
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		String browserName=pro.getProperty("browser");
			
		if(browserName.equalsIgnoreCase("Firefox"))
         {
		   System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", pro.getProperty("ChromeDriverPath"));
			driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", pro.getProperty("IEDriverPath"));
			driver=new InternetExplorerDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public static void signin()
	{
		homePageModel = PageFactory.initElements(driver, HomePageModel.class);
		loginPageModel = PageFactory.initElements(driver, LoginPageModel.class);
		mystorePageModel = PageFactory.initElements(driver, MyStorePageModel.class);
		orderPageModel = PageFactory.initElements(driver, OrderPageModel.class);
		
		homePageModel.getbtnSignin().click();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		loginPageModel.gettxtEmail().sendKeys(pro.getProperty("email"));
		loginPageModel.gettxtPassword().sendKeys(pro.getProperty("password"));
		loginPageModel.getbtnLogin().click();
	}
	
	public void addItems(WebDriver driver,String[] itemsNeeded)
	{
		WebDriverWait wait2 = new WebDriverWait(driver, 100);
    	Actions action = new Actions(driver);
	    int j=0;
	    //WebElement productgrid = driver.findElement(By.cssSelector("body.category.category-4.category-tops.hide-right-column.lang_en:nth-child(2) div.columns-container div.container div.row:nth-child(3) div.center_column.col-xs-12.col-sm-9 > ul.product_list.grid.row:nth-child(5)"));
	    //List<WebElement> products = productgrid.findElements(By.tagName("li"));
	    List<WebElement> products=mystorePageModel.getproductList();
	    
	    for(int i=0;i<products.size();i++)
	    {
	    	String[] name=products.get(i).getText().split("\n");
	    	String formattedName=name[0];
	    	List<String> itemsNeededList = Arrays.asList(itemsNeeded);
	    	if(itemsNeededList.contains(formattedName))
	    	{
	    		j++;
	    		wait2.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@title='"+formattedName+"']/img[@title='"+formattedName+"']"))));
	    		
	    		WebElement myElement = driver.findElement(By.xpath("//*[@title='"+formattedName+"']/img[@title='"+formattedName+"']"));
	    		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
	    		    		
	    		wait2.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[contains(@data-id-product,'"+j+"')]//span[contains(text(),'Add to cart')]"))));
	    		action.moveToElement(driver.findElement(By.xpath("//*[contains(@data-id-product,'"+j+"')]//span[contains(text(),'Add to cart')]"))).click().build().perform();
        	
	    		String mainpage=driver.getWindowHandle();  // Main Handle
	    		
	    		for(String winHandle : driver.getWindowHandles())
	    		{
	    			
	    			driver.switchTo().window(winHandle);
	    			wait2.until(ExpectedConditions.elementToBeClickable(mystorePageModel.getbtnContinueShopping()));
		    		mystorePageModel.getbtnContinueShopping().click();
		    		driver.switchTo().window(mainpage);
	    		}	
	    		
	    		if(j==itemsNeeded.length)
	    		{
	    			break;
	    		}
	    	}
	    }
	}
}