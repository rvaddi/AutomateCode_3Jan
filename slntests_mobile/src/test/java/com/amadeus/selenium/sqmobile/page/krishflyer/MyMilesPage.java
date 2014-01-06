package com.amadeus.selenium.sqmobile.page.krishflyer;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class MyMilesPage extends CommonKFServicesPage {


  public MyMilesPage(SeleniumSEPTest test) throws Exception{
    super(test);
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_MILES, 30);
    if(elementPresent){
      reporter.reportPassed(pageName, "MyMiles Page loaded");
    }else{
      reporter.fail("MyMiles Page not loaded ");
    }
    CheckUtils.checkPageTitle(getTest(), "SQ Mobile- Extend Miles");
    reporter.report("CHECKPOINT", "MyMiles PAGE is DISPLAYED");
  }

  //LOCATORS - MyMiles----------------------------------------------------------

  protected static By LOC_BUTTON_MILES= By.className("more");
  protected static By LOC_HEADER_MONTHS= By.cssSelector(".pageHolder>table>tbody>tr>th");

  //----------------------------------------------------------------------------

  private String pageName = "MyMiles Page";

  /**
   * Clicks Extend miles Button
   * @author bsingh
   */
  public void clickExtendMiles() {
    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_BUTTON_MILES);
    boolean buttonClicked = false ;
    for(WebElement button : buttonElts){
      if(button.isDisplayed() && button.getText().contains("Extend miles")){
        ClickUtils.clickButtonOrFail(getTest(), button, "Extend miles Button couldn't be clicked " , "Extend miles Button clicked successfully");
        buttonClicked = true ;
      }
    }if(!buttonClicked){
      reporter.reportFailed(pageName,"Extend miles Button couldn't be clicked");
    }
  }


  /**
   * Clicks Mileage claim status Button
   * @author bsingh
   */
  public void clickMileageClaimStatus() {
    List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_BUTTON_MILES);
    boolean buttonClicked = false ;
    for(WebElement button : buttonElts){
      if(button.isDisplayed() && button.getText().contains("Mileage claim status")){
        ClickUtils.clickButtonOrFail(getTest(), button, "Mileage claim status Button couldn't be clicked " , "Mileage claim status Button clicked successfully");
        buttonClicked = true ;
      }
    }if(!buttonClicked){
      reporter.reportFailed(pageName,"Mileage claim status Button couldn't be clicked");
    }
  }


  /**
   * Validates MyMilesPage
   */
  public void validateMyMilesPage(){
   boolean monthsPresent =  CheckUtils.checkElementPresent(getTest(), LOC_HEADER_MONTHS, "Months");
   List<WebElement> buttonElts = CheckUtils.getElements(getTest(), LOC_BUTTON_MILES);

   if(monthsPresent){
     reporter.reportPassed(pageName, "Months are present");
   }else{
     reporter.reportFailed(pageName, "Months are not displayed properly in the table header");
   }

   String buttonText = "";
   for(WebElement button : buttonElts){
     buttonText +=button.getText();
     }
   if(buttonText.contains("Extend miles")){
     reporter.reportPassed(pageName,"Extend Miles Button displayed properly");
   }else{
     reporter.reportFailed(pageName, "Extend Miles Button not present");
   }
   if(buttonText.contains("Mileage claim status")){
     reporter.reportPassed(pageName,"Mileage claim status Button displayed properly");
   }else{
     reporter.reportFailed(pageName, "Mileage claim status Button not present");
   }
  }

}

