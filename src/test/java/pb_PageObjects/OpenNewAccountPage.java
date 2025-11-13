package pb_PageObjects;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenNewAccountPage {
	
	protected WebDriver driver;
	Select accountTypeDropdown ;
	Select accountNumberDropdown ;
	private By openNewAccountBtn = By.xpath("//input[@value='Open New Account']");
	private By newAccountNumber = By.xpath("//a[@id='newAccountId']");
	private By actualAccountType = By.xpath("//td[text()='Account Type:']/following-sibling::td");
	private By actualAccountNumber = By.xpath("//td[text()='Account Number:']/following-sibling::td");	
	
	public OpenNewAccountPage(WebDriver driver) {
		this.driver = driver;
		String actualTitle = driver.getTitle().trim().replaceAll("\\s+", " ");
		String expectedTitle = "ParaBank | Open Account";

		if (!actualTitle.equals(expectedTitle)) {
			System.out.println("Title mismatch!");
			throw new IllegalStateException(
					"This is not Open New Account Page," + " current page is: " + driver.getTitle());
		}
	}
	
	public boolean isNewAccountOpenSuccess(String accountType) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		
		accountTypeDropdown = new Select(driver.findElement(By.id("type")));
		accountTypeDropdown.selectByVisibleText(accountType);
			
		accountNumberDropdown = new Select(driver.findElement(By.id("fromAccountId")));
		accountNumberDropdown.selectByIndex(0);
		
		driver.findElement(openNewAccountBtn).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(newAccountNumber));
	    String newAccNumber = driver.findElement(newAccountNumber).getText().trim();;	   	    
	    driver.findElement(newAccountNumber).click();
	   // Thread.sleep(3000);
	    
	    //Actual Account Details Verification
	    
	    wait.until(driver -> {
	        String text = driver.findElement(actualAccountNumber).getText().trim();
	        return !text.isEmpty();
	    });
	    //wait.until(ExpectedConditions.textToBePresentInElementLocated(actualAccountNumber, newAccNumber));
	    String actualAccNumber = driver.findElement(actualAccountNumber).getText().trim();
	    
	    wait.until(driver -> {
	        String text = driver.findElement(actualAccountType).getText().trim();
	        return !text.isEmpty();
	    });
		//wait.until(ExpectedConditions.textToBePresentInElementLocated(actualAccountType, accountType.trim()));	    
	    String actualAccType = driver.findElement(actualAccountType).getText().trim();
	    
	    String expectedAccNumber = newAccNumber;
	    String expectedAccType = accountType.trim();
	   
	    System.out.println("New account created with Account Number: " + newAccNumber);
	    System.out.println("expectedAccType: " + expectedAccType); 
	    System.out.println("actualAccNumber: " + actualAccNumber);
	    System.out.println("actualAccType: " + actualAccType);
	   
	    
	    boolean success = expectedAccType.equals(actualAccType) && expectedAccNumber.equals(actualAccNumber); {
        	if(success) {
        		 System.out.println("Account Number matches! Expected: " + expectedAccNumber + ", Actual: " + actualAccNumber);
        	} else {
        		 System.out.println("Account Number mismatch! Expected: " + expectedAccNumber + ", Actual: " + actualAccNumber);
        	}			
	}
	    return success;
}
}
	