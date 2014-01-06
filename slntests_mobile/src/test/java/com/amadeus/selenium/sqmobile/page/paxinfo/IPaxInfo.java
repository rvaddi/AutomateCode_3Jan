package com.amadeus.selenium.sqmobile.page.paxinfo;

import com.amadeus.selenium.sqmobile.page.paxinfo.PaxInfoPage.Customer;
import com.amadeus.selenium.sqmobile.page.payment.IPayment;
import com.amadeus.selenium.sqmobile.page.seat.SeatMapPage;

public interface IPaxInfo {

  public abstract void fillContactInfo(String contacType ,String areadCode,String contactNumber , String email,String countryCode , String smsNotificationNumber);

  public abstract IPayment continuePayment(Customer customer) throws Exception;

  public abstract SeatMapPage clickSelectSeat() throws Exception;

  public abstract void fillPaxInfo() throws Exception;
}
