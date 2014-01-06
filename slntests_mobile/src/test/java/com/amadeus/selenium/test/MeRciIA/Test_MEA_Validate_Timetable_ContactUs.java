package com.amadeus.selenium.test.MeRciIA;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.amadeus.selenium.common.PageFactory;
import com.amadeus.selenium.runner.SeleniumSEPTestAdvanced;
import com.amadeus.selenium.sqmobile.helper.HelperWelcome;
import com.amadeus.selenium.sqmobile.page.availabililty.TimetableResultPage;
import com.amadeus.selenium.sqmobile.page.home.HomePage;
import com.amadeus.selenium.sqmobile.page.search.TimetableSearchPage;
import com.amadeus.selenium.sqmobile.page.search.TimetableSearchPage.TripType;
import com.amadeus.selenium.sqmobile.page.staticpages.ContactUsPage;
import com.amadeus.selenium.sqmobile.page.welcome.WelcomePage;
/**
 * A sample test for Booking Flow
 * @author RVADDI
 * Test ID : 6465 - MEA - 02 - TimeTable Validation and ContactUs Validation
 */
public class Test_MEA_Validate_Timetable_ContactUs extends SeleniumSEPTestAdvanced{
	//Local Setup -- Required only during maintenance
	@Override
	public void localSetUp() throws Exception {
		//comment this once coding is done
		setDriverClass(FirefoxDriver.class);
		setBaseUrl("http://nceetvqanlb24.dev.amadeus.net/");
		setDebugMode(false);
	}
	@Before
	public void resizeBrowser(){
		getDriverInstance().manage().window().setSize(new Dimension(600,800));
	}
	@Test
	public void scenario() throws Exception {
		driver.manage().deleteAllCookies();
		WelcomePage welcomepage = HelperWelcome.openWelcomePage();
		HomePage homePage =PageFactory.getPageObject(HomePage.class);
		/*	welcome.selectChangeLanguage();
		welcome.changeLocalPreference(welcome.getValue("Country"), welcome.getValue("Language"), false);*/
		homePage.sqMobileMenu.actionTimetable();
		TimetableSearchPage timetableSearchPage = PageFactory.getPageObject(TimetableSearchPage.class);
		TimetableResultPage timetableResultPage=timetableSearchPage.actionSearchTimetable(TripType.OW);
		timetableResultPage.clickOnFlight();
		timetableResultPage.validateFlights(com.amadeus.selenium.sqmobile.page.availabililty.TimetableResultPage.TripType.OW);
		timetableSearchPage.clickBackToHome();
		WelcomePage welcomepage1 = HelperWelcome.openWelcomePage();
		HomePage homePage1 =PageFactory.getPageObject(HomePage.class);
		homePage1.sqMobileMenu.clickContactUs();
		ContactUsPage contactUs=PageFactory.getPageObject(ContactUsPage.class);
		contactUs.validateContactDetails();
	}
}