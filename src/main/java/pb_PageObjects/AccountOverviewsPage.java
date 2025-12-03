package pb_PageObjects;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AccountOverviewsPage {

	protected WebDriver driver;
	// Locators
	private By accountOverviewTable = By.xpath("//table[@id='accountTable']/tbody/tr/child::td/a");
	private By goBtn = By.xpath("//input[@value='Go']");
	private By transactionTable = By.xpath("//table[@id='transactionTable']/tbody/tr");

	public AccountOverviewsPage(WebDriver driver) {
		this.driver = driver;
	}

	// Verifying the transferred amount in Account Overview Page
	public String verifyFundTransfered(Map<String, String> transferDetails) {
		//String amount = transferDetails.get("amount");
		String fromAccount = transferDetails.get("fromAccount");
		
		boolean accountFound = false;
		int tablesize = driver.findElements(accountOverviewTable).size();
		try {
			if (tablesize == 0) {
				throw new Exception("Account overview table is empty.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			for (int i = 1; i <= tablesize; i++) {
				List<WebElement> accounts = driver
						.findElements(By.xpath("//table[@id='accountTable']/tbody/tr[" + i + "]/child::td/a"));
				String accountNum = accounts.get(0).getText();
				//System.out.println("Account Number: " + accountNum);
				//System.out.println("Expected From Account: " + fromAccount);
				if (accountNum.equals(fromAccount)) {
					accounts.get(0).click();
					accountFound = true;
					break;
				}
			}
		}
		if (accountFound) {
			return "Fund Transfer Verified Successfully";
		} else {
			return "Fund Transfer Verification Failed";
		}

	}

	// Verifying the amount debitted from the account after transfer.
	public String verifyAmountDebitted(Map<String, String> transferDetails) {
		double amount = Double.parseDouble(transferDetails.get("amount").trim());
		String fromAccount = transferDetails.get("fromAccount");
	
		// Filtering transactions by activityPeriod 'All' and type 'All' and verifying
		// amount debited or not from the account
		Select activityPeriodDropdown = new Select(driver.findElement(By.id("month")));
		activityPeriodDropdown.selectByVisibleText("All");
		Select TypeDropdown = new Select(driver.findElement(By.id("transactionType")));
		TypeDropdown.selectByVisibleText("All");
		driver.findElement(goBtn).click();
		boolean amountDebitted = false;

		int transactionTableSize = driver.findElements(transactionTable).size();
		System.out.println("Number of transactions in transaction table: " + transactionTableSize);

		for (int j = 1; j <= transactionTableSize; j++) {
			List<WebElement> debit = driver.findElements(By.xpath("//table[@id='transactionTable']/tbody/tr[" + j + "]/child::td[3]"));
			List<WebElement> transactionSent = driver.findElements(By.xpath("//table[@id='transactionTable']/tbody/tr[" + j + "]/child::td[2]/a"));
			String transactionSentmsg = transactionSent.get(0).getText();
			String debitAmount = debit.get(0).getText();
			double debitAmountInNum = Double.parseDouble(debitAmount.replace("$", "").trim());
			
			if (debitAmountInNum == amount && transactionSentmsg.equals("Funds Transfer Sent")) {
				transactionSent.get(0).click();
				System.out.println("Amount $" + amount + " debited from account: " + fromAccount);
				amountDebitted = true;
				break; // stop searching once found
			}
		}

		// After the loop
		if (amountDebitted) {
			return "Amount Transferred and Debited Successfully";
		} else {
			return "Transaction not found and amount not debited";
		}
	}
}
