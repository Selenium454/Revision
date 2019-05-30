package webpages;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import genericfunctions.CRMProLibraries;

public class CRMCompanies extends CRMProLibraries {

      
	
	public CRMCompanies(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how=How.XPATH,using="//input[@name='username']")
	private WebElement username;
	public WebElement getUserName() {
		return username;
	}
	@FindBy(how=How.XPATH,using="//input[@name='password']")
	private WebElement password;
	public WebElement getPassword() {
		return password;
	}
	
	@FindBy(how=How.XPATH,using="//input[@value='Login']")
	private WebElement submit;
	public WebElement getSubmit() {
		return submit;
	}
	@FindBy(how=How.XPATH,using="//div[@id='navmenu']/ul/li[3]/a")
	private WebElement companies;
	public WebElement getCompanies() {
		return companies;
	}
	@FindBy(how=How.XPATH,using="//div[@id='navmenu']/ul/li[3]/ul")
	private WebElement listofcomp;
	public WebElement getListOfComp() {
		return listofcomp;
	}
	@FindBy(how=How.XPATH,using="//div[@id='navmenu']/ul/li[3]/ul/li/a")
	private List<WebElement> companieslist;
	public List<WebElement> getCompaniesOptions(){
		return companieslist;
	}
	@FindBy(how=How.XPATH,using="//a[text()='New Company']")
	private WebElement newcompany;
	public WebElement getNewCompany(){
		return newcompany;
	}
	@FindBy(how=How.XPATH,using="//input[@id='company_name']")
	private WebElement company;
	public WebElement getCompany(){
		return company;
	}
	@FindBy(how=How.XPATH,using="//select[@name='status']")
	private WebElement status;
	public WebElement getStatus(){
		return status;
	}
	@FindBy(how=How.XPATH,using="//input[@name='address_title']")
	private WebElement address;
	public WebElement getAddress(){
		return address;
	}
	@FindBy(how=How.XPATH,using="//input[@name='client_lookup']")
	private WebElement parentcomp;
	public WebElement getParentComp(){
		return parentcomp;
	}
	@FindBy(how=How.XPATH,using="//input[@value='Lookup']")
	private WebElement lookup;
	public WebElement getLookUp(){
		return lookup;
	}
	@FindBy(how=How.XPATH,using="//input[@value='Search']")
	private WebElement lookupsearch;
	public WebElement getLookUpSearch(){
		return lookupsearch;
	}
	@FindBy(how=How.XPATH,using="//input[@id='search']")
	private WebElement enterforsearch;
	public WebElement getEnterdata(){
		return enterforsearch;
	}
	@FindBy(how=How.XPATH,using="//form[@id='companyForm']/table/tbody/tr[1]/td/input")
	private WebElement save;
	public WebElement getSave(){
		return save;
	}
}
