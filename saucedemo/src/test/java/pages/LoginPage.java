package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class LoginPage extends BaseClass {
	
	@FindBy(how=How.ID , using = "user-name")
	public WebElement userName;
	
	@FindBy(id="password")
	public WebElement passWord;
	
	@FindBy(id="login-button")
	public WebElement submit;
	
	@FindBy(xpath = "//h3")
	public WebElement errorMessage;
	
	public LoginPage(WebDriver driver) {
		logger.info("initializing page elements");
		PageFactory.initElements(driver, this);
	}
	
	public void submitToLogin(String uName, String pWord) {
		logger.info("typing username");
		userName.sendKeys(uName);
		logger.info("typing password");
		passWord.sendKeys(pWord);
		logger.info("clicking submit button");
		submit.click();
		
	}

	public String getErrorMessage() {
		String errMessage = errorMessage.getText();
		return errMessage;
	}
}
