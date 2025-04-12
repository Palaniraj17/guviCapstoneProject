package PagesClass;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import BasePackage.baseclass;

public class SearchHotelResultsPage extends baseclass {

	WebDriver driver;
	Properties prop;
	int num=0;
	public SearchHotelResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//table/tbody/tr[2]/td/table/tbody/tr")
	private List<WebElement> searchResults;
	
	@FindBy(xpath="//input[@type='radio']")
	private List<WebElement> hotelresRadio;
	
	@FindBy(xpath="//input[@id='continue']")
	private WebElement contBtnsres;
	
	@FindBy(xpath="//input[@id='cancel']")
	private WebElement cancelBtnsres;

	@FindBy(xpath="//td[@class='login_title']")
	private WebElement header;
	
	@FindBy(xpath="//td[@class='login_title'][contains(text(), 'Book')]")
	private WebElement bookHotelheader;
	
	@FindBy(xpath="//label[@id='radiobutton_span']")
	private WebElement contValidation;
	
	private By getElementByIndexAndType(int index, String type) {
        switch (type.toLowerCase()) {
            case "hotel name":
                return By.xpath("//*[@id='hotel_name_" + index + "']");
            case "location":
                return By.xpath("//*[@id='location_" + index + "']");
            case "rooms":
                return By.xpath("//*[@id='rooms_" + index + "']");
            case "arrival date":
                return By.xpath("//*[@id='arr_date_" + index + "']");
            case "departure date":
                return By.xpath("//*[@id='dep_date_" + index + "']");
            case "rooms type":
                return By.xpath("//*[@id='room_type_" + index + "']");
            default:
            	 throw new IllegalArgumentException("Invalid column type: " + type);
        }

    }
	
	public WebElement getElement(int index, String type)
	{
        return driver.findElement(getElementByIndexAndType(index, type));
    }

    public String getTextFromColumn(int index, String type) {
        WebElement element = getElement(index, type);
		if (type.equalsIgnoreCase("hotel name") || type.equalsIgnoreCase("location") || type.equalsIgnoreCase("rooms") ||
				type.equalsIgnoreCase("arrival date") || type.equalsIgnoreCase("departure date")||type.equalsIgnoreCase("rooms type")) 
		{
			return element.getDomAttribute("value");
		} 
		else 
		{
			return element.getText();
		}
    }
    
    public void selectHotelRow(int i) throws IOException {
    	int Ttlresult=hotelresRadio.size();
    	System.out.println(Ttlresult);
    	int j=i-1;
    	if(j<=Ttlresult)
    	{
    		WebElement radio = hotelresRadio.get(j);
    		radio.click();
    		Assert.assertTrue(true, "The hotel room is selected and the number is " + i);
			System.out.println("The hotel room is selected and the number is " + i);
			tkscrnshtpass();
		} else 
		{
			tkscrnshtfail();
			Assert.fail("The hotel room is not selected and the number is " + i);
		}
    }
    
    public void clickCancelbuttonsearchresults() throws IOException {
    	cancelBtnsres.click();
		cusexpWait(header, 5);
		String expTitle="Search Hotel (Fields marked with Red asterix (*) are mandatory)";
		if(header.getText().equals(expTitle)) {
			Assert.assertTrue(true, "User is navigate to search hotel page");
			System.out.println("User is navigate to search hotel page");
			tkscrnshtpass();
		} else {
			tkscrnshtfail();
			Assert.fail("User is unable to navigate search hotel page");
		}
		
	}
    
    public void clickContinuebuttonsearchresults() throws IOException {
    	contBtnsres.click();
		cusexpWait(bookHotelheader, 5);
		String expTitle="Book A Hotel";
		System.out.println(bookHotelheader.getText());
		if(bookHotelheader.getText().equals(expTitle)) {
			Assert.assertTrue(true, "User is navigate to book hotel page");
			System.out.println("User is navigate to book hotel page");
			tkscrnshtpass();
		} else {
			tkscrnshtfail();
			Assert.fail("User is unable to navigate book hotel page");
		}
		
	}
    
    public void selectContinuewithoutselect() throws IOException {
    	contBtnsres.click();
    	String expValue="Please Select a Hotel";
    	if(contValidation.getText().equals(expValue)) {
			Assert.assertTrue(true, "User is unable to navigate book hotel page and select any one hotel");
			System.out.println("User is unable to navigate book hotel page and select any one hotel");
			tkscrnshtpass();
		} else {
			tkscrnshtfail();
			Assert.fail("Continue button validation is not completed properly");
		}
    }
	
}
