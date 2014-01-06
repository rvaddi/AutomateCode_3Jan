package com.amadeus.selenium.test.MeRciIA;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTestAdvanced;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.home.HomePage;
import com.amadeus.selenium.sqmobile.page.retrieval.SaudiTravellerDetailsPage;
/**
 * Update Traveller details
 * @author RVADDI
 * VERSION :: 1.0
 * TEST ID :: 7157
 * Tested url :http://nceetvqanlb24.dev.amadeus.net/plnext/mobile4SVNRE/MWelcome.action?SITE=SVMCSVMC&LANGUAGE=GB&MT=A
 * Actual url in QC: http://nceetvqanlb24.dev.amadeus.net/plnext/mobile4MS/MWelcome.action?SITE="BFHOBFHO"&LANGUAGE=GB&MT=A
 */
public class Test_Saudi_IA_Update_TravellerDetails extends SeleniumSEPTestAdvanced {
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
	public void updateTravellerInfoTest() throws Exception {
//		driver.manage().deleteAllCookies();
		HelperWelcome.openWelcomePage();
		if (!getDriverInstance().getCurrentUrl().contains(getBaseUrl())) {
			HelperWelcome.openWelcomePage();
		}
		reporter.report("Jsession", (String)getJSessionID().toString());
		HomePage homePage = PageFactory.getPageObject(HomePage.class);
		homePage.validateHomePage();
		homePage.fillInfo(homePage.getValue("LastName"),homePage.getValue("Book RefNo"),false);
		SaudiTravellerDetailsPage saudiTravellerDetails=PageFactory.getPageObject(SaudiTravellerDetailsPage.class);
		saudiTravellerDetails.clickOnPassengerInfo();
		saudiTravellerDetails.updateMobileAndEmail(homePage.getValue("CountryCode"),
												   homePage.getValue("MobileNo"),
												   homePage.getValue("Passenger_EmailId"));
		reporter.reportPassed("Pass", "Update Mobile and Email Passed");
	}
}
