package com.amadeus.selenium.sqmobile.menu;

import org.openqa.selenium.By;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.checkin.CheckinSendFlightInfo;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.sqmobile.page.faredeal.SQMobileDealPage;
import com.amadeus.selenium.sqmobile.page.login.KFLoginPage;
import com.amadeus.selenium.sqmobile.page.retrieval.MyTripsPage;
import com.amadeus.selenium.sqmobile.page.search.SearchPage;
import com.amadeus.selenium.sqmobile.page.search.TimetableSearchPage;
import com.amadeus.selenium.sqmobile.page.search.customers.AirCaraibesSearchPage;
import com.amadeus.selenium.sqmobile.page.search.customers.AirChinaSearchPage;
import com.amadeus.selenium.sqmobile.page.search.customers.ELALSearchPage;
import com.amadeus.selenium.sqmobile.page.search.customers.IcelandAirSearchPage;
import com.amadeus.selenium.sqmobile.page.search.customers.MEASearchPage;
import com.amadeus.selenium.sqmobile.page.search.customers.SQMobileSearchPage;
import com.amadeus.selenium.sqmobile.page.search.customers.SaudiSearchPage;
import com.amadeus.selenium.sqmobile.page.search.customers.TAMSearchPage;
import com.amadeus.selenium.sqmobile.page.staticpages.ContactUsPage;
import com.amadeus.selenium.sqmobile.page.staticpages.FAQPage;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

/**
 *SQMobile menu Actions
 * @author Vigneshwaran Balasubramanian
 *
 */
public class CommonMenuPage extends SqMobileCommonPage{

	protected static final By LOC_IN_HOME_BOOK_FLIGHT = By.cssSelector("a[class*=book-flight]");
	protected static final By LOC_IN_HOME_FARE_DEALS = By.cssSelector("#navFareDeal>a");
	protected static final By LOC_IN_HOME_CheckIn = By.cssSelector("#navCheckin>a");
	protected static final By LOC_IN_HOME_FAQ = By.cssSelector("#navInfo>a");
	protected static final By LOC_IN_HOME_MORE = By.cssSelector("#navMore>a");
	protected static final By LOC_IN_HOME_MY_TRIP = By.className("my-trip");
	protected static final By LOC_IN_HOME_ABOUT = By.className("about");
	protected static final By LOC_IN_HOME_TIMETABLE = By.cssSelector(".navigation.timetable.baselineText");
	protected static final By LOC_BT_HOME_MY_TRIPS = By.cssSelector(".navigation.my-trip");
	protected static final By LOC_BT_HOME_KF_LOGIN = By.cssSelector("#navKrisflyer>a");
	protected static final By LOC_IN_HOME_FLIGHTINFO = By.cssSelector("a[class*=flight-status]");
	protected static final By LOC_BT_HOME_CONTACTUS = By.cssSelector(".navigation.contact");

	public CommonMenuPage(SeleniumSEPTest test) {
		super(test);
	}


	public enum Customer{
		SAUDI, AIRCHINA, ICELANDAIR, TAM, CARAB, SQMOBILE, ELAL, MEA, EGYPTAIR
	}

	/**
	 * Method to Click Book Flight
	 * @throws Exception
	 */
	public void clickBookFlight()throws Exception {
		WaitUtils.waitForElementPresent(getTest(), LOC_IN_HOME_BOOK_FLIGHT, 30);
		ClickUtils.clickButtonOrFail(getTest(), LOC_IN_HOME_BOOK_FLIGHT, "Book Flight button not found");
		reporter.report("Home Page", "Book flight Clicked");

	}

	/**
	 * Method to Click Fare Deals
	 * @throws Exception
	 */
	public void clickFareDeals() throws Exception {
		WaitUtils.waitForElementPresent(getTest(), LOC_IN_HOME_FARE_DEALS, 30);
		ClickUtils.clickButtonOrFail(getTest(), LOC_IN_HOME_FARE_DEALS, "FareDeals Button couldn't be clicked");
		reporter.report("Home page", "Fare deals button Clicked");
	}

	/**
	 * Method to Click Check-in
	 * @throws Exception
	 */
	public void clickCheckIn() throws Exception {
		WaitUtils.waitForElementPresent(getTest(), LOC_IN_HOME_CheckIn, 30);
		ClickUtils.clickButtonOrFail(getTest(), LOC_IN_HOME_CheckIn, "Check-in Button couldn't be clicked");
		reporter.report("Home page", "Check-in button Clicked");
	}

	/**
	 * Method to Click FAQ Button
	 * @throws Exception
	 */
	public void clickFAQ()throws Exception {
		ClickUtils.clickButtonOrFail(getTest(), LOC_IN_HOME_FAQ, "FAQ Button not found");
		reporter.report("Home page", "FAQ button clicked");
	}

	/**
	 * Method to Click More button
	 * @throws Exception
	 */
	public void clickMore()throws Exception {
		ClickUtils.clickButtonOrFail(getTest(), LOC_IN_HOME_MORE, "More button not found");
		reporter.report("Home page", "More button clicked");
	}


	/**
	 * Method to Click Timetable Button
	 * @throws Exception
	 */
	public void clickTimetable() throws Exception {
		ClickUtils.clickButtonOrFail(getTest(), LOC_IN_HOME_TIMETABLE, "Timetable Button couldn't be clicked ", "Clicked Timetable button successfully");
		reporter.report("Home Page", "Time table button clicked");

	}

	/**
	 * Method to Click My Trips Button
	 * @throws Exception
	 */
	public void clickMyTrips() throws Exception {
		ClickUtils.clickButtonOrFail(getTest(), LOC_BT_HOME_MY_TRIPS, "My Trips button not present in Home page");
		reporter.report("Home page", "Mytrips button clicked");
	}
	/**
	 * Method to Click Flight Info Button
	 */
	public void clickFlightInfo()throws Exception {
		ClickUtils.clickButtonOrFail(getTest(), LOC_IN_HOME_FLIGHTINFO, "Flight Info button not found");
		reporter.report("Home page", "Flight Info button clicked");
	}
	/**
	 * Method to Click KrisFlyer Login Button
	 * @throws Exception
	 */
	public void clickKFLogin() throws Exception {
		ClickUtils.clickButtonOrFail(getTest(), LOC_BT_HOME_KF_LOGIN, "KFLogin button not present in Home page");
		reporter.report("Home page", "KFlogin button clicked");
	}
	/**
	 * Action Search Page
	 * @return the Searchpage instance
	 * @throws Exception
	 */
	public SearchPage actionSearch(Customer customer) throws Exception{
		waitForOverlayLoading(200);
		clickBookFlight();
		waitForOverlayLoading(120);
		if(customer!=null){
			if(Customer.SAUDI.equals(customer)){
				return PageFactory.getPageObject(SaudiSearchPage.class);
			}
			else if(Customer.AIRCHINA.equals(customer)){
				return PageFactory.getPageObject(AirChinaSearchPage.class);
			}
			else if(Customer.ICELANDAIR.equals(customer)){
				return PageFactory.getPageObject(IcelandAirSearchPage.class);
			}
			else if(Customer.CARAB.equals(customer)){
				return PageFactory.getPageObject(AirCaraibesSearchPage.class);
			}
			else if(Customer.TAM.equals(customer)){
				return PageFactory.getPageObject(TAMSearchPage.class);
			}
			else if(Customer.ELAL.equals(customer)){
				return PageFactory.getPageObject(ELALSearchPage.class);
			}
			else if(Customer.SQMOBILE.equals(customer)){
				return PageFactory.getPageObject(SQMobileSearchPage.class);
			}
			else if(Customer.MEA.equals(customer)){
				return PageFactory.getPageObject(MEASearchPage.class);
			}
			else if (Customer.EGYPTAIR.equals(customer)) {
				return PageFactory.getPageObject(SearchPage.class);
			}
		}
		else{
			reporter.fail("Customer not found");
		}
		return null;
	}

	/**
	 *Action Fare deals page
	 * @return the SQMobileDealPage instance
	 * @throws Exception
	 */
	public SQMobileDealPage actionFareDeals() throws Exception{
		clickFareDeals();
		return PageFactory.getPageObject(SQMobileDealPage.class);
	}

	/**
	 *Action Check-In page
	 * @return the SendFlightInfo instance
	 * @throws Exception
	 */
	public CheckinSendFlightInfo actionCheckIn() throws Exception{
		clickCheckIn();
		return PageFactory.getPageObject(CheckinSendFlightInfo.class);
	}

	/**
	 * Action FAQ page
	 * @return the FQApage
	 * @throws Exception
	 *
	 */
	public FAQPage actionFAQ() throws Exception{
		clickFAQ();
		return PageFactory.getPageObject(FAQPage.class);
	}

	/**
	 * Action Timetable page
	 * @return the timeTablepage instance
	 * @throws Exception
	 */
	public TimetableSearchPage actionTimetable() throws Exception{
		waitForOverlayLoading(200);
		clickTimetable();
		return PageFactory.getPageObject(TimetableSearchPage.class);
	}

	/**
	 * Action MyTrips
	 * @return the Mytrips instance
	 * @throws Exception
	 */
	public MyTripsPage actionMyTrips() throws Exception{
		clickMyTrips();
		return PageFactory.getPageObject(MyTripsPage.class);
	}

	/**
	 * Action KFLogin
	 * @return the KFloginpage instance
	 * @throws Exception
	 *
	 */
	public KFLoginPage actionKFLogin() throws Exception{
		clickKFLogin();
		return PageFactory.getPageObject(KFLoginPage.class);
	}

	public ContactUsPage actionContactUs() throws Exception{
		waitForOverlayLoading(200);
		clickContactUs();
		return PageFactory.getPageObject(ContactUsPage.class);
	}
	public void clickContactUs() throws Exception {
		waitForOverlayLoading(200);
		ClickUtils.clickButtonOrFail(getTest(), LOC_BT_HOME_CONTACTUS, "ContactUs button not present in Home page");
		reporter.report("Home page", "ContactUs button clicked");
	}

}
