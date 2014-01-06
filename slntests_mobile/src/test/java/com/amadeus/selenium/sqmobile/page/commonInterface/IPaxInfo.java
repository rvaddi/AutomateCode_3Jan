package com.amadeus.selenium.sqmobile.page.commonInterface;

public interface IPaxInfo {

  public abstract void fillPaxInfoDetails(String contacType ,String areadCode,String contactNumber , String email,String countryCode , String smsNotificationNumber);

  public abstract IPayment continuePayment() throws Exception;

  public abstract void selectSeat();
}
