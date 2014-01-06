package com.amadeus.selenium.sqmobile.page.meal;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.FillUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class MealPreferencePage extends SqMobileCommonPage {

  protected final static By LOC_TRIP_PANELS =  By.cssSelector(".panel.trip");
  protected final static By LOC_SELECT_MEAL =  By.cssSelector("[id*='meals']");
  protected final static By LOC_FLIGHT_NUMBER = By.cssSelector(".flight-number>strong");
  protected final static By LOC_BUTTON_SUBMIT = By.className("validation");

  public MealPreferencePage(SeleniumSEPTest test) throws Exception{
    super(test);

    // Check that we're on the right page.
    if(! WaitUtils.waitForElementPresent(getTest(), LOC_TRIP_PANELS, 30)){
      reporter.fail("This is not MealPreference page");
    }else{
      reporter.reportPassed("MealPreference page", "In MealPreference page");
    }
  }

  String pageName = "MealPreference Page" ;
  //-------------------------------------------------------------------------------------

  public enum Itinerary {
    DEPARTURE, RETURN ;
  }


  /**
   * Selects Meal Preference from the dropdown
   * @param itinerary DEPARTURE Or RETURN
   * @param value Value on the basis of which meal from the dropdown is to be selected
   * @author bsingh
   */
  public void selectMealPreference(Itinerary itinerary , String value){
    List<WebElement> panelElts = CheckUtils.getElements(getTest(), LOC_TRIP_PANELS);
    if(panelElts!=null && !panelElts.isEmpty()){
      if(itinerary.equals(Itinerary.DEPARTURE)){
        WebElement selectBox = CheckUtils.getElement(getTest(), panelElts.get(0), LOC_SELECT_MEAL );
        FillUtils.selectByValue(getTest(), selectBox ,  value);
      }else{
        WebElement selectBox = CheckUtils.getElement(getTest(), panelElts.get(1), LOC_SELECT_MEAL );
        FillUtils.selectByValue(getTest(), selectBox ,  value);
      }
    }else{
      reporter.report(pageName, itinerary+ " Panel : is not displayed properly");
    }
  }


  /**
   * Validates Itinenary
   * @param itinerary DEPARTURE Or RETURN
   * @throws IOException
   * @author bsingh
   */
  public void validateItinenary(Itinerary itinerary ) throws IOException{
    List<WebElement> panelElts = CheckUtils.getElements(getTest(), LOC_TRIP_PANELS);
    if(panelElts!=null && !panelElts.isEmpty()){
      if(itinerary.equals(Itinerary.DEPARTURE)){
        WebElement flightNumber = CheckUtils.getElement(getTest(), panelElts.get(0), LOC_FLIGHT_NUMBER);
        if( flightNumber!=null && flightNumber.getText().contains(getValue(itinerary+" DEPARTURE"))){
          reporter.reportPassed(itinerary+" Flight Number : ", flightNumber.getText().trim());
        }else{
          reporter.reportFailed(pageName , "Departure Flight Number is not displayed properly");
        }
      }else if(panelElts.size()>1 && itinerary.equals(Itinerary.RETURN)){
        WebElement flightNumber = CheckUtils.getElement(getTest(), panelElts.get(1), LOC_FLIGHT_NUMBER);
        if(flightNumber !=null && flightNumber.getText().contains(getValue(itinerary+" RETURN"))){
          reporter.reportPassed(itinerary+" RETURN", flightNumber.getText().trim());
        }else{
          reporter.reportFailed(pageName, "Return Flight Number is not displayed properly");
        }
      }
    }
  }


  /**
   * Clicks Save Button
   * @author bsingh
   */
  public void clickSave(){
    List<WebElement> submitButtons = CheckUtils.getElements(getTest(), LOC_BUTTON_SUBMIT);
    for(WebElement tempButoon : submitButtons){
      if(tempButoon.getText().contains("Save")){
        ClickUtils.clickButtonOrFail(getTest(), tempButoon, "Save Button could not clicked");
        reporter.report( pageName , "Save Button has been clicked on Search Page");
        break;
      }
    }
  }


  /**
   * Clicks Cancel Button
   * @author bsingh
   */
  public void clickCancel(){
    List<WebElement> submitButtons = CheckUtils.getElements(getTest(), LOC_BUTTON_SUBMIT);
    for(WebElement tempButoon : submitButtons){
      if(tempButoon.getText().contains("Cancel")){
        ClickUtils.clickButtonOrFail(getTest(), tempButoon, "Cancel Button could not clicked");
        reporter.report( pageName , "Cancel Button has been clicked on Search Page");
        break;
      }
    }
  }

}
