package com.amadeus.selenium.sqmobile.page.seat;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.payment.IPayment;
import com.amadeus.selenium.sqmobile.page.payment.PaymentPage;
import com.amadeus.selenium.sqmobile.page.payment.customers.AirchinaPaymentPage;
import com.amadeus.selenium.sqmobile.page.payment.customers.CarabPaymentPage;
import com.amadeus.selenium.sqmobile.page.payment.customers.IcelandAirPaymentPage;
import com.amadeus.selenium.sqmobile.page.payment.customers.SaudiPaymentPage;
import com.amadeus.selenium.sqmobile.page.payment.customers.TAMPaymentPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;


public class SeatMapPage extends CommonSeatMapPage{

  protected static final By LOC_BUTTON_REVERT = By.id("revertBtn");
  protected static final By LOC_BT_PAX_INFO_CONTINUE_PAYMENT = By.className("validation");

  public SeatMapPage(SeleniumSEPTest test) throws Exception{
    super(test , "SeatMap Page");

    boolean elementPresent = WaitUtils.waitForElementPresent(getTest(), LOC_BUTTON_REVERT, 30);
    if(elementPresent){
      reporter.reportPassed("SeatMap Page", "In SeatMap Page ");
    }else{
      reporter.fail("SeatMap Page not loaded ");
    }

  }


  public enum Customer{
    SAUDI , AIRCHINA,ICELANDAIR,TAM,CARAB,SQMOBILE
  }

  /**
   * Validates FlightDetails in the moving banner
   * @throws Exception
   */
  public void validateBannerFlightDetails(Itinerary itinerary) throws Exception {

    List<WebElement> flightElt = CheckUtils.getElements(getTest(), LOC_MOVING_BANNER_FLIGHT_INFO);
    int size =  flightElt.size() ;
    // validating airline
    if(flightElt!= null && size > 2 &&  flightElt.get(2).getText().equals(getValue(itinerary+" FLIGHT"))){
      reporter.reportPassed("Booking Seat Map : ", "Airline details are displayed properly");
    }else{
      reporter.reportFailed("Booking Seat Map : ","Airline details are not displayed properly");
    }
    // validating aircraft
    if(flightElt!= null &&  size > 3 &&  flightElt.get(3).getText().equals(getValue(itinerary+" AIRCRAFT"))){
      reporter.reportPassed("Booking Seat Map : ", "Aircraft Details are displayed properly");
    }else{
      reporter.reportFailed("Booking Seat Map : ", "Aircraft details are not displayed properly");
    }
  }


  /**
   * Clicks Revert Button
   * @throws Exception
   */

  public void clickCancel()throws Exception {
    ClickUtils.clickButtonOrFail(getTest(), LOC_BUTTON_REVERT, "Revert Button couldn't be clicked", "Revert Button clicked");
    reporter.report("Seat Maps page", "Revert button clicked");
  }


  public IPayment continuePayment(Customer customer)throws Exception {

    WebElement payment = CheckUtils.getElement(getTest(), LOC_BT_PAX_INFO_CONTINUE_PAYMENT);
    if (payment!=null && payment.isDisplayed()){
      payment.click();
      waitForOverlayLoading(30);
      reporter.reportPassed("SeatMap Page", "Continue to Payment link is clicked");
    }
    else{
      reporter.reportFailed("SeatMap Page", "Continue to Payment link is not displayed");
    }

    return getCusomerPaymentObject(customer);
  }



  public IPayment getCusomerPaymentObject(Customer customer) throws Exception{
    if(customer!=null){
      if(Customer.SAUDI.equals(customer)){
        return PageFactory.getPageObject(SaudiPaymentPage.class);
      }
      else if(Customer.AIRCHINA.equals(customer)){
        return PageFactory.getPageObject(AirchinaPaymentPage.class);
      }
      else if(Customer.ICELANDAIR.equals(customer)){
        return PageFactory.getPageObject(IcelandAirPaymentPage.class);
      }
      else if(Customer.CARAB.equals(customer)){
        return PageFactory.getPageObject(CarabPaymentPage.class);
      }
      else if(Customer.TAM.equals(customer)){
        return PageFactory.getPageObject(TAMPaymentPage.class);
      }
      else if(Customer.SQMOBILE.equals(customer)){
        return PageFactory.getPageObject(PaymentPage.class);
      }
    }
    else{
      reporter.fail("Customer not found");
    }
    return null;
  }
}
