package TestCases;

import java.io.IOException;

import org.testng.annotations.*;

import BasePackage.baseclass;
import PagesClass.BookHotelPage;
import PagesClass.BookedItineraryPage;
import PagesClass.BookingConfirmationPage;
import PagesClass.SearchHotelPage;
import PagesClass.SearchHotelResultsPage;

@Listeners(TestReports.TestListener.class)
public class BookItineraryPageTC extends baseclass {
	
	SearchHotelPage shp;
	SearchHotelResultsPage shrp;
	BookHotelPage bhp;
	BookingConfirmationPage bcp;
	BookedItineraryPage bip;

	@BeforeMethod(description="TC_01")
	public void setupPageObjects() throws IOException 
	{
		preCondition();
		shp = new SearchHotelPage(driver);
		shrp = new SearchHotelResultsPage(driver);
		bhp=new BookHotelPage(driver);
		bcp= new BookingConfirmationPage(driver);
		bip=new BookedItineraryPage(driver);
	}
	
	@Test(description="TC_011")
	public void logoutfromBookItinerary() throws IOException, InterruptedException {
		bip.deleteAllbookings();
		shp.FillOptionsSearchHotel();
		shrp.selectHotelRow(1);
		shrp.clickContinuebuttonsearchresults();
		bhp.fillManfieldBookNow();
		bhp.clickBookNow();
		bcp.orderNoVerify();
		bcp.myitineraryBtnVerifybcp();
		bip.getColumnDetailsafterbk();
		bip.clickloutBtnBookItinerary();
	}

	@Test(description="TC_010")
	public void searchOrderID() throws IOException, InterruptedException {
		bip.deleteAllbookings();
		shp.FillOptionsSearchHotel();
		shrp.selectHotelRow(1);
		shrp.clickContinuebuttonsearchresults();
		bhp.fillManfieldBookNow();
		bhp.clickBookNow();
		bcp.orderNoVerify();
		bip.searchOrderID();
	}
	

	@Test(description="TC_009")
	public void bookHotelandCancelOrder() throws IOException, InterruptedException {
		bip.deleteAllbookings();
		shp.FillOptionsSearchHotel();
		shrp.selectHotelRow(1);
		shrp.clickContinuebuttonsearchresults();
		bhp.fillManfieldBookNow();
		bhp.clickBookNow();
		bcp.orderNoVerify();
		bip.searchOrderID();
	}
}
