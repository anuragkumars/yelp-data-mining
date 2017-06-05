/**
*
* $Author: $ 
* $Change: $ 
* $Date: $ 
* $DateTime: $ 
* $File: $ 
* $Header: $ 
* $Id: $ 
* $Revision: $ 
*
*/

package com.brightplan.automation.projects.yelp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;

import com.brightplan.automation.projects.common.CommonJUnitBase;
import com.brightplan.automation.utils.common.DisplayUtils;
import com.brightplan.automation.utils.selenium.SeleniumUtilBase.WebElementType;
import com.brightplan.automation.utils.selenium.SeleniumUtilFactory;
import com.brightplan.automation.utils.selenium.SeleniumUtilHelper;
import com.brightplan.automation.utils.selenium.SeleniumUtilYelpMerchantDetailsTest;

/**
 * java -cp <classpath> -D<>=<>
 *         org.junit.runner.JUnitCore <junit class name>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class YELP001_DisplaySearchDetails_JUnit 
extends CommonJUnitBase
{
	@BeforeClass
	public static void setUpBeforeClassExt1() throws Exception {
		
		System.setProperty("selenium.endpoint","http://www.yelp.com");

		if (System.getProperty("selenium.webdriver.chrome") == null) {
			System.setProperty("selenium.webdriver.chrome",
					"C:/dev/docs/BrightPlan/BrightPlan_B/driver/chromedriver.exe");
		}

		SeleniumUtilFactory seleniumFact = SeleniumUtilFactory.getInstance();
		_utilBase = seleniumFact.create("com.brightplan.automation.utils.selenium.SeleniumUtil", "YelpMerchantDetailsTest");
		_utilHelper = new SeleniumUtilHelper ();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClassExt() throws Exception {
		System.out.println("**** tearDownAfterClassExt () Ext ****");

		_testsRunEndTime = System.currentTimeMillis();
		_testsTotalRunTime = _testsRunEndTime - _testsRunStartTime;
		
		_testsEndDateTime = new Date().toString();
		
		collectTestCaseFooterData ();
		
		boolean bWriteReport = true;
		if (System.getProperty("SkipReportOut") != null && System.getProperty("SkipReportOut").equalsIgnoreCase("yes")) {
			bWriteReport = false;
		}
		
		if (bWriteReport) {
			System.out.println("**** tearDownAfterClassExt () Ext, writing report html ****");
			_utilReport.writeReport();
		} else {
			System.out.println("**** tearDownAfterClassExt () Ext, NOT writing report html as SkipReportOut env var is set to yes ****");
		}
		
	}
	
	@BeforeClass
	public static void setUpBeforeClassExt() throws Exception {
		System.out.println("**** setUpBeforeClassExt() ****");
		_utilReport
				.set_reportTitle("YELP001 : Displaying Merchant Details after search");
		_utilReport.set_reportConfig("default");

		String strDetailKeysDelimited = ""
				+ "Test_CaseNum,Test_CustomerName,Test_CaseDesc,"
				+ "Result_InitialCount,Result_FiltersUsed,Result_FinalCount,Result_MerchantName,"
				+ "Result_MerchantAddress,Result_MerchantPhone,Result_MerchantWebSite,"
				+ "Result_Review1,Result_Review2,Result_Review3,"
				+ "Test_Result";

		String strDetailKeysDisplayDelimited = ""
				+ "Test Case Num,Customer Name, Test Case Description,"
				+ "Initial Search Count, Filters Used, Final Search Count, Merchant Name,"
				+ "Merchant Address,Merchant Phone,Merchant Web Site,"
				+ "Review #1,Review #2,Review #3,"
				+ "Test Result";

		_utilReport.set_detailKeysOrderListAndValues(strDetailKeysDelimited,
				strDetailKeysDisplayDelimited);
		_utilReport
				.set_resultKeysToFormatWithColorDelim("Test_Result");

		// Set up for reporting
		_utilReport.add_detailTestCaseValueToUrlMap("Test_PlaceHolder", "");

		String strFooterKeysDelimited = "Test_ReportStartTime,Test_ReportEndTime,Test_ReportRunTimeInMillis,Test_TestsTotal,Test_TestsPass,Test_TestsFail";
		String strFooterKeysDisplayDelimited = "Report Start Time,Report Run End Time,Total Run Time (in millis),Total Test Cases,Total Test Pass, Total Test Fail";

		_utilReport.set_footerKeysOrderListAndValues(strFooterKeysDelimited,
				strFooterKeysDisplayDelimited);

		String strAllTestCasesDelimited = ""
				+ "TC0001,TC0002,TC0003,TC0004,TC0005,TC0006,TC0007,TC0008,TC0009,TC0010";

		_utilReport.add_allDetailTestCaseOrderList(strAllTestCasesDelimited);

		System.out
				.println("**** setUpBeforeClassExt() - setUpEnvBeforeClass ****");
	}
	
	@Before
	public void setUpExt() throws Exception {
	}

	@After
	public void tearDownExt() throws Exception {
		_utilReport.add_detailTestCaseValueMap(_thisTestCaseNum,
				_hmTestDetailReportMap);
		System.out.println("*** tearDownExt ***\n");
		DisplayUtils
				.HashMapDumpStrVals(_hmTestDetailReportMap, "tearDownExt()");
	}

	public static  void collectTestCaseDetailData () {
		_hmTestDetailReportMap.put("Test_CaseNum", _thisTestCaseNum);
		_hmTestDetailReportMap.put("Test_CaseDataNum", _strDataTestCase);
		_hmTestDetailReportMap.put("Test_CaseDesc", _thisTestCaseDesc);
	}
	
	public static void collectTestCaseFooterData () {
		_hmTestDetailReportMap = new HashMap<String,String> ();
		_hmTestDetailReportMap.clear();
		_hmTestDetailReportMap.put("Test_ReportStartDateTime", ""+_testsStartDateTime);
		_hmTestDetailReportMap.put("Test_ReportEndDateTime", ""+_testsEndDateTime);
		_hmTestDetailReportMap.put("Test_ReportStartTime", ""+_testsRunStartTime);
		_hmTestDetailReportMap.put("Test_ReportEndTime", ""+_testsRunEndTime);
		_hmTestDetailReportMap.put("Test_ReportRunTimeInMillis", ""+_testsTotalRunTime);
		_hmTestDetailReportMap.put("Test_TestsTotal", ""+_totalTestCount);
		_hmTestDetailReportMap.put("Test_TestsPass", ""+_totalPassTestCount);
		_hmTestDetailReportMap.put("Test_TestsFail", ""+_totalFailTestCount);
		
		_utilReport.add_detailTestCaseValueMap("footer", _hmTestDetailReportMap);
	}
	
	@Ignore
	@Test
	public void test0001_restaurant_search_() throws Exception
	{
		_thisTestCaseNum = "TC0001";
		_thisTestCase = "YELP0001_" + _thisTestCaseNum
				+ " Restaurant Search with filter - ";
		_thisTestCaseDesc = "Restaurant search with neighborhood (1,3) and distance filter (1,3). Deprecated";
		_strDataTestCase = "";
		
		_utilBase.init();
		_utilBase.init_driver();

		_utilHelper.navigateToUrl (_utilBase, System.getProperty("selenium.endpoint"), "Go to yelp.com", _waitTimeDefault);
	
		_utilHelper.setFocusOnElement(_utilBase, "find_desc",
				WebElementType.ID, "Setting focus on Find",
				_waitTimeDefault);
		_utilHelper.findAndClickOnElement(_utilBase, "//*[@id=\"header_find_form\"]/div/div[1]/div/div/ul/li[1]",
				WebElementType.XPATH, "Select 'Restaurants' in the dropdown box in Find",
				_waitTimeDefault);
		_utilHelper.findAndClickOnElementWithValue(_utilBase, "//*[@id=\"find_desc\"]",
				WebElementType.XPATH, "Select 'Restaurants' in the dropdown box in Find",
				_waitTimeDefault, "Restaurant Pizza");
		
		_utilHelper.findAndClickOnElement(_utilBase, "//*[@id=\"header-search-submit\"]/span/span[1]",
				WebElementType.XPATH, "Click Find button after setting search criteria",
				_waitTimeDefault);
		
		String totalResult = _utilHelper.getElementText(_utilBase, "//*[@id=\"wrap\"]/div[2]/div[1]/div/div[2]/div/div[1]/div/span",
				WebElementType.XPATH, "Get total initial result count",
				_waitTimeDefault);
		
		logIt("Total Initial Result : " + totalResult);
		
		_utilHelper.findAndClickOnElement(_utilBase, "//*[@id=\"wrap\"]/div[2]/div[1]/div/div[2]/div/div[2]/div[1]/ul/li[7]/label/span",
				WebElementType.XPATH, "Expand All Filters to select parameters",
				_waitTimeDefault);
		
		// Neighborhood .. index 1
		_utilHelper.findAndClickOnElement(_utilBase, "//*[@id=\"wrap\"]/div[2]/div[1]/div/div[2]/div/div[2]/div[2]/div/div[2]/ul[1]/li[1]/label/input",
				WebElementType.XPATH, "Selecting neighborhood element at index 1",
				_waitTimeDefault);

		// Neighborhood .. index 3
		_utilHelper.findAndClickOnElement(_utilBase, "//*[@id=\"wrap\"]/div[2]/div[1]/div/div[2]/div/div[2]/div[2]/div/div[2]/ul[1]/li[3]/label/input",
				WebElementType.XPATH, "Selecting neighborhood element at index 3",
				_waitTimeDefault);

		_utilHelper.findAndClickOnElement(_utilBase, "//*[@id=\"wrap\"]/div[2]/div[1]/div/div[2]/div/div[2]/div[2]/div/div[3]/ul/li[1]/label/span",
				WebElementType.XPATH, "Selecting distance element at index 1",
				_waitTimeDefault);

		// Distance .. index 3
		_utilHelper.findAndClickOnElement(_utilBase, "//*[@id=\"wrap\"]/div[2]/div[1]/div/div[2]/div/div[2]/div[2]/div/div[3]/ul/li[3]/label/span",
				WebElementType.XPATH, "Selecting distance element at index 3",
				_waitTimeDefault);

		String totalCount = _utilHelper.getElementText(_utilBase, "//*[@id=\"wrap\"]/div[2]/div[1]/div/div[2]/div/div[1]/div/span",
				WebElementType.XPATH, "Get total filtered result count",
				_waitTimeDefault);

		
		logIt("totalCount : (" + totalCount + ")");
		totalCount = totalCount.substring(totalCount.indexOf("-")+1);
		totalCount = totalCount.substring(0,totalCount.indexOf(" "));
		
//		String totalCount = elemInput11Text.substring(elemInput11Text.indexOf(" of ")+4);
		int totalResults = 0;
		try {
			totalResults = Integer.parseInt(totalCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> starRatingList = new ArrayList<String> ();
		Map<String, Object> starRatingMap = new LinkedHashMap <String, Object> ();
		
		String displayLinkStarRatingPrefix="//*[@id=\"super-container\"]/div/div[2]/div[1]/div/div[4]/ul[2]/li[";
		String displayLinkStarRatingSuffix="]/div/div[1]/div[1]/div/div[2]/div[1]/div";

		String displayLinkPrefix="//*[@id=\"super-container\"]/div/div[2]/div[1]/div/div[4]/ul[2]/li[";
		String displayLinkSuffix="]/div/div[1]/div[1]/div/div[2]/h3/";
		String displayLinkSuffixSpan="span";
		String displayLinkSuffixLink="span/a";
		
		Map<String, Object> valuesMap = new LinkedHashMap <String, Object> ();
		Object firstElem = null;
		
		_utilHelper.getStarRating ("Getting star rating of merchants",
				_utilBase, 
				displayLinkStarRatingPrefix,
				displayLinkStarRatingSuffix,
				displayLinkPrefix,
				displayLinkSuffix,
				displayLinkSuffixSpan,
				displayLinkSuffixLink,
				WebElementType.XPATH, 
				_waitTimeDefault, 
				totalResults,
				starRatingList,
				starRatingMap,
				valuesMap);
		
		logIt(starRatingList.toString());
		logIt(starRatingMap.toString());
		// Get all star rating
		
		// 10. Click and expand details
		logIt("\n\n");
		logIt(">> Step 10. Click and expand the first result from the search results\n\nClicking on firstElem : " + firstElem);
		((WebElement)valuesMap.get("firstElement")).click();

		List<String> keyElementNameList = new ArrayList<String> ();
		Map<String, Object> keyConfigMap = new LinkedHashMap<String, Object> ();
		Map<String, Object> keyElementValueInOutMap = new LinkedHashMap<String, Object> ();
		boolean bconstructLookup = false;
		long lwaitBetweenExtract = 1000;
		
		keyElementNameList.add("merchant.Address");
		keyElementNameList.add("merchant.Phone");
		keyElementNameList.add("merchant.Website");
		keyElementNameList.add("merchant.Review.1");
		keyElementNameList.add("merchant.Review.2");
		keyElementNameList.add("merchant.Review.3");
		
		keyConfigMap.put("merchant.Address.keyType", WebElementType.XPATH);
		keyConfigMap.put("merchant.Phone.keyType", WebElementType.XPATH);
		keyConfigMap.put("merchant.Website.keyType", WebElementType.XPATH);
		keyConfigMap.put("merchant.Review.1.keyType", WebElementType.XPATH);
		keyConfigMap.put("merchant.Review.2.keyType", WebElementType.XPATH);
		keyConfigMap.put("merchant.Review.3.keyType", WebElementType.XPATH);

		keyConfigMap.put("merchant.Address.keyCompleteRef", "//*[@id=\"wrap\"]/div[2]/div/div[1]/div/div[4]/div[1]/div/div[2]/ul/li[1]/div/strong/address");
		keyConfigMap.put("merchant.Phone.keyCompleteRef", "//*[@id=\"wrap\"]/div[2]/div/div[1]/div/div[4]/div[1]/div/div[2]/ul/li[3]/span[3]");
		keyConfigMap.put("merchant.Website.keyCompleteRef", "//*[@id=\"wrap\"]/div[2]/div/div[1]/div/div[4]/div[1]/div/div[2]/ul/li[4]/span[2]/a");

		keyConfigMap.put("merchant.Review.1.keyCompleteRef", "//*[@id=\"super-container\"]/div/div/div[1]/div[1]/div[1]/ul/li[1]/div[2]/p");
		keyConfigMap.put("merchant.Review.2.keyCompleteRef", "//*[@id=\"super-container\"]/div/div/div[1]/div[1]/div[1]/ul/li[2]/div[2]/p");
		keyConfigMap.put("merchant.Review.3.keyCompleteRef", "//*[@id=\"super-container\"]/div/div/div[1]/div[1]/div[1]/ul/li[3]/div[2]/p");

		_utilHelper.getDetailsFromPageAsMap(
				_utilBase,
				keyElementNameList,
				keyConfigMap,
				keyElementValueInOutMap,
				bconstructLookup, lwaitBetweenExtract);
		
		DisplayUtils.HashMapDumpObjVals((HashMap<String, Object>) keyElementValueInOutMap, "Merchant Details");
			
	}

	//@Ignore
	@Test
	public void test0002_restaurant_search_() throws Exception	
	{
		_thisTestCaseNum = "TC0002";
		_thisTestCase = "YELP0001_" + _thisTestCaseNum
				+ " Restaurant Search with filter - ";
		_thisTestCaseDesc = "Restaurant Pizza search with neighborhood (1,3) and distance filter (1,3)";
		_strDataTestCase = "";
		
		collectOneTestCaseDetailData("Result_FiltersUsed", "Restaurant Pizza / Neighborhood 1,3 / Distance 1,3");
		
		collectOneTestCaseDetailData("Test_CustomerName", "Yelp");

		collectTestCaseDetailData();
		// Start with fail
		collectOneTestCaseDetailData("Test_Result", "Fail");
		
		Map<String, Object> keyElementValueInOutMap = new LinkedHashMap<String, Object>();
		List<String> starRatingList = new ArrayList<String>();
		Map<String, Object> starRatingMap = new LinkedHashMap<String, Object>();
		Map<String, Object> valuesMap = new LinkedHashMap<String, Object>();

		boolean bTestPass = true;

		SeleniumUtilYelpMerchantDetailsTest thisTest = new SeleniumUtilYelpMerchantDetailsTest();

		try {
			thisTest.init(false);
			thisTest.navigateToUrl(true);
			thisTest.setFocusOnMainSearch(true);
			thisTest.selectValueOnMainSearch(true);
			thisTest.setValueAndSearchOnNext(true);
			thisTest.clickFindAfterSettingNewSearchCriteria(true);
			thisTest.showCurrentUrl(true);
			
			String totalInitialRecords = thisTest.reportTotalInitialSearchResults(true);
			collectOneTestCaseDetailData("Result_InitialCount", totalInitialRecords);
			
			thisTest.expandAllFiltersToSelectParams(true);
			thisTest.applyOneSetOfParamsToFilterNeighborhood(true, new int []{1,3});
			thisTest.applyOneSetOfParamsToFilterDistance(true, new int []{1,3});
			int totalResults = thisTest.reportTotalFilteredSearchResults (true);
			collectOneTestCaseDetailData("Result_FinalCount", ""+totalResults);

			thisTest.reportTotalFilteredSearchResults(
					true,
					starRatingList,
					starRatingMap,
					valuesMap,
					totalResults
					) ;
			thisTest.openOneLinkFromTheResults(true,valuesMap); 
			thisTest.getMerchantDetailsFromTheResults(true,keyElementValueInOutMap,valuesMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bTestPass = false;
			
			// Capture screen shot ??
			thisTest.captureScreenShot(_thisTestCaseNum);
		} 	
		
		collectOneTestCaseDetailData("Result_MerchantName", (String) keyElementValueInOutMap.get("merchant.Name"));
		collectOneTestCaseDetailData("Result_MerchantAddress", (String) keyElementValueInOutMap.get("merchant.Address"));
		collectOneTestCaseDetailData("Result_MerchantPhone", (String) keyElementValueInOutMap.get("merchant.Phone"));
		collectOneTestCaseDetailData("Result_MerchantWebSite", (String) keyElementValueInOutMap.get("merchant.WebSite"));
		collectOneTestCaseDetailData("Result_Review1", (String) keyElementValueInOutMap.get("merchant.Review.1"));
		collectOneTestCaseDetailData("Result_Review2", (String) keyElementValueInOutMap.get("merchant.Review.2"));
		collectOneTestCaseDetailData("Result_Review3", (String) keyElementValueInOutMap.get("merchant.Review.3"));
		
		if (bTestPass) {
			collectOneTestCaseDetailData("Test_Result", "Pass");
			increment_totalPassTestCount();
		} else {
			increment_totalFailTestCount();
		}

		try {
			thisTest.tearDown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// To make JUnit happy
		Assert.assertTrue(_thisTestCaseDesc, bTestPass);
	}
	
	@Test
	public void test0003_restaurant_search_() throws Exception	
	{
		_thisTestCaseNum = "TC0003";
		_thisTestCase = "YELP0001_" + _thisTestCaseNum
				+ " Restaurant Search with filter - ";
		_thisTestCaseDesc = "Restaurant Pizza search with neighborhood (1,2) and distance filter (1,3)";
		_strDataTestCase = "";
		
		collectOneTestCaseDetailData("Result_FiltersUsed", "Restaurant Pizza / Neighborhood 1,2 / Distance 1,3");
		
		collectOneTestCaseDetailData("Test_CustomerName", "Yelp");

		collectTestCaseDetailData();
		// Start with fail
		collectOneTestCaseDetailData("Test_Result", "Fail");
		
		Map<String, Object> keyElementValueInOutMap = new LinkedHashMap<String, Object>();
		List<String> starRatingList = new ArrayList<String>();
		Map<String, Object> starRatingMap = new LinkedHashMap<String, Object>();
		Map<String, Object> valuesMap = new LinkedHashMap<String, Object>();

		boolean bTestPass = true;

		SeleniumUtilYelpMerchantDetailsTest thisTest = new SeleniumUtilYelpMerchantDetailsTest();

		try {
			thisTest.init(false);
			thisTest.navigateToUrl(true);
			thisTest.setFocusOnMainSearch(true);
			thisTest.selectValueOnMainSearch(true);
			thisTest.setValueAndSearchOnNext(true);
			thisTest.clickFindAfterSettingNewSearchCriteria(true);
			thisTest.showCurrentUrl(true);
			
			String totalInitialRecords = thisTest.reportTotalInitialSearchResults(true);
			collectOneTestCaseDetailData("Result_InitialCount", totalInitialRecords);
			
			thisTest.expandAllFiltersToSelectParams(true);
			thisTest.applyOneSetOfParamsToFilterNeighborhood(true, new int []{1,2});
			thisTest.applyOneSetOfParamsToFilterDistance(true, new int []{1,3});
			int totalResults = thisTest.reportTotalFilteredSearchResults (true);
			collectOneTestCaseDetailData("Result_FinalCount", ""+totalResults);

			thisTest.reportTotalFilteredSearchResults(
					true,
					starRatingList,
					starRatingMap,
					valuesMap,
					totalResults
					) ;
			thisTest.openOneLinkFromTheResults(true,valuesMap); 
			thisTest.getMerchantDetailsFromTheResults(true,keyElementValueInOutMap,valuesMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bTestPass = false;
			
			// Capture screen shot ??
			thisTest.captureScreenShot(_thisTestCaseNum);
		} 	
		
		collectOneTestCaseDetailData("Result_MerchantName", (String) keyElementValueInOutMap.get("merchant.Name"));
		collectOneTestCaseDetailData("Result_MerchantAddress", (String) keyElementValueInOutMap.get("merchant.Address"));
		collectOneTestCaseDetailData("Result_MerchantPhone", (String) keyElementValueInOutMap.get("merchant.Phone"));
		collectOneTestCaseDetailData("Result_MerchantWebSite", (String) keyElementValueInOutMap.get("merchant.WebSite"));
		collectOneTestCaseDetailData("Result_Review1", (String) keyElementValueInOutMap.get("merchant.Review.1"));
		collectOneTestCaseDetailData("Result_Review2", (String) keyElementValueInOutMap.get("merchant.Review.2"));
		collectOneTestCaseDetailData("Result_Review3", (String) keyElementValueInOutMap.get("merchant.Review.3"));
		
		if (bTestPass) {
			collectOneTestCaseDetailData("Test_Result", "Pass");
			increment_totalPassTestCount();
		} else {
			increment_totalFailTestCount();
		}
		
		try {
			thisTest.tearDown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// To make JUnit happy
		Assert.assertTrue(_thisTestCaseDesc, bTestPass);
	}
	
	@Test
	public void test0004_bar_search_() throws Exception	
	{
		_thisTestCaseNum = "TC0004";
		_thisTestCase = "YELP0001_" + _thisTestCaseNum
				+ " Delivery Search with filter - ";
		_thisTestCaseDesc = "Restaurant Indian search with neighborhood (1,2) and price filter (1,3)";
		_strDataTestCase = "";
		
		collectOneTestCaseDetailData("Result_FiltersUsed", "Restaurant Indian / Neighborhood 1,2 / Price 1,3");
		
		collectOneTestCaseDetailData("Test_CustomerName", "Yelp");

		collectTestCaseDetailData();
		// Start with fail
		collectOneTestCaseDetailData("Test_Result", "Fail");
		
		Map<String, Object> keyElementValueInOutMap = new LinkedHashMap<String, Object>();
		List<String> starRatingList = new ArrayList<String>();
		Map<String, Object> starRatingMap = new LinkedHashMap<String, Object>();
		Map<String, Object> valuesMap = new LinkedHashMap<String, Object>();

		boolean bTestPass = true;

		SeleniumUtilYelpMerchantDetailsTest thisTest = new SeleniumUtilYelpMerchantDetailsTest();

		try {
			thisTest.init(false);
			thisTest.navigateToUrl(true);
			thisTest.setFocusOnMainSearch(true);
			thisTest.selectValueOnMainSearch(true,1);
			thisTest.setValueAndSearchOnNext(true,"Step 4","Restaurant Indian");
			thisTest.clickFindAfterSettingNewSearchCriteria(true);
			thisTest.showCurrentUrl(true);
			
			String totalInitialRecords = thisTest.reportTotalInitialSearchResults(true);
			collectOneTestCaseDetailData("Result_InitialCount", totalInitialRecords);
			
			thisTest.expandAllFiltersToSelectParams(true);
			thisTest.applyOneSetOfParamsToFilterNeighborhood(true, new int []{1,2});
			thisTest.applyOneSetOfParamsToFilterPrice(true, new int []{1,2});
			int totalResults = thisTest.reportTotalFilteredSearchResults (true);
			collectOneTestCaseDetailData("Result_FinalCount", ""+totalResults);

			thisTest.reportTotalFilteredSearchResults(
					true,
					starRatingList,
					starRatingMap,
					valuesMap,
					totalResults
					) ;
			thisTest.openOneLinkFromTheResults(true,valuesMap); 
			thisTest.getMerchantDetailsFromTheResults(true,keyElementValueInOutMap,valuesMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bTestPass = false;
			
			// Capture screen shot ??
			thisTest.captureScreenShot(_thisTestCaseNum);
		} 	
		
		collectOneTestCaseDetailData("Result_MerchantName", (String) keyElementValueInOutMap.get("merchant.Name"));
		collectOneTestCaseDetailData("Result_MerchantAddress", (String) keyElementValueInOutMap.get("merchant.Address"));
		collectOneTestCaseDetailData("Result_MerchantPhone", (String) keyElementValueInOutMap.get("merchant.Phone"));
		collectOneTestCaseDetailData("Result_MerchantWebSite", (String) keyElementValueInOutMap.get("merchant.WebSite"));
		collectOneTestCaseDetailData("Result_Review1", (String) keyElementValueInOutMap.get("merchant.Review.1"));
		collectOneTestCaseDetailData("Result_Review2", (String) keyElementValueInOutMap.get("merchant.Review.2"));
		collectOneTestCaseDetailData("Result_Review3", (String) keyElementValueInOutMap.get("merchant.Review.3"));
		
		if (bTestPass) {
			collectOneTestCaseDetailData("Test_Result", "Pass");
			increment_totalPassTestCount();
		} else {
			increment_totalFailTestCount();
		}
		
		try {
			thisTest.tearDown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// To make JUnit happy
		Assert.assertTrue(_thisTestCaseDesc, bTestPass);
	}
}
