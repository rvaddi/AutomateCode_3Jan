package com.amadeus.selenium.sqmobile.page.commonpage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amadeus.selenium.common.CommonPage;
import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.payment.customers.TAMPaymentPage;
import com.amadeus.selenium.sqmobile.utils.ExcelDataReader;
import com.amadeus.selenium.sqmobile.utils.condition.WaitMultipleOverlayCondition;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class SqMobileCommonPage extends CommonPage{

	public SqMobileCommonPage(SeleniumSEPTest test) {
		super(test);
	}
	//  public static String LOC_XLS_SQ = "\\\\nceetvdev30\\qad\\SeleniumTests\\TestData\\SQMobile\\SQMobile Test Data.xls";
	public static String LOC_XLS_SQ = "D:/SQMobile Test Data.xls";
	public static String strOutPutFolderPath;
	public static String strOutputFilePath;;

	//GLOBAL
	protected static final By LOC_LI_ALL_KF_LOGOUT =By.partialLinkText("Logout");
	protected static final By LOC_LI_ALL_HOME =By.partialLinkText("Home");
	protected static final By LOC_LI_ALL_HOME_2 = By.className("home");
	protected static final By LOC_LI_ALL_BACK = By.partialLinkText("Back");
	protected static final By LOC_TX_ALL_ERROR_MESSAGE_1 = By.className("messageError");
	protected static final By LOC_TX_ALL_ERROR_MESSAGE_2 = By.className("messageTitle");
	protected static final By LOC_TX_ALL_ERROR_MESSAGE_3 = By.className("listStyleDots");
	protected static final By LOC_TX_ALL_ERROR_MESSAGE_4 = By.className("messageDetails");

	protected static final By LOC_LI_BACK_TO_HOME = By.className("back");

	protected static final By LOC_LI_KF_LOGIN_COLLAPSE_BANNER = By.className("kf_open");
	protected static final By LOC_LI_KF_LOGIN_KF_SERVICES = By.id("navKrisflyer");
	protected static final By LOC_LI_KF_LOGIN_LOGOUT = By.className("kf_logout");

	protected static final By LOC_LI_MESSAGE = By.cssSelector(".msg");


	/**
	 * Read Excel data and open SQMobile page url
	 * @return
	 * @throws Exception
	 */
	public void actionOpenWelcomePage() throws Exception {

		readTestData();
		// open the page
		getTest().openPage(getURL());

		// report the URL
		reporter.report("Opened SQ Mobile URL", driver.getCurrentUrl());

		// return the LoginPage object
		// return PageFactory.getPageObject(WelcomePage.class);
	}

	/**
	 * Launches the specified URL
	 *
	 * @return
	 * @throws Exception
	 */
	public void actionLaunchURL(String url) throws Exception {

		// open the page
		getTest().openPage(url);

		// report the URL
		reporter.report("Opened URL", driver.getCurrentUrl());

		// return the LoginPage object
		// return PageFactory.getPageObject(WelcomePage.class);
	}

	/**
	 * method creating the SQMobile url
	 * @return the SQMobile url
	 * @throws IOException
	 */
	public String getURL() throws IOException {

		StringBuilder strUrl = new StringBuilder();
		String siteCode = getValue("SiteCode");

		if(siteCode == null) {
			siteCode = getInputValue(IN_SITECODE);
		}

		String language = getValue("Language");
		if(language == null) {
			language = getInputValue(IN_LANGUAGE);
		}

		if(getValue("SiteName").contains("mobile4MSNRE")||getValue("SiteName").contains("mobile4SV") || getValue("SiteName").contains("SQMobile")){
			if(getValue("SiteName").contains("?")) {
				strUrl.append(getValue("SiteName")).append("&SITE=").append(siteCode).append("&LANGUAGE=").append(language);
			}
			else {
				strUrl.append(getValue("SiteName")).append("?SITE=").append(siteCode).append("&LANGUAGE=").append(language).append("&MT=A");
			}
		}
		else{
			if(getValue("SiteName").contains("?")) {
				reporter.report("1111", "language");
				strUrl.append(getValue("SiteName")).append("&LANGUAGE=").append(language).append("&SITE=").append(siteCode);
			}
			else {
				reporter.report("1111", "lan2222guage");
				strUrl.append(getValue("SiteName")).append("?LANGUAGE=").append(language).append("&SITE=").append(siteCode).append("&MT=A");
			}
		}
		return strUrl.toString();
	}

	//****************************************************************************
	//FUNCTION NAME   :clickHome2
	//INPUT PARAM     :NA
	//OUTPUT PARAM    :NA
	//DESCRIPTION     :To click on Home link
	//NOTE            :None

	public void clickHome2()throws Exception {

		WaitUtils.waitForElementVisible(getTest(), LOC_LI_ALL_HOME_2, 60);
		WebElement home = CheckUtils.getElement(getTest(), LOC_LI_ALL_HOME_2);
		if (home != null && home.isDisplayed()) {
			home.click();
			reporter.report("Home", "Home button is clicked");
		}
	}

	//****************************************************************************
	//FUNCTION NAME   :clickLogout
	//INPUT PARAM     :NA
	//OUTPUT PARAM    :NA
	//DESCRIPTION     :To click on Logout link
	//NOTE            :None

	public void clickLogout()throws Exception {

		WebElement logout = CheckUtils.getElement(getTest(), LOC_LI_ALL_KF_LOGOUT);
		if (logout != null && logout.isDisplayed()) {
			logout.click();
			reporter.report("Logout", "Logout button is clicked");
		}
	}
	//****************************************************************************


	//****************************************************************************
	//FUNCTION NAME   :validateSecureURL
	//INPUT PARAM     :NA
	//OUTPUT PARAM    :NA
	//DESCRIPTION     :To validate URL is http secure
	//NOTE            :None

	public void validateSecureURL()throws Exception {

		if (driver.getCurrentUrl().contains("https")) {
			reporter.reportPassed("URL", "URL changed to https successfully");
		}
		else {
			reporter.reportFailed("URL", "URL has not been changed to https");
		}
	}
	//****************************************************************************


	// ***************************************************************************
	// FUNCTION NAME :clearCookies
	// INPUT PARAM :NA
	// OUTPUT PARAM :NA
	// DESCRIPTION :To clear all the browser cookies
	// NOTE :None

	public void clearCookies() throws Exception {

		driver.manage().deleteAllCookies();
	}

	// ***************************************************************************


	// ****************************************************************************
	// FUNCTION NAME :validateKFLogin
	// INPUT PARAM :NA
	// OUTPUT PARAM :NA
	// DESCRIPTION :To validate the elements after KF login

	public void validateKFLogin() throws Exception {

		// Validate the collapsible banner for Krisflyer member is displayed
		WebElement collapse_banner = CheckUtils.getElement(getTest(), LOC_LI_KF_LOGIN_COLLAPSE_BANNER);
		if (collapse_banner == null || !collapse_banner.isDisplayed()) {
			reporter.reportFailed("KF Login", "Collapse-Banner is not displayed");
		}

		// Validate logout button is displayed
		WebElement logout = CheckUtils.getElement(getTest(), LOC_LI_KF_LOGIN_LOGOUT);
		if (logout == null || !logout.isDisplayed()) {
			reporter.reportFailed("KF Login", "Logout Button is not displayed");
		}
	}


	/**
	 * read from excel for the key parameter
	 * @param strKey
	 * @return the value
	 * @throws IOException
	 */
	public String getValue(String strKey) throws IOException {
		File file = new File(strOutputFilePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String iLine;
		while ((iLine = br.readLine()) != null) {
			//  if (iLine.indexOf(strKey) != -1) {
			if (iLine.substring(0,iLine.indexOf('=')).trim().equalsIgnoreCase((strKey.trim()))) {
				break;
			}
		}
		if(iLine!=null){
			// String[] strOutputValue = iLine.split("\\=");
			String strOutputValue = "" ;
			if(iLine.contains("=")) {
				strOutputValue = iLine.substring(iLine.indexOf('=')+1);
			}
			br.close();
			// return strOutputValue[1].trim();
			return strOutputValue.trim();
		}
		else{
			br.close();
			return "";
		}
	}

	/**
	 * Update value for a key parameter in properties file
	 * @param strKey
	 * @param strValue
	 */
	public void updateValue(String strKey, String strValue) {
		try {
			String filePath = strOutputFilePath;
			File inFile = new File(filePath);

			if (!inFile.isFile()) {
				System.out.println("Parameter is not an existing file");
				return;
			}

			// Construct the new file that will later be renamed to the original filename.
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

			String line = null;

			// Read from the original file and write to the new
			// unless content matches data to be removed.
			while ((line = br.readLine()) != null) {
				String[] strOutputValue = line.split("\\=");
				if (strOutputValue[0].trim().equals(strKey)) {
					pw.println(strKey + " = " + strValue);
					continue;
				}
				pw.println(line);
				pw.flush();
			}

			pw.close();
			br.close();

			// Delete the original file

			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}

			// Rename the new file to the filename the original file had.
			if (!tempFile.renameTo(inFile)) {
				System.out.println("Could not rename file");
			}
		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void addValue(String strKey, String strValue) throws IOException {

		FileWriter fstream = new FileWriter(strOutputFilePath, true);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(strKey + " = " + strValue);
		out.newLine();
		out.close();
		//  System.out.println("Added value for " + strKey + " : " + strValue);
	}

	/**
	 * Read Test data from Excel
	 * @throws Exception
	 * -History : Created by Vigneshwaran Balasubramanian
	 */
	public void readTestData() throws Exception {
		HashMap<String, String> testData = ExcelDataReader.obtainTestData(LOC_XLS_SQ, 0, getTest().getClass().getSimpleName());

		//getting the count of same class column
	    strOutPutFolderPath = getTest().getOutpuFolder();
		strOutputFilePath = strOutPutFolderPath + "\\" + getTest().getClass().getSimpleName()+ "stdOutData.properties";
		File f = new File(strOutputFilePath);
		try{
			if(f.exists()) {
				f.delete();
			}
			f.createNewFile();

			if (!testData.isEmpty()) {
				addAllValues(testData);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * Add all values read Excel file into properties file
	 * @param testData
	 * @throws IOException
	 */
	public static void addAllValues(HashMap<String,String> testData) throws IOException{
		String filePath = strOutputFilePath;

		FileWriter fstream = new FileWriter(filePath, true);
		BufferedWriter out = new BufferedWriter(fstream);
		for(Map.Entry<String, String> entry : testData.entrySet()){
			out.write(entry.getKey() + " = " + entry.getValue());
			out.newLine();
		}
		out.flush();
		out.close();
	}

	public boolean waitForOverlayLoading(int timeOut) {
		// wait for an element condition
		ExpectedCondition<Boolean> condition = new WaitMultipleOverlayCondition();
		Wait<WebDriver> wait = new WebDriverWait(getTest().getDriverInstance(), timeOut);
		Boolean waitResult = wait.until(condition);
		if (waitResult != null) {
			return waitResult.booleanValue();
		}
		return false;
	}


	/**
	 * Validates the presence of the message to be displayed
	 * @param msgToBeValidated
	 * @return
	 */
	public void validateMessage(String msgToBeValidated) {
		StringBuffer msgDisplayed = new StringBuffer();
		List<WebElement> listElts = CheckUtils.getElements(getTest(), LOC_LI_MESSAGE);
		if (listElts != null && listElts.size() > 0) {
			for (WebElement elt : listElts) {
				msgDisplayed = msgDisplayed.append(elt.getText());
			}

			if (msgDisplayed.toString().toLowerCase().contains(msgToBeValidated.toLowerCase())) {
				reporter.reportPassed("Message Displayed : ", msgDisplayed.toString());
			}
			else {
				reporter.reportFailed("Message Displayed : ", msgDisplayed.toString());
			}
		}
		else {
			reporter.reportFailed("Validation Failed", "Message is not Displayed");
		}
	}

	public void clickBackToHome(){
		waitForOverlayLoading(200);
		WaitUtils.waitForElementVisible(getTest(), LOC_LI_BACK_TO_HOME, 60);
		WebElement back = CheckUtils.getElement(getTest(), LOC_LI_ALL_HOME_2);
		if (back != null && back.isDisplayed()) {
			back.click();
			reporter.report("Back", "Back button is clicked");
		}
	}
}