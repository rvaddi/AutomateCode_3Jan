package com.amadeus.selenium.sqmobile.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import com.amadeus.selenium.runner.SeleniumSEPTest;
import com.amadeus.selenium.sqmobile.page.commonpage.SqMobileCommonPage;

public class ExcelDataReader extends SqMobileCommonPage {

	public ExcelDataReader(SeleniumSEPTest test) {
		super(test);
	}

	//****************************************************************************
	//FUNCTION NAME   : obtainRowNo
	//INPUT PARAM     : w - the workbook to read data from
	//                : sheetNo - starting at index 0
	//                : testName - the test name to fetch the data
	//OUTPUT PARAM    : RETURNS the row no of the test name
	//DESCRIPTION     : To retrieve the row number of the corresponding test case from the excel sheet
	public static int obtainRowNo(Workbook w, int sheetNo, String testName) throws Exception {
		int rowNo = -1;
		Sheet sheet = w.getSheet(sheetNo);
		for (int j = 0; j < sheet.getRows(); j++) {
			Cell cell = sheet.getCell(1, j);
			String cellContent = cell.getContents();
			if (cellContent.equals(testName)) {
				rowNo = j;
				break;
			}
		}
		return rowNo;
	}
	//****************************************************************************


	//****************************************************************************
	//FUNCTION NAME   : obtainTestData
	//INPUT PARAM     : fileName - name of the excel file
	//                : sheetNo - starting at index 0
	//                : testName - the test name to fetch the data
	//OUTPUT PARAM    : RETURNS the hash map containing all the test data
	//DESCRIPTION     : Method obtaining test data of the corresponding test name

public static HashMap<String, String> obtainTestData(String fileName, int sheetNo, String testName) throws Exception {
		List<Integer> rowList = new ArrayList<Integer>();
		HashMap<String, String> testData = new HashMap<String, String>();
		File inputWorkbook = new File(fileName);
		Workbook w = Workbook.getWorkbook(inputWorkbook);

		// To obtain the row no of the test
		int rowNo = obtainRowNo(w, sheetNo, testName);

		// Add the row numbers to the list
		rowList.add(0);
		rowList.add(rowNo);

		// Return if row no is < 0
		if (rowNo < 0) {
			System.out.println("Test Name not found in the excel: " + testName);
		}
		else {
			Sheet sheet = w.getSheet(sheetNo);
			for (int i = 0; i < sheet.getColumns(); i++) {
				String header = sheet.getCell(i, rowList.get(0)).getContents();
				String value = sheet.getCell(i, rowList.get(1)).getContents();
				if (header != "") {
					testData.put(header, value);
				}
				else {
					break;
				}
			}
		}
		return testData;
	}

}
