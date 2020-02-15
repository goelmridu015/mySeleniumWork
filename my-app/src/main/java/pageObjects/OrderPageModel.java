package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderPageModel {
public WebDriver driver;
	
	public OrderPageModel(WebDriver driver) 
	{
		this.driver=driver;
	}
	
	@FindBy(xpath="//*[@id='order-list']//th[2]")
	private WebElement btnDateOrderList;
	
	@FindBy(xpath="//tbody/tr/td[1]")
	private List<WebElement> OrderList;
	
	@FindBy(name="id_product")
	private WebElement ddProduct;
	
	@FindBy(name="msgText")
	private WebElement txtmsgText;
	
	@FindBy(xpath="//*[@id='order-detail-content']/table/tbody")
	private List<WebElement> ProductcolorList;
	
	@FindBy(xpath="//span[contains(text(),'Send')]")
	private WebElement btnSend;
	
	@FindBy(xpath="//*[@id='block-order-detail']/div[5]/table/tbody/tr[1]/td[1]")
	private List<WebElement> lbldate;
	
	
	//Getter methods
	public WebElement getbtnDateOrderList()
	{
		return btnDateOrderList;
	}
	
	public List<WebElement> getOrderList()
	{
		return OrderList;
	}
	
	public WebElement getddProduct()
	{
		return ddProduct;
	}
	
	public WebElement gettxtmsgText()
	{
		return txtmsgText;
	}
	
	public List<WebElement> getProductcolorList()
	{
		return ProductcolorList;
	}
	
	public WebElement getbtnSend()
	{
		return btnSend;
	}
	
	public List<WebElement> getlbldate()
	{
		return lbldate;
	}

}
