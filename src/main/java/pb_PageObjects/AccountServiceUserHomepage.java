package pb_PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountServiceUserHomepage {
	
	protected WebDriver driver;
	
	public AccountServiceUserHomepage(WebDriver driver) {
		this.driver = driver;

	}
	public void navigateTo(String linkName) {
	    driver.findElement(By.linkText(linkName)).click();
	    
	}
}

	
