package pb_PageObjects;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountServiceUserHomepage {
	
	protected WebDriver driver;
	
	public AccountServiceUserHomepage(WebDriver driver) {
		this.driver = driver;

	}
	public void navigateTo(String linkName) {
	    driver.findElement(By.linkText(linkName)).click();
	    
//	    switch (linkName) {
//	        case "Open New Account": return new OpenNewAccountPage(driver);
////	        case "Accounts Overview": return new AccountsOverviewPage(driver);
////	        case "Transfer Funds": return new TransferFundsPage(driver);
////	        case "Bill Pay": return new BillPayPage(driver);
////	        case "Find Transactions": return new FindTransactionPage(driver);
////	        case "Update Contact Info": return new UpdateContactInfoPage(driver);
////	        case "Request Loan": return new RequestLoanPage(driver);
////	        case "Log Out": return new LoginPage(driver);
//	        default: throw new IllegalArgumentException("Unknown link: " + linkName);
//	    }
	}
}

	
