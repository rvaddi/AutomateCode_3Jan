package com.amadeus.selenium.sqmobile.page.retrieval;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class MyTripsPage extends SqMobileCommonPage {

  protected final static By LOC_TEXT_LAST_NAME = By.name("DIRECT_RETRIEVE_LASTNAME");
  protected final static By LOC_SELECT_RETRIEVAL_OPTION = By.name("REC_LOC_TYPE");
  protected final static By LOC_TEXT_PNR_TICKET_NUMBER = By.name("REC_LOC");
  protected final static By LOC_CHK_BOX_REMEMBER_LAST_NAME = By.name("chkRemeberLastName");
  protected final static By LOC_SUBMIT_TRIP = By.className("validation");

  public MyTripsPage(SeleniumSEPTest test) throws Exception{
    super(test);

    // Check that we're on the right page.
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_SUBMIT_TRIP, 30)){
      reporter.fail("This is not my trips page");
    }else{
      reporter.reportPassed("My Trips page", "In my trips page");
    }
  }

  public enum LocatorType{
    BOOKINGREF, TICKET ;
  }

  /**
   * Method to retrieve the booking reference
   * @param lastName - the last name
   * @param recordLocator - the pnr number
   * @param rememberLastName - true if it has to be selected otherwise false
   * @throws Exception
   */
  public void actionRetrieveBookingReference(String lastName, String recordLocator, boolean rememberLastName) throws Exception{
    fillLastName(lastName);
    selectLocatorType(LocatorType.BOOKINGREF);
    fillRecordLocator(recordLocator);
    clickRememberLastName(rememberLastName);

    clickGetTrip();

    PageFactory.getPageObject(MyFlightsPage.class);
  }

  /**
   * Method to retrieve the e-ticket
   * @param lastName - the last name
   * @param recordLocator - the record locator
   * @throws Exception
   */
  public void actionRetrieveETicket(String lastName, String recordLocator) throws Exception{
    fillLastName(lastName);
    selectLocatorType(LocatorType.TICKET);
    fillRecordLocator(recordLocator);

    clickGetTrip();

    PageFactory.getPageObject(MyFlightsPage.class);
  }


  /**
   * Method filling the last name
   * @param lastName the lastName
   */
  public void fillLastName(String lastName){
    FillUtils.fillInputOrFail(getTest(), LOC_TEXT_LAST_NAME, lastName, "LastName couldn't be filled");
    reporter.reportPassed("Last Name", lastName);
  }

  /**
   * Method selecting the locator type
   * @param locatorType - type option to be selected
   */

  public void selectLocatorType(LocatorType locatorType){
    FillUtils.fillSelectByValueOrFail(getTest(), LOC_SELECT_RETRIEVAL_OPTION, locatorType.toString(), "Failed to select "+locatorType);
    reporter.reportPassed("Locator Type", locatorType.toString());
  }

  /**
   * Method to fill the record locator
   * @param recordLocator - the pnr or the ticket no
   */

  public void fillRecordLocator(String recordLocator){
    FillUtils.fillInputOrFail(getTest(), LOC_TEXT_PNR_TICKET_NUMBER, recordLocator, "REcord Locator couldn't be filled");
    reporter.reportPassed("Record Locator", recordLocator);
  }

  /**
   * Method to select the remember me checkbox
   * @param rememberLastName - true if it has to be selected otherwise false
   */

  public void clickRememberLastName(boolean rememberLastName){
    WebElement chkbox = CheckUtils.getElement(getTest(), LOC_CHK_BOX_REMEMBER_LAST_NAME);
    if(chkbox != null && chkbox.isDisplayed()){
      int status = new Boolean(rememberLastName).compareTo(new Boolean(chkbox.isSelected()));
      if(status > 0 || status < 0)
        ClickUtils.clickButtonOrFail(getTest(), chkbox, "Failed to click remember me");
    }
  }

  /**
   * Method to click on get trip button
   */
  public void clickGetTrip(){
    ClickUtils.clickButtonOrFail(getTest(), LOC_SUBMIT_TRIP, "GetTrip Button couldn't be clicked");
    reporter.reportPassed("My trips page", "Get trip");
  }
}
