package com.amadeus.selenium.sqmobile.page.staticpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class ContactUsPage extends SqMobileCommonPage {

  public ContactUsPage(SeleniumSEPTest test) throws Exception{
    super(test);
    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_CONTACT_HEADER, 30);
    if(elementPresent){
      reporter.reportPassed("ContactUs : ", "ContactUs Page loaded");
    }else{
      reporter.fail("ContactUs Page not loaded ");
    }


    CheckUtils.checkPageTitle(getTest(), "Home");
    reporter.report("CHECKPOINT", "ContactUs PAGE is DISPLAYED");
  }

  //LOCATORS - ContactUsPage----------------------------------------------------

  protected static By LOC_CONTACT_HEADER= By.cssSelector(".panel.contact>header>h1");
  protected static By LOC_PANELS_COUNTRIES = By.className("on");
  protected static By LOC_CONTACT_DATA = By.tagName("dt");
  //----------------------------------------------------------------------------

  private String pageName = "ContactUs Page";

  /**
   * Validates Header Text
   * @throws Exception
   * @author bsingh
   */
  public void validateHeader() throws Exception{
    WebElement contactHeaderElt = CheckUtils.getElement(getTest(),LOC_CONTACT_HEADER);
    if(contactHeaderElt!=null ){
      if(contactHeaderElt.getText().contains("Contact Us")){
        reporter.reportPassed("Contact Header : ", contactHeaderElt.getText());
      }else{
        reporter.reportPassed("Contact Header : ", contactHeaderElt.getText());
      }
    }else{
      reporter.reportFailed(pageName, "Contact Header Elt is not displayed");
    }
  }


  /**
   * Validates ContactDetails for the countries
   * @throws Exception
   * @author bsingh
   */
  public void validateContactDetails() throws Exception {
    List<WebElement> countryPanelElts = CheckUtils.getElements(getTest(), LOC_PANELS_COUNTRIES);
    boolean displayedProperly = true ;
    if(countryPanelElts!=null){
      for(WebElement countryInfo : countryPanelElts){
        WebElement contactData = CheckUtils.getElement(getTest(), countryInfo , LOC_CONTACT_DATA);
        if(contactData==null ){
          reporter.reportFailed(pageName, "ContactInfo for country(s) not displayed properly" );
          displayedProperly = false;
        }
      }
    }
    if(displayedProperly){
      reporter.reportPassed(pageName, "Contact Details are Displayed properly");
    }
  }

}
