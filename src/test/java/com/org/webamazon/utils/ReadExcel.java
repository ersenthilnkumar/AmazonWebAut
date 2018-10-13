package com.org.webamazon.utils;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

public class ReadExcel
{
	static Logger log = Logger.getLogger("devpinoyLogger");
	public static Excel xls;

	/********************************************************************************************************************
	 * Function Name : getBasicData
	 * Parameters    : testName/key to iterate the test data, sheet name in excel sheet
	 * Return        : Object[][] - no of rows of data and key value combo of test data - in column wise
	 * Purpose       : To get data from the excel sheet and feed to called modules script thro data provider
	 * @throws Exception 
	 * 
	 *******************************************************************************************************************/
	public static Object[][] getBasicData(String sheetName, String testName) throws Exception
	{
		xls = new Excel(System.getProperty("user.dir")+"\\resources\\ExcelData.xlsx");
	
		System.out.println("********************");
		int testStartRowNum=1;
		while(!xls.getCellData(sheetName, 0, testStartRowNum).equals(testName))
		{
			testStartRowNum++;
			if(testStartRowNum > xls.getRowCount(sheetName))
			{
				System.out.println("Test name not found");
				return null;
			}

		}
		log.info("Test starts from "+ testStartRowNum);
		// row on which cols are present
		int testHeaderStartCol = 0; 
		System.out.println(testHeaderStartCol);
		// row on which data starts
		int testDataStartCol = testHeaderStartCol+1;
		System.out.println(testDataStartCol);

		// find total columns of test data in the test
		int totalDataCols=0; //assuming
		while(!xls.getCellData(sheetName, (testDataStartCol+totalDataCols), testDataStartCol+1).equals(""))
		{
//			System.out.println(xls.getCellData(sheetName, (testDataStartCol+totalDataCols), testDataStartCol+1));
			totalDataCols++;
		}
		log.info("Total Data Columns ->" +totalDataCols);

		// find total Rows in the test data
		int totalHeaderRows=0;
		while(!xls.getCellData(sheetName, 0, testDataStartCol+1+totalHeaderRows).equals(""))
		{
//			System.out.println(xls.getCellData(sheetName, 0, testDataStartCol+1+totalHeaderRows));
			totalHeaderRows++;
		}
		log.info("Total Rows -> "+totalHeaderRows);


		// extract data for every row and put data in hashtable
		Object data[][] = new Object[1][totalDataCols];
		int index=0;
		Hashtable<String,String> table = null;
		//Read the extracted data from hash table and return data of type Hash table
		for(int cNum=1;cNum<(totalDataCols+1);cNum++)
		{
			table = new Hashtable<String,String>();
			for(int rNum=2;rNum<totalHeaderRows+2;rNum++)
			{
				String key = xls.getCellData(sheetName, testHeaderStartCol, rNum);
	//			System.out.println(key);
				String value = xls.getCellData(sheetName, cNum, rNum);
				
	//			System.out.println(value);
				table.put(key, value);
				//System.out.print(xls.getCellData("Test Data", cNum, rNum)+" -- ");
			}
			data[index][0]=table;
			index++;
		}
		return data;
	}

	/********************************************************************************************************************
	 * Function Name : getData
	 * Parameters    : testName/key to iterate the test data, sheet name in excel sheet
	 * Return        : Object[][] - no of rows of data and key value combo of test data - in row  wise
	 * Purpose       : To get data from the excel sheet and feed to called modules script thro data provider
	 * @throws Exception 
	 * 
	 *******************************************************************************************************************/

	public static Object[][] getData(String sheetName, String testName)
	{
		System.out.println("********************");
		// find row Num from which test starts
		// find total rows of test data in the test
		// find total columns in the test data
		// extract data for every row and put data in hashtables
		// put the hastables in object array


		// find row Num from which test starts
		int testStartRowNum=1;

		while(!xls.getCellData(sheetName, 0, testStartRowNum).equals(testName)){
			testStartRowNum++;
			if(testStartRowNum > xls.getRowCount(sheetName)){
				System.out.println("Test name not found");
				return null;
			}

		}
		//System.out.println("Test starts from "+ testStartRowNum);
		log.info("Test starts from "+ testStartRowNum);

		int testColStartRow = testStartRowNum+1; // row on which cols are present
		int testDataStartRow = testStartRowNum+2;// row on which data starts

		// find total rows of test data in the test
		int totalDataRows=0; //assuming
		while(!xls.getCellData(sheetName, 0, (testDataStartRow+totalDataRows)).equals("")){
			totalDataRows++;
		}
		//System.out.println("Total rows ->" +totalDataRows);
		log.info("Total rows ->" +totalDataRows);

		// find total columns in the test data
		int totalCols=0;
		while(!xls.getCellData(sheetName, totalCols, testColStartRow).equals("")){
			totalCols++;
		}
		//System.out.println("Total cols -> "+totalCols );
		log.info("Total cols -> "+totalCols);

		// extract data for every row and put data in hashtables

		Object data[][] = new Object[totalDataRows][1];
		int index=0;
		Hashtable<String,String> table = null;

		for(int rNum=testDataStartRow;rNum<(testDataStartRow+totalDataRows);rNum++){
			table = new Hashtable<String,String>();
			for(int cNum=0;cNum<totalCols;cNum++){
				String key = xls.getCellData(sheetName, cNum, testColStartRow);
				String value = xls.getCellData(sheetName, cNum, rNum);
				table.put(key, value);
				//System.out.print(xls.getCellData("Test Data", cNum, rNum)+" -- ");
			}
			//System.out.println();
			data[index][0]=table;
			index++;
		}
		return data;
	}

}
