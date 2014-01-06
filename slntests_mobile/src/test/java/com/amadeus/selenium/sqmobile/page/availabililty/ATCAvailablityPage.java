package com.amadeus.selenium.sqmobile.page.availabililty;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;
/***
 * ATC Availablity Page
 * @author vbalasubramanian
 *
 */
public class ATCAvailablityPage extends SqMobileCommonPage{

  // Locators of ATCAvailability Page ------------------------------------------

  protected final static By LOC_BT_DEPARTURE_TOGGLE_BUTTONS_EXPAND_COLLAPSE = By.cssSelector(".panel.list>header>h1>button");
  protected final static By LOC_WL_FARE_FAMILY_INBOUND = By.cssSelector(".inbound>span>ul");
  protected final static By LOC_IN_DEAPARTURE_TOGGLE_BUTTON_HEADER = By.cssSelector(".panel.list>header>h1>a");
  protected final static By LOC_IN_DEPARTURE_HEADER = By.cssSelector(".sum>h1");
  protected final static By LOC_IN_DEPARTURE_DATE = By.cssSelector(".sum>p");
  protected final static By LOC_IN_DEPARTURE_CITIES = By.cssSelector(".sum>p>span");
  protected final static By LOC_IN_DEPARTURE_FLIGHT_NO = By.cssSelector(".flight-number>strong");
  protected final static By LOC_IN_FARE_PRICE = By.cssSelector(".price>.data");
  protected final static By LOC_IN_SELECT_INBOUND_FLIGHT = By.cssSelector(".inbound>span>ul");
  protected final static By LOC_WL_LOC_SELECT_INBOUND_FLIGHTS = By.cssSelector(".inbound>span>ul>li");
  protected final static By LOC_IN_FLIGHT_NO = By.cssSelector(".flight-number");
  protected final static By LOC_IN_PRICE = By.cssSelector(".price>strong");
  protected final static By LOC_WL_FARE_DEAL = By.cssSelector(".panel.list");


  public ATCAvailablityPage(SeleniumSEPTest test) {
    super(test);

    // Checking if we landed on the right page
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_IN_FLIGHT_NO, 30)){
      reporter.fail("This is not ATCAvailability Page");
    }else{
      reporter.reportPassed("ATCAvailability Page", "In ATCAvailability Page");
    }
  }


  /**
   * Validate ATC Availablity Page
   * @throws IOException
   */
  public void validateATCAvailablityPage() throws IOException{

    ValidateATCPageOverview();

  }

  private void ValidateATCPageOverview() throws IOException{
    int fareFamilyCount = 0;
    CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_DEPARTURE_HEADER ,"Departure Header");
    CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_DEPARTURE_DATE, "Departure Date");

    WebElement date = CheckUtils.getElement(getTest(), LOC_IN_DEPARTURE_DATE);
    if(date.getText().contains(getValue("DEPARTURE DATE").trim())){
      reporter.reportPassed("Departure Date : ",date.getText());
    }
    else{
      reporter.fail("Departure Date not matching");
    }

    WebElement fromCity = CheckUtils.getElements(getTest(), LOC_IN_DEPARTURE_CITIES).get(0);
    if(fromCity!=null && fromCity.isDisplayed()){
      reporter.reportPassed("Departure City : ", fromCity.getText());
    }
    else{
      reporter.fail("Departure City not present");
    }

    WebElement toCity = CheckUtils.getElements(getTest(), LOC_IN_DEPARTURE_CITIES).get(1);
    if(toCity!=null && toCity.isDisplayed()){
      reporter.reportPassed("Arrival City : ", toCity.getText());
    }
    else{
      reporter.fail("arrival City not present");
    }

    List<WebElement> departureFareFamilies = CheckUtils.getElements(getTest(), LOC_BT_DEPARTURE_TOGGLE_BUTTONS_EXPAND_COLLAPSE);
    for(WebElement departureFare : departureFareFamilies){
      reporter.report("Fare Family", departureFare.getText() +" present");
      fareFamilyCount++;
    }
    if(fareFamilyCount!=4){
      reporter.fail("Invalid Departure Fares Family count");
    }
  }
  /**
   * Select First Flight Displayed
   * @throws IOException
   */
  public void selectFirstFlight() throws IOException{
    WaitUtils.waitForElementPresent(getTest(), LOC_IN_SELECT_INBOUND_FLIGHT, 30);
    WebElement firstSector = CheckUtils.getElements(getTest(), LOC_IN_SELECT_INBOUND_FLIGHT).get(0);
    List<WebElement> flightsElt = firstSector.findElements( LOC_WL_LOC_SELECT_INBOUND_FLIGHTS);
    for(WebElement flights : flightsElt){
      WebElement flightNo =flights.findElement(LOC_IN_FLIGHT_NO);
      WebElement price = flights.findElement(LOC_IN_PRICE);
      if(price!=null && price.isDisplayed()){
        addValue("CURRENCY", price.getText());
      }
      if(flightNo!=null && flightNo.isDisplayed()){
        addValue("OUTBOUND FLIGHT NO", flightNo.getText());
        ClickUtils.click(getTest(), flightNo);
        reporter.report("Flight Selection", "Direct Outbound Flight selected - FlightNumber : "+flightNo.getText());
        waitForOverlayLoading(60);
        break;
      }
    }

  }
  /**
   * Select Flight From Fare Family
   * @param fareDeal
   * @throws IOException
   */
  public void selectFlightFromFareFamily(String fareDeal) throws IOException{
    if(fareDeal.contains("Sweet Deals 2 to go")){
      WebElement sweetDeals2Gobutton = CheckUtils.getElements(getTest(), LOC_BT_DEPARTURE_TOGGLE_BUTTONS_EXPAND_COLLAPSE).get(0);
      if(sweetDeals2Gobutton!=null && sweetDeals2Gobutton.isDisplayed()){
        ClickUtils.clickButtonOrFail(getTest(), sweetDeals2Gobutton,"Sweet Deals 2 to go button not found" , "Sweet Deals 2 to go button clicked");
      }
      WebElement sweetDeal2GoElt = CheckUtils.getElements(getTest(), LOC_WL_FARE_DEAL).get(0);
      selectFlightFromFareFamilies(sweetDeal2GoElt);
    }
    else if(fareDeal.contains("Sweet Deals")){
      WebElement sweetDealsButton = CheckUtils.getElements(getTest(), LOC_BT_DEPARTURE_TOGGLE_BUTTONS_EXPAND_COLLAPSE).get(1);
      if(sweetDealsButton!=null && sweetDealsButton.isDisplayed()){
        ClickUtils.clickButtonOrFail(getTest(), sweetDealsButton,"Sweet Deals button not found" , "Sweet Deals  button clicked");
      }
      WebElement sweetDealsElt = CheckUtils.getElements(getTest(), LOC_WL_FARE_DEAL).get(1);
      selectFlightFromFareFamilies(sweetDealsElt);
    }
    else if(fareDeal.contains("Flexi Saver")){
      WebElement selectFlexiSaverButton = CheckUtils.getElements(getTest(), LOC_BT_DEPARTURE_TOGGLE_BUTTONS_EXPAND_COLLAPSE).get(2);
      if(selectFlexiSaverButton!=null && selectFlexiSaverButton.isDisplayed()){
        ClickUtils.clickButtonOrFail(getTest(), selectFlexiSaverButton,"Flexi Saver button not found"  , "Flexi Saver clicked");
      }

      WebElement selectFlexiSaverElt = CheckUtils.getElements(getTest(), LOC_WL_FARE_DEAL).get(2);
      selectFlightFromFareFamilies(selectFlexiSaverElt);
    }
    else if(fareDeal.contains("Flexi")){
      WebElement flexiButton = CheckUtils.getElements(getTest(), LOC_BT_DEPARTURE_TOGGLE_BUTTONS_EXPAND_COLLAPSE).get(2);
      if(flexiButton!=null && flexiButton.isDisplayed()){
        ClickUtils.clickButtonOrFail(getTest(), flexiButton,"Flexi button not found" , "Flexi button clicked");
      }

      WebElement flexiButtonElt = CheckUtils.getElements(getTest(), LOC_WL_FARE_DEAL).get(3);
      selectFlightFromFareFamilies(flexiButtonElt);
    }
    else{
      reporter.fail("Fare Deal not present");
    }
  }

  /**
   * Select Flight from fareFamilyElt
   * @param fareFamilyElt
   * @throws IOException
   */
  private void selectFlightFromFareFamilies(WebElement fareFamilyElt)throws IOException{
    List<WebElement> flightsElt = fareFamilyElt.findElements(LOC_WL_LOC_SELECT_INBOUND_FLIGHTS);
    for (WebElement flights : flightsElt){
      WebElement flightNo = flights.findElement(LOC_IN_FLIGHT_NO);
      WebElement price = flights.findElement(LOC_IN_PRICE);
      if(price!=null && price.isDisplayed()){
        addValue("CURRENCY", price.getText());
      }
      if(flightNo!=null && flightNo.isDisplayed()){
        addValue("OUTBOUND FLIGHT NO", flightNo.getText());
        ClickUtils.click(getTest(), flightNo);
        reporter.report("Flight Selection", "Direct Outbound Flight selected - FlightNumber : ");
        break;
      }
    }
  }
}
