package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageModel {

	public WebDriver driver;
	
	public HomePageModel(WebDriver driver) 
	{
		this.driver=driver;
	}

	
	@FindBy(xpath="//*[contains(@class,'login')]")
	private WebElement btnSignin;
	
	//Getter method
	public WebElement getbtnSignin()
	{
		return btnSignin;
	}
	
	
	
}
