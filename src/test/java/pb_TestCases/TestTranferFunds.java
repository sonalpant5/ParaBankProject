package pb_TestCases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import pb_BaseLibrary.BaseClass_pb;
import pb_PageObjects.AccountOverviewsPage;
import pb_PageObjects.AccountServiceUserHomepage;
import pb_PageObjects.HomePage;
import pb_PageObjects.LoginPage;
import pb_PageObjects.OpenNewAccountPage;
import pb_PageObjects.RegisterNewUserPage;
import pb_PageObjects.TransferFundsPage;
import pb_Utils.RandomDataUtils;

public class TestTranferFunds extends BaseClass_pb {
	
	@Test
	public void testTransferFunds() throws Exception {
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
				openNewAccPage.isNewAccountOpenSuccess(accountType);

				serviceHomePage.navigateTo("Transfer Funds");
		// Test code for transferring funds would go here
				TransferFundsPage transferFundsPage = new TransferFundsPage(driver);
				String amount = "1000";
				Map<String, String> transferDetails= transferFundsPage.tranferFunds(amount);
				Thread.sleep(2000);
				serviceHomePage.navigateTo("Accounts Overview");
				AccountOverviewsPage accountOverviewsPage = new AccountOverviewsPage(driver);
				String tranferResult = accountOverviewsPage.verifyFundTransfered(transferDetails);
				Assert.assertEquals(tranferResult, "Fund Transfer Verified Successfully");
				
				String debitResult = accountOverviewsPage.verifyAmountDebitted(transferDetails);
				Assert.assertEquals(debitResult, "Amount Transferred and Debited Successfully");
	}

}
