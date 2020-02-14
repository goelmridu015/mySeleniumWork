package pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyStorePageModel 
{
    public WebDriver driver;
	public MyStorePageModel(WebDriver driver) 
	{
		this.driver=driver;		
	}
	
	@FindBy(xpath="//*[@id='block_top_menu']//*[contains(text(),'Women')]")
	private WebElement btntopMenu;
	
	@FindBy(xpath="//div[@id='subcategories']//a[@title='Tops']")
	private WebElement btnsubcategories;
	
	@FindBy(xpath="//ul[@class='product_list grid row']//li")
	private List<WebElement> productList;
	
	@FindBy(xpath="//*[@title='$$']/img[@title='$$']")
	private WebElement btnfinalproduct;
	
	//*[contains(@data-id-product,'1')]//span[contains(text(),'Add to cart')]
	@FindBy(xpath="//span[contains(text(),'Add to cart')]")
	private WebElement btnAddtoCart;
	
    ////span[@class='continue btn btn-default button exclusive-medium']/span
	@FindBy(xpath="//span[@class='continue btn btn-default button exclusive-medium']/span")
	private WebElement btnContinueShopping;
	
	@FindBy(xpath="//a[contains(@title,'View my shopping cart')]")
	private WebElement btnViewCart;
	
	@FindBy(id="total_product")
	private WebElement lbltotalproduct;
	
	@FindBy(id="total_shipping")
	private WebElement lbltotalshipping;
	
	@FindBy(id="total_price")
	private WebElement lbltotal;
	
	@FindBy(css=".standard-checkout > span:nth-child(1)")
	private WebElement btnProceedonSummaryPage;
	
	@FindBy(xpath="//button[@name='processAddress']")
	private WebElement btnProceedonAddressPage;
	
	@FindBy(id="cgv")
	private WebElement cbTermsandCond;
	
	@FindBy(xpath="//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]")
	private WebElement btnProceedonShippingPage;
	
	@FindBy(xpath="//a[@class='bankwire']")
	private WebElement btnPaymentbyWire;
	
	@FindBy(xpath="//span[contains(text(),'I confirm my order')]")
	private WebElement btnConfirmOrder;
	
	@FindBy(xpath="//span[contains(text(),'Order history and details')]")
	private WebElement btnOrderHistory;
	
	
	//Getter methods
	public WebElement getbtntopMenu()
	{
		return btntopMenu;
	}
	
	public WebElement getbtnsubcategories()
	{
		return btnsubcategories;
	}
	
	public List<WebElement> getproductList()
	{
		return productList;
	}
	
	public WebElement getbtnfinalproduct()
	{
		return btnfinalproduct;
	}

	
	public WebElement getbtnAddtoCart()
	{
		return btnAddtoCart;
	}
	
	public WebElement getbtnContinueShopping()
	{
		return btnContinueShopping;
	}
	
	public WebElement getbtnViewCart()
	{
		return btnViewCart;
	}
	
	public WebElement getlbltotalproduct()
	{
		return lbltotalproduct;
	}
	
	public WebElement getlbltotalshipping()
	{
		return lbltotalshipping;
	}
	
	public WebElement getlbltotal()
	{
		return lbltotal;
	}
	
	
	public WebElement getbtnProceedonSummaryPage()
	{
		return btnProceedonSummaryPage;
	}
	
	
	public WebElement getbtnProceedonAddressPage()
	{
		return btnProceedonAddressPage;
	}
	
	public WebElement getcbTermsandCond()
	{
		return cbTermsandCond;
	}
	
	public WebElement getbtnProceedonShippingPage()
	{
		return btnProceedonShippingPage;
	}
	
	public WebElement getbtnPaymentbyWire()
	{
		return btnPaymentbyWire;
	}
	
	public WebElement getbtnConfirmOrder()
	{
		return btnConfirmOrder;
	}
	
	public WebElement getbtnOrderHistory()
	{
		return btnOrderHistory;
	}
	
	
}
