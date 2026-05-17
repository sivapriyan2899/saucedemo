package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import commonUtilities.GetDataFromExcel;
import listeners.RetryAnalyser;
import pages.LoginPage;

public class LoginPageTest extends BaseClass {
	
	
	@Test(dataProvider = "loginData", dataProviderClass = GetDataFromExcel.class)
	public void loginWithValidCredentials(String userName, String passWord) {
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.submitToLogin(userName, passWord);
		String actualTitle = driver.getTitle();
		String expectedTile = "Swag Labs";
		Assert.assertEquals(actualTitle, expectedTile);
	}
	
	@Test(dataProvider = "loginData", dataProviderClass = GetDataFromExcel.class, retryAnalyzer = RetryAnalyser.class)
	public void loginWithInvalidCredentials(String userName, String passWord) {
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.submitToLogin(userName, passWord);
		String actualMessage = "Epic sadface: Username and password do not match any user in this service";
		Assert.assertEquals(loginPage.getErrorMessage(), actualMessage);
	}
}
