package pb_TestCases;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pb_BaseLibrary.BaseClass_pb;
import pb_PageObjects.AccountServiceUserHomepage;
import pb_PageObjects.HomePage;
import pb_PageObjects.LoginPage;
import pb_PageObjects.OpenNewAccountPage;
import pb_PageObjects.RegisterNewUserPage;
import pb_Utils.RandomDataUtils;

public class TestOpenNewAccount extends BaseClass_pb {
	
	@Test
	public void testOpenNewAccount() throws Exception
	{
		Map<String,String> userData = RandomDataUtils.randomUserData();
//Here registering a new user to perform Open New Account operation
		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegistrationPage();
		
		RegisterNewUserPage regisNewUser = new RegisterNewUserPage(driver);
		regisNewUser.registerNewUser(userData);
		AccountServiceUserHomepage serviceHomePage = new AccountServiceUserHomepage(driver);
		serviceHomePage.navigateTo("Log Out");
		
// Logging in with newly registered user		
		LoginPage lp = new LoginPage(driver);
		String username = userData.get("username");
		String password = userData.get("password");
		lp.enterLoginDetails(username, password);
		
// Navigating and Verifying to Open New Account Page
		serviceHomePage.navigateTo("Open New Account");
		OpenNewAccountPage openNewAccPage = new OpenNewAccountPage(driver);
		String accountType = "SAVINGS";
		boolean result =  openNewAccPage.isNewAccountOpenSuccess(accountType);
		Assert.assertTrue(result, "Open New Account Failed");
		System.out.println("Result of Open New Account: " + result);
	}
}
