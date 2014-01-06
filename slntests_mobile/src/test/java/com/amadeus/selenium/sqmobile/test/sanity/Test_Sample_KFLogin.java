package com.amadeus.selenium.sqmobile.test.sanity;


import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.home.HomePage;
import com.amadeus.selenium.sqmobile.page.login.KFLoginPage;
import com.amadeus.selenium.sqmobile.page.welcome.WelcomePage;

/**
 * A sample test for KFLogin
 * @author bsingh
 *
 */
public class Test_Sample_KFLogin extends SeleniumSEPTest{

  //Local Setup -- Required only during maintenance
  @Override
  public void localSetUp() throws Exception {
    //comment this once coding is done
    setDriverClass(FirefoxDriver.class);
    setBaseUrl("http://nceetvqanlb22.dev.amadeus.net");
    setDebugMode(false);
  }
/*
  @Before
  public void resizeBrowser(){
    getDriverInstance().manage().window().setSize(new Dimension(600,800));
  }
*/

  @Test
  public void scenario() throws Exception {

    //***INSTANTIATION of HomePage to use methods under that class***
    WelcomePage welcomepage = HelperWelcome.openWelcomePage();
    HomePage homePage = welcomepage.changeLocalPreferences("Iceland", "GB" , false);

    homePage.clickHome2();

    //***INSTANTIATION of WelcomePage to use methods under that class***
    homePage.sqMobileMenu.actionKFLogin();

    KFLoginPage kfLoginPage = PageFactory.getPageObject(KFLoginPage.class);
    kfLoginPage.fillKFNumber( homePage.getValue("KFNumber")) ;
    kfLoginPage.fillPIN("12345");
    kfLoginPage.clickKFLogin();
    homePage.clickLogout();



  }
}