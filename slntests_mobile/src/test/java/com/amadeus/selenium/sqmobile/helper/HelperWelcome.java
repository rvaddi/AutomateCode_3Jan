package com.amadeus.selenium.sqmobile.helper;


import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.sqmobile.page.welcome.SQMobileWelcomePage;
import com.amadeus.selenium.sqmobile.page.welcome.WelcomePage;
/**
 * Helper Class to open SQ Mobile
 * @author Vigneshwaran Balasubramanian
 *
 */
public class HelperWelcome {

  /**
   * Method opening SQ Mobile with all default values for NRE
   * @return  the welcomepage instance
   * @throws Exception
   */
  public static WelcomePage openWelcomePage() throws Exception{
    return openWelcomePageWith(null ,null);
  }

  public enum Customer{
    SQMOBILE
  }

  /**
   *Opening SQ Mobile with  parameter values
   * @param siteCode - the sitecode
   * @param language - the language
   * @return the welcomePage instance
   * @throws Exception
   */
  public static WelcomePage openWelcomePageWith(String siteCode, String  language) throws Exception {
    SqMobileCommonPage sqmobileCommonPage = PageFactory.getPageObject(SqMobileCommonPage.class);

    if (siteCode != null) {
      sqmobileCommonPage.setInputValue(SqMobileCommonPage.IN_SITECODE, siteCode);
    }
    if (language != null) {
      sqmobileCommonPage.setInputValue(SqMobileCommonPage.IN_LANGUAGE, language);
    }

    sqmobileCommonPage.actionOpenWelcomePage();

    return PageFactory.getPageObject(WelcomePage.class);
  }


  /**
   * Method opening SQ Mobile with all default values for NRE
   * @return  the welcomepage instance
   * @throws Exception
   */
  public static WelcomePage openCustomerSpecificWelcomePage(Customer customer) throws Exception{
     return openCusotmerSpecificWelcomePageWith(null ,null , customer);


  }


  /**
   *Opening SQ Mobile with  parameter values
   * @param siteCode - the sitecode
   * @param language - the language
   * @return the welcomePage instance
   * @throws Exception
   */
  public static WelcomePage openCusotmerSpecificWelcomePageWith(String siteCode, String  language , Customer customer) throws Exception {
    SqMobileCommonPage sqmobileCommonPage = PageFactory.getPageObject(SqMobileCommonPage.class);

    if (siteCode != null) {
      sqmobileCommonPage.setInputValue(SqMobileCommonPage.IN_SITECODE, siteCode);
    }
    if (language != null) {
      sqmobileCommonPage.setInputValue(SqMobileCommonPage.IN_LANGUAGE, language);
    }

    sqmobileCommonPage.actionOpenWelcomePage();

    if(customer.equals(Customer.SQMOBILE))
      return PageFactory.getPageObject(SQMobileWelcomePage.class);
    else
      return PageFactory.getPageObject(WelcomePage.class);
  }

}
