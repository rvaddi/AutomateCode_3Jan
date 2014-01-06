package com.amadeus.selenium.sqmobile.page.commonInterface;

public interface IPayment {

  public abstract void fillPaymentDetail(String Address1,String Address2,String City,String State,String zipCode,String Country)throws Exception;

  public abstract void clickContinue() throws Exception;

  public abstract void cancelBooking() throws Exception ;

}
