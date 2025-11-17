package pb_PageObjects;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class TransferFundsPage {
	
	protected WebDriver driver;
	private By amountTxtbox = By.id("amount");
	private Select fromAccountDropdown;
	private Select toAccountDropdown ;
	private By transferFundsBtn = By.xpath("//input[@value='Transfer']");
	private By resultMsg = By.xpath("//div[@id='showResult']/h1");
	
	public TransferFundsPage(WebDriver driver) {
		this.driver = driver;		
	}
	
	public  Map<String, String> tranferFunds(String amount) {	
		// Transferring Funds from Transfer Funds Link
		 driver.findElement(amountTxtbox).clear();
		 driver.findElement(amountTxtbox).sendKeys(amount);
		
		fromAccountDropdown = new Select(driver.findElement(By.id("fromAccountId")));
		fromAccountDropdown.selectByIndex(0);
		String fromAccount = fromAccountDropdown.getFirstSelectedOption().getText();
		
		toAccountDropdown = new Select(driver.findElement(By.id("toAccountId")));
		toAccountDropdown.selectByIndex(0);
		String toAccount = toAccountDropdown.getFirstSelectedOption().getText();
		
		try {
			if(driver.findElement(amountTxtbox).equals("")) {		
                throw new Exception("Amount cannot be empty");}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			
            }	driver.findElement(transferFundsBtn).click();	
			if (driver.findElement(resultMsg).getText().contains("Transfer Complete!")) {
				System.out.println("Transfer of $" + amount + " from " + fromAccount + " to " + toAccount
						+ " completed successfully.");
			} else {
				System.out.println("Transfer failed.");
			}
			
			Map<String, String> transferDetails = new HashMap<>();
			transferDetails.put("amount", amount);
			transferDetails.put("fromAccount", fromAccount);
			transferDetails.put("toAccount", toAccount);
			
//		//Navigating to Account Overview Page
//			driver.findElement(By.linkText("Account Overview")).click();
			return transferDetails;
			
	}
}
