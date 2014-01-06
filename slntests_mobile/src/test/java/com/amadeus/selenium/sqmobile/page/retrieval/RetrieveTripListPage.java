package com.amadeus.selenium.sqmobile.page.retrieval;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class RetrieveTripListPage extends SqMobileCommonPage {

  protected static final By LOC_TRIPS_TITLE = By.className(".panel.list.triplist>header>h1");
  protected static final By LOC_TRIP_REFERENCE_NUMBERS = By.cssSelector(".panel.list.triplist>section>ul>li");
  protected static final By LOC_TRIP_ROUTE = By.className("route");
  protected static final By LOC_BUTTON_RETRIEVE = By.className("secondary");

  public RetrieveTripListPage(SeleniumSEPTest test) throws Exception{
    super(test);
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_RETRIEVE, 30);
    if(elementPresent){
      reporter.reportPassed("Retrieve TripList page" , "RetriveTripList Page loaded");
    }else{
      reporter.fail("Retrive TripList Page not loaded ");
    }
  }

  /**
   * Selects the Trip From the TripList Displayed as per the PNR
   * @param PNR PNR number of the trip for which trip is to be selected
   * @throws Exception
   */
  public void selectTrip(String PNR) throws Exception {

    boolean clicked = false ;
    List<WebElement> tripLinks = CheckUtils.getElements(getTest(), LOC_TRIP_REFERENCE_NUMBERS);
    for (WebElement trip : tripLinks) {
      if (trip.getText().contains(PNR)) {
        WebElement elt = CheckUtils.getElement(getTest(), trip , LOC_TRIP_ROUTE);
        if(elt!=null && elt.isDisplayed()){
          elt.click();
          reporter.report("Retrieve TripList page ", "Select Trip route");
          clicked = true ;
        }
        break;
      }
    }
    if(clicked){
      addValue("PNR/TICKET",PNR);
      reporter.reportPassed("Retrieve TripList page", "Trip selected for PNR - " + PNR);
    }else{
      reporter.reportFailed("Retrieve TripList page", "Trip cannot selected for PNR - " + PNR);
    }
  }

  /**
   * Clicks Retrieve Buttton
   * @throws Exception
   */
  public void actionClickRetrive()  {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_RETRIEVE, "Retrive Button couldn't be clicked", "Retrived Buttton clicked successfully" );
    reporter.report("Retrieve Triplist page", "Retrieve button is clicked");
  }

}
