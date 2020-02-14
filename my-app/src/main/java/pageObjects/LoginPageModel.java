package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageModel {
	
	public WebDriver driver;
	
	public LoginPageModel(WebDriver driver) 
	{
		this.driver=driver;
	}
	
	@FindBy(id="email")
	private WebElement txtEmail;
	
	@FindBy(id="passwd")
	private WebElement txtPassword;
	
	@FindBy(xpath="//*[@id='SubmitLogin']/span")
	private WebElement btnLogin;
	
	
	//Getter methods
	public WebElement gettxtEmail()
	{
		return txtEmail;
	}
	
	public WebElement gettxtPassword()
	{
		return txtPassword;
	}
	
	public WebElement getbtnLogin()
	{
		return btnLogin;
	}

}
