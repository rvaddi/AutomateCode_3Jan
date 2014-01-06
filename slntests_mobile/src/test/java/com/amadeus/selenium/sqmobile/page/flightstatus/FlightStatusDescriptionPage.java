package com.amadeus.selenium.sqmobile.page.flightstatus;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class FlightStatusDescriptionPage extends SqMobileCommonPage {

  protected static final By LOC_FLIGHT_NUMBER = By.cssSelector(".panel>header>h1");
  protected static final By LOC_FLIGHT_DETAIL_HEADERS = By.cssSelector(".details>dl>dt");
  protected static final By LOC_FLIGHT_DETAIL_DATA = By.cssSelector(".details>dl>dd");

  public FlightStatusDescriptionPage(SeleniumSEPTest test) throws Exception {
    super(test);
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_FLIGHT_NUMBER, 30);
    if(elementPresent){
      reporter.reportPassed("FlightStatusDescription Page", "In FlightStatusDescription Page");
    }else{
      reporter.fail("FlightStatusDescription Page not loaded ");
    }
  }

  private String pageName = "FlightStatusDescription Page";

  public void validateFlightStatusDesc() throws IOException{
    WebElement flightNumElt = CheckUtils.getElement(getTest(), LOC_FLIGHT_NUMBER);
    List<WebElement> headerElts = CheckUtils.getElements(getTest(),LOC_FLIGHT_DETAIL_HEADERS);
    List<WebElement> dataElts = CheckUtils.getElements(getTest(), LOC_FLIGHT_DETAIL_DATA);

    if(flightNumElt!=null){
      if(flightNumElt.getText().equalsIgnoreCase(getValue("FLIGHT NUMBER"))){
        reporter.reportPassed("FlightNumber displayed : ", flightNumElt.getText());
      }else{
        reporter.reportFailed("FlightNumber displayed : ", flightNumElt.getText());
      }
    }else{
      reporter.reportFailed("FlightStatusDescription Page : ", "Flight Number Elt is not displayed");
    }

    if(headerElts!=null && dataElts!=null && headerElts.size()== dataElts.size()){
     if(headerElts.get(0).getText().contains("Date & time") && !dataElts.get(0).getText().isEmpty()){
       reporter.reportPassed("Date & time : ", dataElts.get(0).getText());
     }else{
       reporter.reportFailed( pageName , "Date & Time is not displayed properly" );
     }
     if(headerElts.get(1).getText().contains("Number of stops") && !dataElts.get(1).getText().isEmpty()){
       reporter.reportPassed("Number of stops: ", dataElts.get(1).getText());
     }else{
       reporter.reportFailed( pageName , "Number of stops is not displayed properly" );
     }
     if(headerElts.get(2).getText().contains("Aircraft") && !dataElts.get(2).getText().isEmpty()){
       reporter.reportPassed("Aircraft : ", dataElts.get(2).getText());
     }else{
       reporter.reportFailed( pageName , "Aircraft is not displayed properly" );
     }
     if(headerElts.get(3).getText().contains("Total travel time") && !dataElts.get(3).getText().isEmpty()){
       reporter.reportPassed("Total travel time : ", dataElts.get(3).getText());
     }else{
       reporter.reportFailed( pageName , "Total travel time is not displayed properly " );
     }

    }

  }



}