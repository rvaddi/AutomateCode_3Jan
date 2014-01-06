package com.amadeus.selenium.sqmobile.page.faredeal;

import java.io.IOException;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.utils.WaitUtils;

public class SQMobileDealPage extends FareDealsPage  {

 // public final  SQMobileMenu sqMobileMenu;

  public SQMobileDealPage(SeleniumSEPTest test) throws Exception {
    super(test);
    // TODO Auto-generated constructor stub

  //  sqMobileMenu = PageFactory.getPageObject(SQMobileMenu.class) ;

    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_LI_FARE_DEALS_COUNTRY, 120);
    if(elementPresent){
      reporter.reportPassed(pageName, "FareDeals Page loaded");
    }else{
      reporter.reportFailed(pageName,"FareDeals Page NOT loaded ");
    }
  }

  /**
   * Validates Fare Deal Page
   *
   * @author sankar
   */
   public void validateDealpage(){

     validateFareDealsPage();
     validateSpecificNumOfOffers(0);
   }

   /**
    * Fill Search Criteria in Fare Condition Page
    *
    * @throws NumberFormatException,Exception
    * @author sankar
    */
   public void FillFareDealpage() throws NumberFormatException, Exception{

     String country = getValue("Country");
     String[] FilterOption = {"PR","FT","TP"};

     selectCountry(country);
     validateMultipleOffers();

     if(FilterOption[0].equalsIgnoreCase(getValue("Filter Option"))){
       clickPriceFilter();
     } else if(FilterOption[1].equalsIgnoreCase(getValue("Filter Option"))){
       SelectFromToFilter();
       ValidateFromToDealOffer();
     }else if(FilterOption[2].equalsIgnoreCase(getValue("Filter Option"))){
       SelectTravelPeriod();
       ValidateTravelPeriodOffer();
     } else{
       reporter.reportWarning("Input value", "Filter option is not correct or not entered");
     }

    // validateMultipleOffers();
    // selectDeal();
   }

   /**
    * Select FromTo Filter Option in Fare Deal page
    *
    * @author sankar
    */
   public void SelectFromToFilter(){

     clickFromToFilter();
     ValidateFromToDefaultvalue();
   }

   /**
    * Select TravelPeriod Filter Option in Fare Deal page
    *
    * @author sankar
    */
   public void SelectTravelPeriod(){
     clickTravelPeriod();
     ValidateTravelPeriodDefaultValue();
   }

   /**
    * Validate FromTo Filter Option in Fare Deal page
    *
    * @throws IOException
    * @author sankar
    */
   public void ValidateFromToDealOffer() throws IOException{

     ChangeTovalue();
     ChangeFromValue();
     ChangeBothFromToValue();
   }

   /**
    * Validate TravelPeriodOffer Filter Option in Fare Deal page
    *
    * @throws IOException
    * @author sankar
    */
   public void ValidateTravelPeriodOffer() throws NumberFormatException, IOException, Exception{

     verifyTravelPeriodDealOffer(LOC_LI_FARE_DEALS_OFFERS,LOC_LI_FARE_DEALS_Travel_From,LOC_LI_FARE_DEALS_Travel_To,"Travel Period");
     VerifyPastDateinDatePicker();
     ChangeDepArrivalDate();
     ClickFromToAndBack();
     ClickPriceAndBack();
   }
}
