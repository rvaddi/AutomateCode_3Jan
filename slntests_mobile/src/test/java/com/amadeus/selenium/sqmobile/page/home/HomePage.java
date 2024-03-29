package com.amadeus.selenium.sqmobile.page.home;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.menu.CommonMenuPage;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.sqmobile.page.retrieval.MyFlightsPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.ClickUtils;

public class HomePage extends SqMobileCommonPage{
  protected static final By LOC_IN_HOME_BOOK_FLIGHT = By.className("book-flight");
  protected static final By LOC_IN_HOME_FARE_DEALS = By.className("deals");
  protected static final By LOC_IN_HOME_FAQ = By.className("faq");
  protected static final By LOC_IN_HOME_MORE = By.className("more");
  protected static final By LOC_IN_HOME_MY_TRIP = By.className("my-trip");
  protected static final By LOC_IN_HOME_ABOUT = By.className("about");
  protected static final By LOC_IN_HOME_TIMETABLE = By.cssSelector(".navigation.timetable.baselineText");
  protected static final By LOC_BT_HOME_MY_TRIPS = By.cssSelector(".navigation.my-trip");
  protected static final By LOC_BT_HOME_KF_LOGIN = By.cssSelector("#navKrisflyer>a");

  public final  CommonMenuPage sqMobileMenu;

	//TestId 5461
	protected static final By LOC_IN_HOME_EG_LNAME = By.cssSelector("#lastNameId>label");
	protected final static By LOC_SELECT_RETRIEVAL_OPTION = By.name("REC_LOC_TYPE");
	protected final static By LOC_TEXT_LAST_NAME = By.name("DIRECT_RETRIEVE_LASTNAME");
	protected final static By LOC_TEXT_PNR_TICKET_NUMBER = By.name("REC_LOC");
	protected final static By LOC_CHK_BOX_REMEMBER_LAST_NAME = By.name("chkRemeberLastName");
	protected final static By LOC_SUBMIT_TRIP = By.name("buttonSave");
	protected final static By LOC_EG_HOME_FLIGHT_INFO = By.id("navInfo");
	protected final static By LOC_SAUDI_HOME_FLIGHT_INFO = By.cssSelector("a[class*=flight-status]");


  public HomePage(SeleniumSEPTest test) throws Exception{
    super(test);
    sqMobileMenu = PageFactory.getPageObject(CommonMenuPage.class);
  }

/**
 * Validate Home page
 * @throws Exception
 */
  public void validateHomepage()throws Exception {


    int i = 0;
    WebElement link1 = CheckUtils.getElement(getTest(), LOC_IN_HOME_BOOK_FLIGHT);
    if (link1 != null && link1.isDisplayed()) {
      i = i + 1;
      reporter.reportPassed("Validating Home Page", "Book Flight link is displayed");
    }
    WebElement link2 = CheckUtils.getElement(getTest(), LOC_IN_HOME_FARE_DEALS);
    if (link2 != null && link2.isDisplayed()) {
      i = i + 1;
      reporter.reportPassed("Validating Home Page", "Fare Deals link is displayed");
    }
    WebElement link3 = CheckUtils.getElement(getTest(), LOC_IN_HOME_MY_TRIP);
    if (link3 != null && link3.isDisplayed()) {
      i = i + 1;
      reporter.reportPassed("Validating Home Page", "My Trip link is displayed");
    }
    WebElement link4 = CheckUtils.getElement(getTest(), LOC_IN_HOME_FAQ);
    if (link4 != null && link4.isDisplayed()) {
      i = i + 1;
      reporter.reportPassed("ValidatingHome Page", "FAQ link is displayed");
    }
    WebElement link5 = CheckUtils.getElement(getTest(), LOC_IN_HOME_MORE);
    if (link5 != null && link5.isDisplayed()) {
      i = i + 1;
      reporter.reportPassed("Validating Home Page", "More link is displayed");
    }
    WebElement link6 = CheckUtils.getElement(getTest(), LOC_IN_HOME_ABOUT);
    if (link6 != null && link6.isDisplayed()) {
      i = i + 1;
      reporter.reportPassed("ValidatingHome Page", "About link is displayed");
    }

    if (i == 6) {
      reporter.reportPassed("Validating Home Page", "All the links in Home Page are displayed successfully");
    }
    else {
      reporter.reportFailed("Validating Home Page", "All the links are not displayed");
    }
  }

	//Test ID 5461
	public void validateHomePage()throws Exception {
		int i = 0;
		WebElement link1 = CheckUtils.getElement(getTest(), LOC_IN_HOME_EG_LNAME);
		if (link1 != null && link1.isDisplayed()) {
			i = i + 1;
			reporter.reportPassed("Validating Home Page", "Last Name is displayed");
		}
		if (i == 1) {
			reporter.reportPassed("Validating Home Page", "All the links in Home Page are displayed successfully");
		}
		else {
			reporter.reportFailed("Validating Home Page", "All the links are not displayed");
		}

	}
	public void fillInfo(String lastName,String bookRef,boolean rememberLastName) throws Exception
	{
		FillUtils.fillInputOrFail(getTest(), LOC_TEXT_LAST_NAME, lastName, "LastName couldn't be filled");
		reporter.reportPassed("Last Name", lastName);

		FillUtils.fillSelectByValueOrFail(getTest(), LOC_SELECT_RETRIEVAL_OPTION, "BOOKINGREF", "Failed to select BOOKINGREF");
		reporter.reportPassed("Locator Type", "BOOKINGREF");

		FillUtils.fillInputOrFail(getTest(), LOC_TEXT_PNR_TICKET_NUMBER, bookRef, "Record Locator couldn't be filled");
		reporter.reportPassed("Record Locator", bookRef);

		WebElement chkbox = CheckUtils.getElement(getTest(), LOC_CHK_BOX_REMEMBER_LAST_NAME);
		if(chkbox != null && chkbox.isDisplayed()){
			int status = new Boolean(rememberLastName).compareTo(new Boolean(chkbox.isSelected()));
			if(status > 0 || status < 0)
				ClickUtils.clickButtonOrFail(getTest(), chkbox, "Failed to click remember me");
		}
		ClickUtils.clickButtonOrFail(getTest(), LOC_SUBMIT_TRIP, "GetTrip Button couldn't be clicked");
	    reporter.reportPassed("Home page", "Get trip");

	    waitForOverlayLoading(200);
	    waitForOverlayLoading(200);


	}

	//Test Id 5091
	public void clickOnFlightInfo()
	{
		waitForOverlayLoading(120);
	    ClickUtils.clickButtonOrFail(getTest(), LOC_SAUDI_HOME_FLIGHT_INFO, "Flight Info Button is NOT displayed");
	    waitForOverlayLoading(120);
	}
}

