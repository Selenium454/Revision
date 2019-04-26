package genericfunctions;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import reports.CRMProReport;

public class CRMProLibraries extends CRMProReport {

	public void input(WebElement ele, String testdata, String field) {
		try {
			if (ele.isDisplayed()) {
				ele.clear();
				ele.sendKeys(testdata);
				logger.log(Status.INFO, "Entered data " + testdata + " in " + field);
			} else {
				logger.log(Status.FAIL, "Element not found " + ele);
			}
		} catch (Exception e) {

			Assert.fail("failed due to " + e.getMessage());

		}
	}

	public void click(WebElement ele, String field) {
		try {
			elementTobeClickable(ele);
			if (ele.isDisplayed()) {
				ele.click();
				logger.log(Status.INFO, "Clicked on " + " ON " + field);

			}
		} catch (Exception e) {
			Assert.fail("failed due to " + e.getMessage());

		}
	}

	public void selectByValue(WebElement ele, String testdata, String field) {

		if (ele.isDisplayed()) {
			new Select(ele).selectByValue(testdata);
			logger.log(Status.INFO, "Selected value " + " from drop down " + field);
		}
	}

	public void selectByText(WebElement ele, String testdata, String field) {

		if (ele.isDisplayed()) {
			new Select(ele).selectByVisibleText(testdata);
			logger.log(Status.INFO, "Selected value from " + " from " + field);
		}
	}

	public WebElement explicitwait(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.visibilityOf(ele));

	}

	public String getText(WebElement ele) {
		String str = null;
		if (ele.isDisplayed()) {
			str = ele.getText().trim();
		}
		return str;
	}

	public WebElement elementTobeClickable(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public String getFirstSelectedOption(WebElement ele) {
		return new Select(ele).getFirstSelectedOption().getText().trim();

	}

	public void delay(int time) {
		try {
			Thread.sleep(time + 000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void alertaccept() {
		driver.switchTo().alert().accept();
	}

	public void mouseover(WebElement ele) {
		explicitwait(ele);
		Actions action = new Actions(driver);
		action.moveToElement(ele).build().perform();
	}
}
