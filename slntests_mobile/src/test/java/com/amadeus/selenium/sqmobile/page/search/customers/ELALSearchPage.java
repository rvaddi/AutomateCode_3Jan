package com.amadeus.selenium.sqmobile.page.search.customers;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.search.SearchPage;
import com.amadeus.selenium.sqmobile.utils.CommonUtils;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

/**
 * Search Page
 * @author bsingh
 *
 */

public class ELALSearchPage extends SearchPage{

  /*
   * Web Element Identifiers
   *
   */
  protected static By LOC_LS_SEARCHPAGE_CABINCLASS = By.id("COMMERCIAL_FARE_FAMILY_1");

  String pageName = "ELALSearch Page";


  public ELALSearchPage(SeleniumSEPTest test) throws Exception {
    super(test);
  }



  /**
   * Selecting PAX Count and Cabin Class
   * @throws IOException
   */
  public void selectPAXCountAndCabin() throws IOException {

    FillUtils.selectByValue(getTest(), LOC_LS_SEARCHPAGE_CABINCLASS, getValue("Cabin Class").trim());
    FillUtils.selectByValue(getTest(), LOC_LS_SEARCHPAGE_ADULT_COUNT, getValue("Nb Adult").trim());
    //FillUtils.selectByValue(getTest(), LOC_LS_SEARCHPAGE_CHILD_COUNT, getValue("Nb Child").trim());
  }

  /**
   * Select Dates
   * @param roundTrip : WebElement for roundTrip radio button
   * @param oneWay : WebElement for OneWay radio button
   * @param utils : Utils for handling date operation
   * @param tripType : Trip type to be selected RT or OW
   * @throws IOException
   */
  public void selectDates(WebElement roundTrip, WebElement oneWay, CommonUtils utils, String[] tripType)
  throws IOException {
    if (getValue("Trip Type").trim().equals(tripType[0])){

      ClickUtils.click(getTest(), oneWay);
      String Dep = utils.addDate("d MMMM yyyy", "Date", new Integer (getValue("Dep Gap").trim()));
      String DepDate = (Dep.split(" "))[0];
      String DepMonth = (Dep.split(" "))[1]+" "+(Dep.split(" "))[2];
      FillUtils.selectByVisibleText(getTest(), LOC_LS_SEARCHPAGE_DEPARTURE_DATE, DepDate);
      FillUtils.selectByVisibleText(getTest(), LOC_LS_SEARCHPAGE_DEPARTURE_MONTH, DepMonth);
    }
    else{

      ClickUtils.click(getTest(), roundTrip);
      String Dep = utils.addDate("d MMMM yyyy", "Date", new Integer (getValue("Dep Gap").trim()));
      String DepDate = (Dep.split(" "))[0];
      String DepMonth = (Dep.split(" "))[1]+" "+(Dep.split(" "))[2];

      FillUtils.selectByVisibleText(getTest(), LOC_LS_SEARCHPAGE_DEPARTURE_DATE, DepDate);
      FillUtils.selectByVisibleText(getTest(), LOC_LS_SEARCHPAGE_DEPARTURE_MONTH, DepMonth);

      String Ret = utils.addDate("d MMMM yyyy", "Date", new Integer (getValue("Ret Gap").trim()));
      String RetDate = (Ret.split(" "))[0];
      String RetMonth = (Ret.split(" "))[1]+" "+(Ret.split(" "))[2];

      FillUtils.selectByVisibleText(getTest(), LOC_LS_SEARCHPAGE_RETURN_DATE, RetDate);
      FillUtils.selectByVisibleText(getTest(), LOC_LS_SEARCHPAGE_RETURN_MONTH, RetMonth);
    }
  }


  /**
   * Handling Flexi Dates operation
   * @throws IOException
   */
  public void handleFlexiDates() throws IOException {
  }

  public void clickSearchButton(){
    super.clickSearchButton();
    WaitUtils.wait(5);
  }

}
