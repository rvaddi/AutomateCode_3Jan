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
/**
 * ATC Inbound Page
 * @author vbalasubramanian
 *
 */
public class ATCInboundPage extends SqMobileCommonPage{

  protected final static By LOC_IN_FLIGHT_NO = By.cssSelector(".flight-number");
  protected final static By LOC_IN_DEPARTURE_HEADER = By.cssSelector(".panel.sum.inBound>header>h1");
  protected final static By LOC_IN_DEPARTURE_FLIGHT_NO = By.cssSelector(".panel.sum.inbound>section>p>.flight-number>strong");
  protected final static By LOC_WL_DEPARTURE_LOCATION = By.cssSelector(".panel.sum.inbound>section>p>span>.city");
  protected final static By LOC_IN_SELECT_INBOUND_FLIGHT = By.cssSelector(".inbound>span>ul");
  protected final static By LOC_WL_SELECT_INBOUND_FLIGHTS_LIST = By.cssSelector(".inbound>span>ul>li");
  protected final static By LOC_IN_PRICE = By.cssSelector(".price>strong");


  public ATCInboundPage(SeleniumSEPTest test) throws Exception{
    super(test);
    // Checking if we are on the right page
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_WL_SELECT_INBOUND_FLIGHTS_LIST, 30)){
      reporter.fail("This is not ATCInboundPage Page");
    }else{
      reporter.reportPassed("ATCInboundPage Page", "In ATCInboundPage Page");
    }
  }


  /**
   * Validate ATC Inbound Page
   * @throws IOException
   */
  public void validateATCInboundPage() throws IOException{
    CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_DEPARTURE_HEADER, "Departure Header");
    CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_DEPARTURE_FLIGHT_NO, "Departure Flight No");
    WebElement departureFlightNoElt = CheckUtils.getElement(getTest(), LOC_IN_DEPARTURE_FLIGHT_NO);

    if(departureFlightNoElt.getText().contains(getValue("OUTBOUND FLIGHT NO").trim())){
      reporter.reportPassed("Departure Flight Number matched : ", departureFlightNoElt.getText());
    }
    else{
      reporter.fail("Departure Flight Number  not matching");
    }
    List<WebElement> departureLocation = CheckUtils.getElements(getTest(), LOC_WL_DEPARTURE_LOCATION);
    for(WebElement depElt:departureLocation){
      if(getValue("DEPARTURE LOCATION").trim().contains(depElt.getText())){
        reporter.reportPassed("Departure Location matched : ", depElt.getText());
      }
      else{
        reporter.fail("Departure Location not found");
      }
    }
  }

  /**
   * Select First Return Flight
   * @throws IOException
   */
  public void selectFirstReturnFlight() throws IOException{
    CheckUtils.checkMandatoryElementPresent(getTest(), LOC_IN_SELECT_INBOUND_FLIGHT, "Inbound Flights");
    WebElement firstSector = CheckUtils.getElements(getTest(), LOC_IN_SELECT_INBOUND_FLIGHT).get(0);
    List<WebElement> flightsElt = firstSector.findElements(LOC_WL_SELECT_INBOUND_FLIGHTS_LIST);
    for(WebElement flights : flightsElt){
      WebElement flightNo = flights.findElement(LOC_IN_FLIGHT_NO);
      WebElement price = flights.findElement(LOC_IN_PRICE);
      if(price!=null && price.isDisplayed()){
        updateValue("CURRENCY", price.getText());
      }
      if(flightNo!=null && flightNo.isDisplayed()){
        addValue("INBOUND FLIGHT NO", flightNo.getText());
        ClickUtils.click(getTest(), flightNo);
        reporter.report("Flight Selection",flightNo.getText());
        break;
      }
    }
  }


}

