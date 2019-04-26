package webpages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import genericfunctions.CRMProLibraries;

public class CRMQuestionMark extends CRMProLibraries {

	public CRMQuestionMark(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[@name='username']")
	private WebElement username;

	public WebElement getUserName() {
		return username;
	}

	@FindBy(how = How.XPATH, using = "//input[@name='password']")
	private WebElement password;

	public WebElement getPassword() {
		return password;
	}

	@FindBy(how = How.XPATH, using = "//input[@value='Login']")
	private WebElement submit;

	public WebElement getSubmit() {
		return submit;
	}

	@FindBy(how = How.XPATH, using = "//div[@id='navmenu']/ul/li[3]/a")
	private WebElement companies;

	public WebElement getCompanies() {
		return companies;
	}

	@FindBy(how=How.XPATH,using="//div[@id='navmenu']/ul/li[3]/ul/li[1]/a")
	private WebElement newcompany;
	public WebElement getNewCompany(){
		return newcompany;
	}
	@FindBy(how=How.XPATH,using="//select[@name='status']/following::input[@class='button_help']")
	private List<WebElement> question;
	public List<WebElement> getQuestionMark(){
		return question;
	}
}
