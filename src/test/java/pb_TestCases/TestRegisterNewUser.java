package pb_TestCases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import pb_BaseLibrary.BaseClass_pb;
import pb_PageObjects.HomePage;
import pb_PageObjects.RegisterNewUserPage;
import pb_Utils.RandomDataUtils;

/*
 * This test class verifies the registration of a new user.
 */

public class TestRegisterNewUser extends BaseClass_pb {
	@Test
	public void verifyRegisterNewUser() throws InterruptedException {
		Map<String,String> userData = RandomDataUtils.randomUserData();

		HomePage homePage = new HomePage(driver);
		homePage.navigateToRegistrationPage();

		RegisterNewUserPage regisNewUser = new RegisterNewUserPage(driver);
		regisNewUser.registerNewUser(userData);

		Assert.assertTrue(regisNewUser.confirmRegistrationSuccess(), "Registration Failed!");

	}
}
