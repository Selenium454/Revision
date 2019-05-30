package testcases;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import genericfunctions.CRMProLibraries;
import testdata.CRMP;

public class CRMCompaniesTestCase extends CRMProLibraries {

	String str = null;
	static Map<String, String> data = CRMP.getInputdata("Sheet1", "CRMCompaniesTestCase");

	@Test(priority = 0)
	public void CRMLogin() {
		input(crmcomp.getUserName(), data.get("username"), "USername");
		input(crmcomp.getPassword(), data.get("password"), "Password");
		click(crmcomp.getSubmit(), "clciked on submit button");
		delay(4000);
		driver.switchTo().frame("mainpanel");

		mouseover(crmcomp.getCompanies());

		crmcomp.getListOfComp();
		List<WebElement> tot = crmcomp.getCompaniesOptions();
		System.out.println(tot.size());

		for (WebElement ele : tot) {

			crmcomp.getCompaniesOptions();
			String str = ele.getText();

			if (str.equals("New Company")) {
				click(crmcomp.getNewCompany(), "click on new company");
				break;
			}
		}
	}

//		for (int i = 1; i <= tot.size(); i++) {
//
//			driver.findElement(By.xpath("//div[@id='navmenu']/ul/li[3]/ul/li[" + i + "]/a]")).getText();
//			System.out.println(str);
//			if (str.equals("New Company")) {
//			click(crmcomp.getNewCompany(), "click on new company");
//		}
//		}	

	@Test(priority = 1)
	public void CreateNewComp() {

		// input(crmcomp.getCompany(), "AusSelenium", "Compname");
		input(crmcomp.getCompany(), data.get("company"), "Compname");

		click(crmcomp.getLookUp(), "click on lookup");
		delay(3000);
		String parent = driver.getWindowHandle();
		Set<String> childs = driver.getWindowHandles();
		System.out.println(childs.size());
		Iterator<String> It = childs.iterator();
		while (It.hasNext()) {
			String child = It.next();

			if (!child.equals(parent)) {
				driver.switchTo().window(child);
				click(crmcomp.getLookUpSearch(), "clicked on lookup search");
				driver.switchTo().alert().accept();
				input(crmcomp.getEnterdata(), "Hello", "enter data for lookup search");
				driver.findElement(By.xpath("//input[@value='Search']")).click();
				crmcomp.getScreenshot("./Report/Screenshot/sample.png");
				driver.close();
			}
		}
		delay(3000);
		driver.switchTo().window(parent);
		driver.switchTo().frame("mainpanel");
		click(crmcomp.getSave(), "Click on save button");
		driver.close();

	}
	
}
