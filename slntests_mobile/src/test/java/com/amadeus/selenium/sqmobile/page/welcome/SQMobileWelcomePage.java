package com.amadeus.selenium.sqmobile.page.welcome;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.home.HomePage;
import com.amadeus.selenium.sqmobile.page.home.customers.SQMobileHomePage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

/**
 * Welcome page
 *
 * @author Vigneshwaran Balasubramanian
 *
 */
public class SQMobileWelcomePage extends WelcomePage {


  protected static final By LOC_WL_SELECT_COUNTRY = By.cssSelector("select[id*='COUNTRY']");
  protected static final By LOC_WL_SELECT_LANGUAGE = By.cssSelector("select[id*='LANGUAGE']");
  protected static final By LOC_BT_CONTINUE = By.cssSelector(".buttonRow4.buttonDirection.floatR>a>span");


  public SQMobileWelcomePage(SeleniumSEPTest test) throws Exception {
    super(test);
  }

  /**
   * Select Change Language from Home Page
   */
  public void selectChangeLanguage(){
    WebElement elt = CheckUtils.getElement(getTest(), LOC_IN_CHANGE_LANGUAGE);
    if(elt!=null && elt.isDisplayed()){
      elt.click();
      reporter.report("Change Language", "Change Language Selected");
    }
    else{
      reporter.fail("Welcome page not displayed");
    }
  }

  /**
   * Change Local Preferences
   * @return the Homepage instance
   * @throws Exception
   */
  public HomePage changeLocalPreferences(String country , String language , boolean acceptAlert) throws Exception{
    WaitUtils.waitForElementPresent(getTest(),LOC_BT_CONTINUE , 30);
    WebElement selectCountry = CheckUtils.getElement(getTest(), LOC_WL_SELECT_COUNTRY);
    WebElement selectLanguage = CheckUtils.getElement(getTest(), LOC_WL_SELECT_LANGUAGE);

    if(selectCountry!=null && selectCountry.isDisplayed()){
      FillUtils.fillSelectByVisibleTextOrFail(getTest(), LOC_WL_SELECT_COUNTRY, country, "Country not selected");
      reporter.report("Country Selection", "Country Changed");
    }
    else{
      reporter.fail("Country not selected");
    }
    if(selectLanguage!=null && selectLanguage.isDisplayed()){
      FillUtils.fillSelectByValueOrFail(getTest(), LOC_WL_SELECT_LANGUAGE, language, "Language not selected");
      reporter.report("Language Selection", "Language Changed");
    }
    else{
      reporter.fail("Language not selected");
    }

    ClickUtils.click(getTest(), LOC_BT_CONTINUE);
    reporter.report("Local Prefernces", "Country and Language Changed");
    // Accepting Alert

    if(acceptAlert)
    driver.switchTo().alert().accept();
    waitForOverlayLoading(50);
    return PageFactory.getPageObject(SQMobileHomePage.class);

  }
}
