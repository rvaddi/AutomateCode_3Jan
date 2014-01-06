package com.amadeus.selenium.test.MeRciIA;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTestAdvanced;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.home.HomePage;

/**
 * SELECT MEAL PREFERENCE FOR MULTI PAX
 * @author RVADDI
 * VERSION :: 1.0
 * TEST ID :: 5461
 *
 */
public class Test_EgyptAir_IA_Meal_Multi_Pax extends SeleniumSEPTestAdvanced{
	  @Override
	  public void localSetUp() throws Exception {
	    setDriverClass(FirefoxDriver.class);
	    setBaseUrl("http://nceetvqanlb24.dev.amadeus.net");
	    setDebugMode(false);
	  }

	  @Before
	  public void resizeBrowser(){
	    getDriverInstance().manage().window().setSize(new Dimension(600,800));
	  }


	  	@Test
		public void mealMultiPaxTest() throws Exception {
			driver.manage().deleteAllCookies();
			HelperWelcome.openWelcomePage();
			if (!getDriverInstance().getCurrentUrl().contains(getBaseUrl())) {
				HelperWelcome.openWelcomePage();
			}
			HomePage homePage = PageFactory.getPageObject(HomePage.class);
			homePage.validateHomePage();
			homePage.fillInfo(homePage.getValue("LastName"),homePage.getValue("Book RefNo"),false);

			reporter.reportPassed("Pass", "HomePage Passed");



			 String failedSteps = this.getFailedSteps();
			    if(failedSteps != null && failedSteps.length() > 0) {
			      reporter.fail("Validation(s) failed");
			    }

		}

}
