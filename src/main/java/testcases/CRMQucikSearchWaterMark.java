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

public class CRMQucikSearchWaterMark extends CRMProLibraries {

	String str = null;
	static Map<String, String> data = CRMP.getInputdata("Sheet1", "CRMCompaniesTestCase");

	@Test(priority = 0)
	public void CRMLogin() {
		
		input(crmcomp.getUserName(), data.get("username"), "USername");
		input(crmcomp.getPassword(), data.get("password"), "Password");
		click(crmcomp.getSubmit(), "clciked on submit button");
		delay(4000);
		driver.switchTo().frame("mainpanel");
		String str = crmcomp.getQuickMark().getAttribute("value");
		System.out.println(str);
		// delay(3000);
		String parent = driver.getWindowHandle();
		driver.findElement(By.xpath("//div[@id='navMenu']/ul/li[23]")).click();
		
		Set<String> childs = driver.getWindowHandles();
		Iterator<String> ie = childs.iterator();
		while (ie.hasNext()) {
			
			String child = ie.next();
			
			if(!parent.equals(child)) {
				
				driver.switchTo().window(child);
				List<WebElement> list = driver.findElements(By.xpath("//div[@id='post-listing']/article/h3/a"));
				for (WebElement ele : list) {

				//	driver.findElement(By.xpath("//div[@id='post-listing']/article/h3/a"));
					String title = ele.getText();
					if (title.equals("Do not share logins!")) {
						ele.click();
						delay(3000);
						String str1 = driver.findElement(By.xpath("//section[@id='content']/article/header/h2")).getText();
						System.out.println(str1);
						System.out.println(driver.getTitle());
						break;

					}
				}
				driver.close();
			}
			

		}
		    delay(3000);
			driver.switchTo().window(parent);
			driver.switchTo().frame("mainpanel");
		driver.close();
	}
}
