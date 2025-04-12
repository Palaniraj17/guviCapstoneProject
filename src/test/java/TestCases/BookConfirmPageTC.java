package TestCases;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BasePackage.baseclass;
import PagesClass.BookHotelPage;
import PagesClass.BookedItineraryPage;
import PagesClass.BookingConfirmationPage;
import PagesClass.SearchHotelPage;
import PagesClass.SearchHotelResultsPage;

@Listeners(TestReports.TestListener.class)
public class BookConfirmPageTC extends baseclass{

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
	
	@Test(description="TC_007")
	public void afterbooksearchpage() throws IOException, InterruptedException {
		shp.FillOptionsSearchHotel();
		shrp.selectHotelRow(1);
		shrp.clickContinuebuttonsearchresults();
		bhp.fillManfieldBookNow();
		bhp.clickBookNow();
		bcp.orderNoVerify();
		bcp.searchBtnVerifybcp();
	}
	
	@Test
	public void afterbookmyitinerarypage() throws IOException, InterruptedException {
		bip.deleteAllbookings();
		shp.FillOptionsSearchHotel();
		shrp.selectHotelRow(1);
		shrp.clickContinuebuttonsearchresults();
		bhp.fillManfieldBookNow();
		bhp.clickBookNow();
		bcp.orderNoVerify();
		bcp.myitineraryBtnVerifybcp();
	}
	
	@Test(description="TC_014")
	public void afterbooklogoutpage() throws IOException, InterruptedException {
		bip.deleteAllbookings();
		shp.FillOptionsSearchHotel();
		shrp.selectHotelRow(1);
		shrp.clickContinuebuttonsearchresults();
		bhp.fillManfieldBookNow();
		bhp.clickBookNow();
		bcp.orderNoVerify();
		bcp.logoutBtnVerifybcp();
	}
	
	@Test(description="TC_008")
	public void afterbookmyitinerarypageValueVerify() throws IOException, InterruptedException {
		bip.deleteAllbookings();
		shp.FillOptionsSearchHotel();
		shrp.selectHotelRow(1);
		shrp.clickContinuebuttonsearchresults();
		bhp.fillManfieldBookNow();
		bhp.clickBookNow();
		bcp.orderNoVerify();
		bcp.myitineraryBtnVerifybcp();
		bip.getColumnDetailsafterbk();
	}
	
}
