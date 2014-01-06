package com.amadeus.selenium.sqmobile.menu.customers;

import org.openqa.selenium.By;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.menu.CommonMenuPage;
import com.amadeus.selenium.sqmobile.page.retrieval.MyTripsPage;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

/**
 *SQMobile menu Actions
 * @author bsingh
 *
 */
public class SQMobileMenu extends CommonMenuPage{


  protected static final By LOC_IN_HOME_BOOK_FLIGHT = By.cssSelector("#navbookFlight>a");
  protected static final By LOC_BT_HOME_MY_TRIPS = By.cssSelector("#navmyTrips>a");

  public SQMobileMenu(SeleniumSEPTest test) {
    super(test);
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
   * Method to Click My Trips Button
   * @throws Exception
   */
  public void clickMyTrips() throws Exception {
	  waitForOverlayLoading(200);
	  waitForOverlayLoading(200);
    ClickUtils.clickButtonOrFail(getTest(), LOC_BT_HOME_MY_TRIPS, "My Trips button not present in Home page");
    reporter.reportPassed("Home page", "Mytrips button clicked");
  }
}
