package com.amadeus.selenium.sqmobile.page.calendar;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;
import com.amadeus.selenium.utils.CheckUtils;
import com.amadeus.selenium.utils.ClickUtils;
import com.amadeus.selenium.utils.WaitUtils;

public class CommonCalendarPage extends SqMobileCommonPage{

  public CommonCalendarPage(SeleniumSEPTest test) {
    super(test);
    // TODO Auto-generated constructor stub
  }

  protected static By LOC_FareDeal_Matrix_Table= By.className("scroller");
  protected static By LOC_FareDeal_Matrix_DepDate= By.cssSelector("[role=rowheader]");
  protected static By LOC_FareDeal_Matrix_RetDate= By.cssSelector("[role=columnheader]");
  protected static By LOC_FareDeal_OriDes_TopOfCalendar= By.cssSelector("*.panel>header>h1");

  protected static By LOC_FareDeal_Date_Combination = By.cssSelector("[data-aria-selected=true][role=gridcell]");
  protected static By LOC_FareDeal_Date_Combination_Flight = By.cssSelector("[data-aria-selected=true][role=gridcell]>div");
  protected static By LOC_FareDeal_Matrix_Grid = By.cssSelector(".scroller>[role=grid]>[role=row]");

  String pagename = "Matrix Calendar page";

  /**
   * Validate Date is displayed properly in matrix format based on Flexible Dates
   *
   * @throws IOException
   * @author sankar
   */
  public void ValidateMatrixFormatDate() throws ParseException, IOException {

    WaitUtils.waitForElementPresent(getTest(), LOC_FareDeal_Matrix_Table, 20);
    WebElement matrixtable = CheckUtils.getElement(getTest(), LOC_FareDeal_Matrix_Table);

    List<WebElement> DepDates = CheckUtils.getElements(getTest(), LOC_FareDeal_Matrix_DepDate);
    List<WebElement> RetDates = CheckUtils.getElements(getTest(), LOC_FareDeal_Matrix_RetDate);

    if (matrixtable != null && matrixtable.isDisplayed()) {
      if (!(getValue("Return Date").equalsIgnoreCase(""))) {
        if (!(DepDates.isEmpty() || RetDates.isEmpty())) {

          DateFromMatrixTable(DepDates, "Departure Date");
          DateFromMatrixTable(RetDates, "Return Date");
        }
        else {
          reporter.reportFailed("Calender matrix", "Calender matrix Date is not displayed in proper Format. (" +
              "Departure Date: " + getValue("Departure Date") + " and Return Date: " + getValue("Return Date") + ")");
        }
      }
      else {
        if ((DepDates.isEmpty() && !(RetDates.isEmpty()))) {

          DateFromMatrixTable(RetDates, "Departure Date");
        }
        else {
          reporter.reportFailed("Calender matrix", "Calender matrix Date is not displayed in proper Format. (" +
              "Departure Date: " + getValue("Departure Date") + " and Return Date: " + getValue("Return Date") + ")");
        }
      }
    }
    else {
      reporter.reportFailed("Calender matrix", "Calender matrix Date is not displayed in the page. (" +
          "Departure Date: " + getValue("Departure Date") + " and Return Date: " + getValue("Return Date") + ")");
    }
  }

  /**
   * Get the Date list from Calendar matrix
   *
   * @throws IOException
   * @author sankar
   */
  public void DateFromMatrixTable(List<WebElement> DepRetdate, String dateField) throws ParseException, IOException{

    String getstringDate;
    String SplitDate[];
    List<Date> matrixDateList = new ArrayList<Date>() ;
    Date matrixdate = null;
    Date getdate;

    Calendar matrixcalendar = GregorianCalendar.getInstance();
    Calendar Destinationdate = GregorianCalendar.getInstance();
    Calendar CurrentDate = GregorianCalendar.getInstance();

    getdate = new SimpleDateFormat("dd MMMM yyyy").parse(getValue(dateField));
    Destinationdate.setTime(getdate);

    for(WebElement date : DepRetdate){

      getstringDate = date.getText().trim();
      SplitDate = getstringDate.split("\n");
      getstringDate = SplitDate[0]+" "+SplitDate[1];
      matrixdate = new SimpleDateFormat("EEE dd MMM").parse(getstringDate);

      if(matrixdate.getMonth()>getdate.getMonth() && matrixdate.getMonth()==11 && getdate.getMonth()==0){

        matrixcalendar.setTime(matrixdate);
        matrixcalendar.set((Destinationdate.get(Destinationdate.YEAR)-1), matrixcalendar.get(matrixcalendar.MONTH), matrixcalendar.get(matrixcalendar.DATE), 0, 0, 0);
        matrixdate = matrixcalendar.getTime();
      }else if(matrixdate.getMonth()<getdate.getMonth() && matrixdate.getMonth()==0 && getdate.getMonth()==11){
        matrixcalendar.setTime(matrixdate);
        matrixcalendar.set((CurrentDate.get(CurrentDate.YEAR)+1), matrixcalendar.get(matrixcalendar.MONTH), matrixcalendar.get(matrixcalendar.DATE), 0, 0, 0);
        matrixdate = matrixcalendar.getTime();
      }else{
        matrixcalendar.setTime(matrixdate);
        matrixcalendar.set((Destinationdate.get(Destinationdate.YEAR)), matrixcalendar.get(matrixcalendar.MONTH), matrixcalendar.get(matrixcalendar.DATE), 0, 0, 0);
        matrixdate = matrixcalendar.getTime();
      }

      matrixDateList.add(matrixdate);
    }

    CheckDepRetMatrixDate(matrixDateList,getdate,dateField);
  }

  /**
   * Validate Date list from Calendar matrix with Departure or Return Date
   *
   * @throws IOException
   * @author sankar
   */
  public void CheckDepRetMatrixDate(List<Date> matrixDateList, Date getdate , String dateField) throws IOException, ParseException{

    boolean comparedate = true;
    String FlexibleDates = getValue("Flexible Dates Count");

    int BeforeDate = Integer.parseInt(FlexibleDates);

    for (int i = 0; i < matrixDateList.size(); i++) {
      if (i < BeforeDate) {
        if(!(matrixDateList.get(i).compareTo(getdate)<0)){
          comparedate = false;
          reporter.reportFailed("Matrix "+dateField, "Flexible Date are not displayed properly in Calendar matrix. ("+dateField+": "+getdate+" and matrix Date: "+matrixDateList.get(i)+")");
        }
      }
      else if (i == BeforeDate) {
        if(!(matrixDateList.get(i).compareTo(getdate)==0)){
          comparedate = false;
          reporter.reportFailed("Matrix "+dateField, "Flexible Date are not displayed properly in Calendar matrix. ("+dateField+": "+getdate+" and matrix Date: "+matrixDateList.get(i)+")");
        }
      } else{
        if(!(matrixDateList.get(i).compareTo(getdate)>0)){
          comparedate = false;
          reporter.reportFailed("Matrix "+dateField, "Flexible Date are not displayed properly in Calendar matrix. ("+dateField+": "+getdate+" and matrix Date: "+matrixDateList.get(i)+")");
        }
      }
    }

    if(comparedate){
      reporter.reportPassed("Matrix "+dateField, "Flexible Dates are displayed properly in Calendar matrix. ("+dateField+": "+getdate+" and matrix Date List: "+matrixDateList+")");
    }else{
      reporter.reportFailed("Matrix "+dateField, "Flexible Dates are not displayed properly in Calendar matrix. ("+dateField+": "+getdate+" and matrix Date List: "+matrixDateList+")");
    }
  }

  /**
   * Validate Origin and Destination is displayed in Calendar matrix page
   *
   * @throws IOException
   * @author sankar
   */
  public void VerifyOriginDestinationDisplayed(){

    WebElement City = CheckUtils.getElement(getTest(), LOC_FareDeal_OriDes_TopOfCalendar);
    boolean OriginDestination = false;

    if (City != null && City.isDisplayed()) {
      if(!(City.getText().trim().isEmpty())){
        OriginDestination = true;
        reporter.reportPassed("Matrix Calender Page",
            "Origin and Destination is displayed on top of the Matrix calendar. (City: " + City.getText().trim() + ")");
      }
    }

    if(!OriginDestination){
      reporter.reportFailed("Matrix Calender Page",
          "Origin and Destination is NOT displayed on top of the Matrix calendar");
    }
  }

  /**
   * Click Flight based in Date Combination selected from matrix calendar
   *
   * @throws IOException
   * @author sankar
   */
  public void ClickFlightForDateCombination() {

    ClickUtils.clickButtonOrFail(getTest(), LOC_FareDeal_Date_Combination_Flight, "Flight is NOT selected from the Matrix Calendar","Flight is selected from the Matrix Calendar");
  }

  /**
   * Validate Distinguish mark displayed for the selected Date combination Flight
   *
   * @throws IOException
   * @author sankar
   */
  public void VerifyMarksForDateCombination() throws IOException {

    WebElement DateCombination = CheckUtils.getElement(getTest(), LOC_FareDeal_Date_Combination);
    WebElement Marks = CheckUtils.getElement(getTest(), DateCombination, By.tagName("img"));
    String Distinguishmark;

    if (DateCombination != null && DateCombination.isDisplayed()) {
      if (Marks != null && Marks.isDisplayed()) {
        Distinguishmark = "true";
        addValue("Distinguish Mark", Distinguishmark);
        reporter.reportPassed("Distinguish mark",
            "Offers displayed distinguish mark on calendar page for a selected Date combination. (Departure Date: " +
                getValue("Departure Date") + " and Return Date: " + getValue("Return Date") + ")");
      }
      else {
        reporter.reportWarning("Distinguish mark",
            "Offers NOT displayed distinguish mark on calendar page for a selected Date combination. (Departure Date: " +
                    getValue("Departure Date") + " and Return Date: " + getValue("Return Date") + ")");
      }
    }
    else {
      reporter.reportFailed("Distinguish mark",
          "Flights are NOT displayed for a selected Date combination. (Departure Date: " +
                getValue("Departure Date") + " and Return Date: " + getValue("Return Date") + ")");
    }
  }

  /**
   * Select the Flight from Matrix Calendar
   *
   * @throws IOException
   * @author sankar
   */
  public void SelectFlightfromMatrixCalendar(String DealType) throws IOException {

    Map<String, Object> GetFlightDetail = new HashMap<String, Object>();
    WebElement ClickFlight;
    boolean IsDealFlightAvailable;

    GetFlightDetail = GetFareDealFlight(DealType);

    ClickFlight = (WebElement)GetFlightDetail.get("ClickFlight");
    IsDealFlightAvailable = (Boolean)GetFlightDetail.get("IsDealFlightAvailable");

    if (ClickFlight != null && ClickFlight.isDisplayed()) {
      if (DealType.equalsIgnoreCase("Fare Deal")) {
        if (IsDealFlightAvailable) {
          reporter.reportPassed(pagename, "Fare Deal flight with distinguish mark is available. (Flight: " + ClickFlight.getText().trim() + ")");
          ClickUtils.click(getTest(), ClickFlight);
          reporter.reportPassed(pagename, "Fare Deal Flight is selected from Matrix Calendar page.");
        }
        else {
       // reporter.reportFailed(pagename,"Fare Deal flight with distinguish mark is NOT available. (Flight: " + ClickFlight.getText().trim() + ")");
          reporter.reportWarning(pagename,"Fare Deal flight with distinguish mark is NOT available. (Flight: " + ClickFlight.getText().trim() + ")");
          ClickUtils.click(getTest(), ClickFlight);
          reporter.reportPassed(pagename, "Regular Flight is selected from Matrix Calendar page.");
        }
      }
      else {
        if (!IsDealFlightAvailable) {
          reporter.reportPassed(pagename, "Regular flight is available. (Flight: " + ClickFlight.getText().trim() + ")");
          ClickUtils.click(getTest(), ClickFlight);
          reporter.reportPassed(pagename, "Regular Flight is selected from Matrix Calendar page.");
        }
        else {
       // reporter.reportFailed(pagename, "Regular flight is NOT available. (Flight: " + ClickFlight.getText().trim() + ")");
          reporter.reportWarning(pagename, "Regular flight is NOT available. (Flight: " + ClickFlight.getText().trim() + ")");
          ClickUtils.click(getTest(), ClickFlight);
          reporter.reportPassed(pagename, "Fare Deal Flight is selected from Matrix Calendar page.");
        }
      }
    }
    else {
    //  reporter.reportFailed("Select Flight From Calendar", "No Flights are available to select from Matrix Calendar page.");
        reporter.fail("No Flights are available to select from Matrix Calendar page.");
    }
  }

  /**
   * Get the Flight to be selected based on DealType
   *
   * @throws IOException
   * @author sankar
   */
  public Map<String, Object> GetFareDealFlight(String DealType) throws IOException {

    Map<String, Object> SetFlightDetail = new HashMap<String, Object>();
    WebElement ClickFlight = null;
    boolean IsDealFlightAvailable = false;
    boolean DealFlightSelected = false;

    if (!getValue("Distinguish Mark").equalsIgnoreCase("true")) {
      VerifyMarksForDateCombination();
    }
    if (DealType.equalsIgnoreCase("FareDeal")) {
      if (getValue("Distinguish Mark").equalsIgnoreCase("true")) {
        if (!(CheckUtils.getElement(getTest(), LOC_FareDeal_Date_Combination_Flight) == null)) {
          DealFlightSelected = true;
          IsDealFlightAvailable = true;
          ClickFlight = CheckUtils.getElement(getTest(), LOC_FareDeal_Date_Combination_Flight);
        }
      }
    }
    else {
      if (getValue("Distinguish Mark").equalsIgnoreCase("")) {
        if(!(CheckUtils.getElement(getTest(), LOC_FareDeal_Date_Combination_Flight)==null)){
        DealFlightSelected = true;
        ClickFlight = CheckUtils.getElement(getTest(), LOC_FareDeal_Date_Combination_Flight);
        }
      }
    }

    if (!DealFlightSelected) {

      List<WebElement> Matrixrows = CheckUtils.getElements(getTest(), LOC_FareDeal_Matrix_Grid);

      DealFlight: for (WebElement Row : Matrixrows) {
        String column = Row.getAttribute("class");
        if (!(column.equalsIgnoreCase("columnhead"))) {
          // List<WebElement> Matrixcells = CheckUtils.getElements(getTest(), Row, By.tagName("li"));
          List<WebElement> Matrixcells = CheckUtils.getElements(getTest(), Row, By.cssSelector("li>div"));
          for (WebElement Cell : Matrixcells) {
            if (ClickFlight == null) {
              ClickFlight = Cell;
            }
            WebElement Marks = CheckUtils.getElement(getTest(), Cell, By.tagName("img"));

            if (DealType.equalsIgnoreCase("FareDeal")) {
              if (!(Marks == null)) {
                ClickFlight = Cell;
                IsDealFlightAvailable = true;
                break DealFlight;
              }
            }
            else {
              if ((Marks == null)) {
                ClickFlight = Cell;
                IsDealFlightAvailable = false;
                break DealFlight;
              }
            }
          }
        }
      }
    }

    SetFlightDetail.put("ClickFlight", ClickFlight);
    SetFlightDetail.put("IsDealFlightAvailable", IsDealFlightAvailable);

    return SetFlightDetail;
  }

}
