package pb_PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
 * This class represents the Home Page of the ParaBank application.
 */
public class HomePage {

	protected WebDriver driver;
	By registerLink = By.linkText("Register");
	By logoutLink = By.xpath("//li/a[text()='Log Out']");

	public HomePage(WebDriver driver) {
		this.driver = driver;
//		String actualTitle = driver.getTitle().trim().replaceAll("\\s+", " ");
//		String expectedTitle = "ParaBank | Register for Free Online Account Access";
//
//		if(!actualTitle.equals(expectedTitle))
//		{
//			System.out.println("Title mismatch!");
//			throw new IllegalStateException("This is not Home Page," + " current page is: " +driver.getTitle());
//		}
	}

	public void navigateToRegistrationPage() {
		// Code to navigate to the registration page
		driver.findElement(registerLink).click();		
	}
	
	public void logout() {
		driver.findElement(logoutLink).click();
	}

	
}
