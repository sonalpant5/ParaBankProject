package pb_TestCases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import pb_BaseLibrary.BaseClass_pb;
import pb_PageObjects.AccountServiceUserHomepage;
import pb_PageObjects.HomePage;
import pb_PageObjects.LoginPage;
import pb_PageObjects.RegisterNewUserPage;
import pb_Utils.RandomDataUtils;

/*
 * This test class verifies the registration of a new user.
 */

public class TestRegisterNewUserAndLogin extends BaseClass_pb {
	@Test
	public void verifyRegisterNewUser() throws InterruptedException {
		Map<String,String> userData = RandomDataUtils.randomUserData();

		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegistrationPage();

		RegisterNewUserPage regisNewUser = new RegisterNewUserPage(driver);
		regisNewUser.registerNewUser(userData);

		Assert.assertTrue(regisNewUser.confirmRegistrationSuccess(), "Registration Failed!");
		
		AccountServiceUserHomepage serviceHomePage = new AccountServiceUserHomepage(driver);
		serviceHomePage.navigateTo("Log Out");
		
		LoginPage lp = new LoginPage(driver);
		String username = userData.get("username");
		String password = userData.get("password");
		lp.enterLoginDetails(username, password);
	

		Assert.assertTrue(lp.isLoginSuccesful(), "Login Failed");

	}
}
