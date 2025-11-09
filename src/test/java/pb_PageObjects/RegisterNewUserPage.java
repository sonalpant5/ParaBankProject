package pb_PageObjects;

import java.time.Duration;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//Sonal Code
/* 
 * This class handles the registration of a new user on the ParaBank application.
 */
public class RegisterNewUserPage {

WebDriver driver;
	
	By firstName = By.id("customer.firstName");
	By lastName = By.id("customer.lastName");
	By address = By.id("customer.address.street");
	By city = By.id("customer.address.city");
	By state = By.id("customer.address.state");
	By zipCode = By.id("customer.address.zipCode");
	By phoneNumber = By.id("customer.phoneNumber");
	By ssn = By.id("customer.ssn");
	By username = By.id("customer.username");
	By password = By.id("customer.password");
	By confirmPassword = By.id("repeatedPassword");
	By registerButton = By.xpath("//input[@value='Register']");
	By successMsg = By.xpath("//p[contains(text(),'Your account was created successfully')]");

	
	public RegisterNewUserPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void registerNewUser(Map<String,String> userData) {
		driver.findElement(firstName).sendKeys(userData.get("firstName"));
		driver.findElement(lastName).sendKeys(userData.get("lastName"));
		driver.findElement(address).sendKeys(userData.get("address"));
		driver.findElement(city).sendKeys(userData.get("city"));
		driver.findElement(state).sendKeys(userData.get("state"));
		driver.findElement(zipCode).sendKeys(userData.get("zipCode"));
		driver.findElement(phoneNumber).sendKeys(userData.get("phoneNumber"));
		driver.findElement(ssn).sendKeys(userData.get("ssn"));
		driver.findElement(username).sendKeys(userData.get("username"));
		driver.findElement(password).sendKeys(userData.get("password"));
		driver.findElement(confirmPassword).sendKeys(userData.get("confirmPassword"));
		driver.findElement(registerButton).click();
	}
	
	public boolean confirmRegistrationSuccess() {
		try{
			return(new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(successMsg))
                    .isDisplayed());
		}catch(Exception e) {
            return false;
        }
}
}
