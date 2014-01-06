package com.amadeus.selenium.sqmobile.page.payment;

import com.amadeus.selenium.sqmobile.page.confirmation.ConfirmationPage;

public interface IPayment {


  public abstract ConfirmationPage clickContinue() throws Exception;

  public abstract void cancelBooking() throws Exception ;

  public abstract void fillPaymentInfo() throws Exception ;

  public abstract void fillAddressDetails(String Address1,String Address2,String City,String State,String zipCode,String Country)throws Exception;

}
