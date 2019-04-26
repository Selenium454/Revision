package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import genericfunctions.CRMProLibraries;

public class CRMQuickMark extends CRMProLibraries {

	public CRMQuickMark(WebDriver driver) {
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

    @FindBy(how=How.XPATH,using ="//div[@id='quickSearchPanel']/following::input[3]")
    private WebElement quick;
    public WebElement getQuickMark() {
    	return quick;
    }
	
}
