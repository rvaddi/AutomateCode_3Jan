package com.amadeus.selenium.sqmobile.page.home.customers;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.menu.customers.SQMobileMenu;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.sqmobile.page.home.HomePage;
import com.amadeus.selenium.utils.CheckUtils;

public class SQMobileHomePage extends HomePage{
  protected static final By LOC_IN_HOME_BOOK_FLIGHT = By.cssSelector("#navbookFlight>a");
  protected static final By LOC_IN_HOME_FARE_DEALS = By.className("deals");
  protected static final By LOC_IN_HOME_FAQ = By.className("faq");
  protected static final By LOC_IN_HOME_MORE = By.className("more");
  protected static final By LOC_IN_HOME_MY_TRIP = By.className("my-trip");
  protected static final By LOC_IN_HOME_ABOUT = By.className("about");
  protected static final By LOC_IN_HOME_TIMETABLE = By.cssSelector(".navigation.timetable.baselineText");
  protected static final By LOC_BT_HOME_MY_TRIPS = By.cssSelector(".navigation.my-trip");
  protected static final By LOC_BT_HOME_KF_LOGIN = By.cssSelector("#navKrisflyer>a");

  public final  SQMobileMenu sqMobileMenu;

  public SQMobileHomePage(SeleniumSEPTest test) throws Exception{
    super(test);
    sqMobileMenu = PageFactory.getPageObject(SQMobileMenu.class);
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
}

