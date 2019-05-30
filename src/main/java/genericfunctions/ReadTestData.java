package genericfunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;


public class ReadTestData {

	public static String sPreviousTestCaseName;
	public static int iRowNo;
	public static File file;
	public static FileInputStream fin;
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static int rows;
	public static int cols;
	public static String sCurTestCaseName;
	public static String sKey;
	public static String sValue;
	public static Map.Entry<String, Map<String, String>> entry;
	public static Map<String, String> values;

	/*
	 * Below Method is Used to get multiple sets of data from excel. Parameters:
	 * File Path, Sheet Name, TestCase Name
	 */

	/*
	 * public static Map<String, Map<String, String>> readTestData(String sFilePath,
	 * String sSheetName, String sTestCaseName) throws Exception {
	 * 
	 * Map<String, Map<String, String>> objTestData = new HashMap<String,
	 * Map<String, String>>(); try { iRowNo = 1; // Go To File Path file = new
	 * File(sFilePath); fin = new FileInputStream(file); wb = new XSSFWorkbook(fin);
	 * sh = wb.getSheet(sSheetName); rows = sh.getLastRowNum() -
	 * sh.getFirstRowNum(); cols = sh.getRow(0).getLastCellNum();
	 * 
	 * i is used to get the Test Case Name
	 * 
	 * for (int i = 1; i <=rows; i++) {
	 * 
	 * 
	 * // Below Map Interface is used to find Single Set of Data Map<String, String>
	 * objRowData = new HashMap<String, String>();
	 * 
	 * sCurTestCaseName = sh.getRow(i).getCell(0).getStringCellValue().trim();
	 * 
	 * if (sCurTestCaseName.length() == 0) { Assert.fail(sCurTestCaseName
	 * +" Current Test Case Name is Empty, Kindly provide valid TestCaseName in Sheet"
	 * ); break; } else if (sTestCaseName.trim().length() <= 0) {
	 * Assert.fail(sTestCaseName
	 * +"Current Test Case Name  is Empty, Kindly Pass valid TestCaseName"); break;
	 * }
	 * 
	 * if (sCurTestCaseName.equalsIgnoreCase(sTestCaseName)) {
	 * 
	 * // Below Loop is used to get all the headers names in the excel for (int j =
	 * 0; j < cols; j++) { sKey =
	 * sh.getRow(0).getCell(j).getStringCellValue().trim(); sValue = ""; if
	 * (sh.getRow(i).getCell(j) != null) { if (sh.getRow(i).getCell(j).getCellType()
	 * == CellType.STRING) { sValue =
	 * sh.getRow(i).getCell(j).getStringCellValue().trim(); } else { sValue =
	 * sh.getRow(i).getCell(j).getRawValue(); } } objRowData.put(sKey, sValue); }
	 * objTestData.put("Row" + iRowNo, objRowData);
	 * 
	 * objRowData = null;
	 * 
	 * iRowNo++; } }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return objTestData; }
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, String> getJsonData(String fileName, String testCaseName) {

		Map<String, String> testData = new HashMap<String, String>();

		try {
			if (fileName.length() > 1 && !fileName.isEmpty() && !fileName.equals(null) && testCaseName.length() > 1
					&& !testCaseName.isEmpty() && !testCaseName.equals(null)) {

				// Parsing Json File
				Object obj = new JSONParser()
						.parse(new FileReader(System.getProperty("user.dir") + "/ExcelData/" + fileName + ".json"));

				// TypeCaset Obj to JSonObject
				JSONObject jobj = (JSONObject) obj;

				Map data = (Map) jobj.get(testCaseName);

				Iterator<Map.Entry> ite = data.entrySet().iterator();

				while (ite.hasNext()) {
					Map.Entry pair = ite.next();
					String key = pair.getKey().toString();
					String value = pair.getValue().toString();
					testData.put(key, value);
				}

			} else {
				Assert.fail("FileName OR TestCaseName is not Valid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testData;
	}
}
