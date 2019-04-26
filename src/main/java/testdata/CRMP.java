package testdata;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import genericfunctions.Constants;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class CRMP {

	public static String value = null;
	public static String key = null;
	public static String celldata = null;
    
	
public static Map<String, String> getInputdata(String sheet, String testcasename) {
    
	Map<String, String> data = null;

		try {

			data = new HashMap<String, String>();
			File path = new File(Constants.exceldata);
			FileInputStream fis = new FileInputStream(path);//
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet("sheet1");
			int rows = sh.getLastRowNum() - sh.getFirstRowNum();
			int col = sh.getRow(0).getLastCellNum();
			System.out.println(rows);
			System.out.println(col);
			for (int i = 1; i <= rows; i++) {
				celldata = sh.getRow(i).getCell(0).getStringCellValue();
				System.out.println(celldata);
				if (celldata.equalsIgnoreCase(testcasename)) {
					for (int j = 1; j < col; j++) {

						key = sh.getRow(0).getCell(j).getStringCellValue();
						value = sh.getRow(i).getCell(j).getStringCellValue();
						/*
						 * System.out.println(key); System.out.println(value);
						 */
					data.put(key, value);
					System.out.println(data);
						
					}

				}
				

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}

}