package com.amadeus.selenium.sqmobile.xml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.amadeus.selenium.runner.SeleniumSEPTest;

public class PublishXMLReport {

  private SeleniumSEPTest test = null;

  public PublishXMLReport(SeleniumSEPTest test) {
    this.test = test;
  }

  /**
   * Method converts log file to xml file
   *
   */
  public void ConvertLogToXml() throws IOException {

    StringBuilder XMLData = new StringBuilder();

    XMLData.append(SeleniumReport());
    WriteXMLFile(XMLData);

  }

  /**
   * Method writing XML data into XML file
   * @param XMLData data read from log file
   *
   */
  public void WriteXMLFile(StringBuilder XMLData) throws IOException {

    FileWriter FWriter = new FileWriter(CreateXmlFile(test), true);
    BufferedWriter BWriter = new BufferedWriter(FWriter);

    BWriter.write(XMLData.toString());

    BWriter.flush();
    BWriter.close();
    FWriter.close();
  }

  /**
   * Method creating an XML file for the test
   * @param test the SeleniumSEPTest instance
   * @return the XML file instance
   */
  private File CreateXmlFile(SeleniumSEPTest test) {
    File XmlFile = null;

    try {
      XmlFile = new File(test.getParameters().getOutputFolder(), test.getClass().getSimpleName().concat(".xml"));

      if (XmlFile.exists()) {
        XmlFile.delete();
      }
      XmlFile.createNewFile();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    return XmlFile;
  }

  /**
   * Method reads log file and create complete xml data
   *
   */
  public StringBuilder SeleniumReport() {

    StringBuilder selenium_report = new StringBuilder();

    selenium_report.append("<?xml version='1.0' encoding='UTF-8'?>");
    selenium_report.append("<seleniumReport>");
    selenium_report.append(TestInfo());
    selenium_report.append(TestParameters());
    selenium_report.append(ExecutionDetails());
    selenium_report.append("</seleniumReport>");

    return selenium_report;
  }

  /**
   * Method reads log file and create [test-info] data
   *
   */
  public StringBuilder TestInfo() {

    String logdata;
    StringBuilder test_parameter = new StringBuilder();

    test_parameter.append("<test-info>");

    test_parameter.append("<testName>");
    logdata = ReadLogFile("Test Class:");
    if(!(logdata==null)){
    logdata = logdata.substring(logdata.lastIndexOf(".") + 1).trim();
    test_parameter.append(logdata);
    }
    test_parameter.append("</testName>");

    test_parameter.append("<testStatus>");
    logdata = ReadLogFile("STATUS$$None$$");
    if(!(logdata==null)){
    logdata = logdata.substring(logdata.lastIndexOf("$$") + 2);
    logdata = logdata.substring(logdata.lastIndexOf(" ") + 1).trim();
    test_parameter.append(logdata);
    }
    test_parameter.append("</testStatus>");

    test_parameter.append("<runnerVersion>");
    logdata = ReadLogFile("Runner version:");
    if(!(logdata==null)){
    logdata = logdata.substring(logdata.lastIndexOf("version:") + 8).trim();
    test_parameter.append(logdata);
    }
    test_parameter.append("</runnerVersion>");

    test_parameter.append("<browser>");
    logdata = ReadLogFile("Browser:");
    if(!(logdata==null)){
    logdata = logdata.substring(logdata.lastIndexOf("Browser:") + 8).trim();
    test_parameter.append(logdata);
    }
    test_parameter.append("</browser>");

    test_parameter.append("<startTime>");
    logdata = ReadLogFile("Start logging at");
    if(!(logdata==null)){
    logdata = logdata.substring(logdata.lastIndexOf("at") + 2).trim();
    test_parameter.append(logdata);
    }
    test_parameter.append("</startTime>");

    test_parameter.append("<properties-file>");
    logdata = ReadLogFile("Properties file:");
    if(!(logdata==null)){
    logdata = logdata.substring(logdata.lastIndexOf("file:") + 5).trim();
    test_parameter.append(logdata);
    }
    test_parameter.append("</properties-file>");

    test_parameter.append("</test-info>");

    return test_parameter;
  }

  /**
   * Method reads log file and create [test-parameters] data
   *
   */
  public StringBuilder TestParameters() {

    String logdata;
    StringBuilder test_parameter = new StringBuilder();

    test_parameter.append("<test-parameters>");

    test_parameter.append("<test-folder>");
    logdata = ReadLogFile("Test Folder");
    if(!(logdata==null)){
    logdata = logdata.substring(logdata.lastIndexOf("Jar:") + 4).trim();
    test_parameter.append(logdata);
    }
    test_parameter.append("</test-folder>");

    test_parameter.append("<test-class>");
    logdata = ReadLogFile("Test Class:");
    if(!(logdata==null)){
    logdata = logdata.substring(logdata.lastIndexOf("Class:") + 6).trim();
    test_parameter.append(logdata);
    }
    test_parameter.append("</test-class>");

    test_parameter.append("<targetUrl>");
    logdata = ReadLogFile("Target URL:");
    if(!(logdata==null)){
    logdata = logdata.substring(logdata.lastIndexOf("URL:") + 4).trim();
    test_parameter.append(logdata);
    }
    test_parameter.append("</targetUrl>");

    test_parameter.append("<browser-code>");
    logdata = ReadLogFile("Browser code:");
    if(!(logdata==null)){
      logdata = logdata.substring(logdata.lastIndexOf("code:") + 5).trim();
    test_parameter.append(logdata);
    }
    test_parameter.append("</browser-code>");

    test_parameter.append("<webDriver>");
    logdata = ReadLogFile("Web Driver:");
    if(!(logdata==null)){
    logdata = logdata.substring(logdata.lastIndexOf("Driver:") + 7).trim();
    test_parameter.append(logdata);
    }
    test_parameter.append("</webDriver>");

    test_parameter.append("<skip-localsetup>");
    logdata = ReadLogFile("Skip local setup:");
    if(!(logdata==null)){
    logdata = logdata.substring(logdata.lastIndexOf("setup:") + 6).trim();
    test_parameter.append(logdata);
    }
    test_parameter.append("</skip-localsetup>");

    test_parameter.append("<output-folder>");
    logdata = ReadLogFile("Output folder:");
    if(!(logdata==null)){
    logdata = logdata.substring(logdata.lastIndexOf("folder:") + 7).trim();
    test_parameter.append(logdata);
    }
    test_parameter.append("</output-folder>");

    test_parameter.append("<remoteServerUrl>");
    logdata = ReadLogFile("Remote Server Url:");
    if(!(logdata==null)){
    logdata = logdata.substring(logdata.lastIndexOf("Url:") + 4).trim();
    test_parameter.append(logdata);
    }
    test_parameter.append("</remoteServerUrl>");

    test_parameter.append("<gds>");
    logdata = ReadLogFile("GDS:");
    if(!(logdata==null)){
    logdata = logdata.substring(logdata.lastIndexOf("GDS:") + 4).trim();
    test_parameter.append(logdata);
    }
    test_parameter.append("</gds>");

    test_parameter.append("<timeout>");
    logdata = ReadLogFile("TimeOut:");
    if(!(logdata==null)){
    logdata = logdata.substring(logdata.lastIndexOf("TimeOut:") + 8).trim();
    test_parameter.append(logdata);
    }
    test_parameter.append("</timeout>");

    test_parameter.append("<screenCaptureMode>");
    logdata = ReadLogFile("Screen capture mode:");
    if(!(logdata==null)){
    logdata = logdata.substring(logdata.lastIndexOf("mode:") + 5).trim();
    test_parameter.append(logdata);
    }
    test_parameter.append("</screenCaptureMode>");

    test_parameter.append("<automaticScreenCaptureMode>");
    logdata = ReadLogFile("Automatic Screen capture mode:");
    if(!(logdata==null)){
    logdata = logdata.substring(logdata.lastIndexOf("mode:") + 5).trim();
    test_parameter.append(logdata);
    }
    test_parameter.append("</automaticScreenCaptureMode>");

    test_parameter.append("</test-parameters>");

    return test_parameter;
  }

  /**
   * Method reads log file and create [execution-details] data
   *
   */
  public StringBuilder ExecutionDetails() {

    String logdata;
    StringBuilder execution_detail = new StringBuilder();

    execution_detail.append("<execution-details>");

    execution_detail.append("<jsessionList>");
    execution_detail.append("<jsessionId>");
    logdata = ReadLogFile("[JSESSIONID]");
    if(!(logdata==null)){
    logdata = logdata.substring(logdata.lastIndexOf("[JSESSIONID]") + 12).trim();
    execution_detail.append(logdata);
    }
    execution_detail.append("</jsessionId>");
    execution_detail.append("</jsessionList>");

    execution_detail.append("<reporter>");

    execution_detail.append("<test-steps>");
    execution_detail.append(ReadTestSteps("[REPORT]"));
    execution_detail.append("</test-steps>");

    execution_detail.append("<error>");
    execution_detail.append(ReadTestSteps("[ERROR]"));
    execution_detail.append("</error>");

    execution_detail.append("</reporter>");
    execution_detail.append("</execution-details>");

    return execution_detail;
  }

  /**
   * Method reads log file and create [test-steps] data
   * @param findtext the text to be search in log file
   * @return the xml data to write
   *
   */
  public StringBuilder ReadTestSteps(String findtext) {

    String logdata;
    String teststepdata;
    StringBuilder test_step = new StringBuilder();

    String testContent = test.getLogger().toString();

    if (!testContent.isEmpty()) {
      String[] lines = testContent.split("\\n");

      for (int i = 0; i < lines.length; i++) {
        logdata = lines[i];

        if (logdata.contains(findtext)) {
          if (findtext.equalsIgnoreCase("[REPORT]")) {
            if ((logdata.contains("$$Passed$$") || logdata.contains("$$Failed$$") || logdata.contains("$$None$$")) &&
                !(logdata.contains("ScreenShot") || logdata.contains("Input element By") || logdata.contains("[JSESSIONID]"))) {

              test_step.append("<step>");

              test_step.append("<title>");
              teststepdata = logdata.substring((logdata.indexOf("[REPORT]") + 8), logdata.indexOf("$$")).trim();
              test_step.append(teststepdata.replaceAll("&", "&#38;").replaceAll("<", "&#60;"));
              test_step.append("</title>");

              test_step.append("<status>");
              teststepdata = logdata.substring((logdata.indexOf("$$") + 2), logdata.lastIndexOf("$$")).trim();
              test_step.append(teststepdata.replaceAll("&", "&#38;").replaceAll("<", "&#60;"));
              test_step.append("</status>");

              test_step.append("<description>");
              teststepdata = logdata.substring((logdata.lastIndexOf("$$") + 2)).trim();
              test_step.append(teststepdata.replaceAll("&", "&#38;").replaceAll("<", "&#60;"));
              test_step.append("</description>");

              test_step.append("</step>");
            }
          }
          else if (findtext.equalsIgnoreCase("[ERROR]")) {

            test_step.append("<error-msg>");
            logdata = logdata.substring(logdata.indexOf("[ERROR]") + 7).trim();
            test_step.append(logdata.replaceAll("&", "&#38;").replaceAll("<", "&#60;"));
            test_step.append("</error-msg>");
          }
        }
      }
    }

    return test_step;
  }

  /**
   * Method reads log file and create xml data
   * @param findtext the text to be search in log file
   *@return the xml data to write
   */
  public String ReadLogFile(String findtext) {

    String logdata;
    String returnline = null;

    String testContent = test.getLogger().toString();

    if (!testContent.isEmpty()) {
      String[] lines = testContent.split("\\n");

      for (int i = 0; i < lines.length; i++) {
        logdata = lines[i];

        if (logdata.contains(findtext)) {

          returnline = logdata;
          returnline = returnline.replaceAll("&", "&#38;").replaceAll("<", "&#60;");
          break;
        }
      }
    }

    return returnline;
  }
}
